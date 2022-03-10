package internship.task1service2.repository;

import internship.task1service2.exceptions.DatabaseConnectionException;
import internship.task1service2.exceptions.SQLRequestException;
import internship.task1service2.model.ColumnModel;
import internship.task1service2.model.IndexModel;
import internship.task1service2.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseRepository {
    private final DataSource dataSource;
    private static final String DB_NAME = "world";

    @Autowired
    public DatabaseRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<TableModel> getDatabaseStructure() throws DatabaseConnectionException, SQLRequestException {
        List<TableModel> result = new ArrayList<>();

        try(Connection con = dataSource.getConnection()){
            DatabaseMetaData metaData = con.getMetaData();
            getTablesData(metaData, result);
            getColumnData(metaData, result);
        }
        catch (SQLException e){
            throw new DatabaseConnectionException("Can't establish connection with database to get metadata");
        }

        return result;
    }

    private void getTablesData(DatabaseMetaData metaData, List<TableModel> dbModel) throws SQLRequestException{
        try(ResultSet names = metaData.getTables(DB_NAME, null, null, new String[] {"TABLE"})){
            while(names.next()){
                dbModel.add(TableModel.builder()
                        .tableName(names.getString("TABLE_NAME"))
                        .tableType(names.getString("TABLE_TYPE"))
                        .tableSchema(DB_NAME)
                        .columns(new ArrayList<>())
                        .index(new ArrayList<>())
                        .keyColumn(new ArrayList<>())
                        .build());
            }
        }
        catch (SQLException e){
            throw new SQLRequestException("Got error during collecting data about table name, type and schema");
        }

        for(TableModel table: dbModel){
            try(ResultSet indexes = metaData.getIndexInfo(DB_NAME, null, table.getTableName(), false, true);
            ResultSet primeKeys = metaData.getPrimaryKeys(DB_NAME, null, table.getTableName())){
                while(indexes.next()) {
                    table.getIndex().add(IndexModel.builder()
                            .indexName(indexes.getString("INDEX_NAME"))
                            .indexTable(indexes.getString("TABLE_NAME"))
                            .indexColumn(indexes.getString("COLUMN_NAME")).build());
                }

                while(primeKeys.next()){
                    table.getKeyColumn().add(primeKeys.getString("COLUMN_NAME"));
                }
            }
            catch (SQLException e){
                throw new SQLRequestException("Got error during collecting data about table indexes and keys");
            }
        }
    }

    private void getColumnData(DatabaseMetaData metaData, List<TableModel> dbModel) throws SQLRequestException {
        for(TableModel table : dbModel){
            String query = String.format("select COLUMN_NAME, COLUMN_TYPE, NUMERIC_SCALE, NUMERIC_PRECISION, DATETIME_PRECISION, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE from information_schema.columns where table_schema like('%s') and table_name like('%s') order by table_name, ordinal_position", DB_NAME, table.getTableName());
            try (Statement stmt = metaData.getConnection().createStatement(); ResultSet rs = stmt.executeQuery(query)){
                while(rs.next()) {
                    table.getColumns().add(ColumnModel.builder()
                            .name(rs.getString("COLUMN_NAME"))
                            .type(rs.getString("COLUMN_TYPE"))
                            .scale(rs.getInt("NUMERIC_SCALE"))
                            .numericPrecision(rs.getInt("NUMERIC_PRECISION"))
                            .datetimePrecision(rs.getInt("DATETIME_PRECISION"))
                            .characterMaximumLength(rs.getInt("CHARACTER_MAXIMUM_LENGTH"))
                            .isNullable(rs.getString("IS_NULLABLE"))
                            .build());
                }
            }
            catch(SQLException e){
                throw new SQLRequestException("Got error during collecting data about columns");
            }
        }
    }
}
