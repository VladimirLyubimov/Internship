package internship.task1service2.repository;

import internship.task1service2.exceptions.DatabaseConnectionException;
import internship.task1service2.exceptions.SQLRequestException;
import internship.task1service2.model.CityModel;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class CityRepository {

    //TODO потоконебезопасно. Спринг создает один экземпляр класса. И он один используется на все потоки.
    //TODO такой класс не должен иметь состояния (если есть поля, они должны быть финальные)
    //TODO что если два запроса прилетят одновременно?
    //TODO Погугли!!!
    private Connection con;

    public CityRepository(){}

    public void openConnection() throws DatabaseConnectionException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DatabaseConnectionException("Error with MySQL JDBC driver");
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "123456789");
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new DatabaseConnectionException("Can't connect to database. Check database URL, username and password");
        }
    }

    public void closeConnection() throws DatabaseConnectionException{
        try {
            if(con != null) {
                con.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new DatabaseConnectionException("Error with closing connection with database");
        }
    }

    public ArrayList<CityModel> getCityArray() throws SQLRequestException, DatabaseConnectionException{
        openConnection();

        String query = "select * from city limit 7;";
        ArrayList<CityModel> result = new ArrayList<>();
        // TODO погугли "try with resource"
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            CityModel city;
            while((city = getCityFromResultSet(rs)) != null) {
                result.add(city);
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            closeConnection();
            throw new SQLRequestException("Can't make request to database correctly");
        }

        closeConnection();

        return result;
    }

    public Optional<CityModel> getCityById(int id) throws SQLRequestException, DatabaseConnectionException{
        openConnection();

        String query = "select * from city where id = " + id + ';';
        Optional<CityModel> result;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            result = Optional.ofNullable(getCityFromResultSet(rs));

            rs.close();
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            closeConnection();
            throw new SQLRequestException("Can't make request to database correctly");
        }

        closeConnection();

        return result;
    }

    private CityModel getCityFromResultSet(ResultSet rs) throws SQLRequestException{
        CityModel city = null;
        try {
            if (rs.next()) {
                city = new CityModel(rs.getString("Name"), rs.getString("CountryCode"), rs.getInt("Population"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new SQLRequestException("Error occurred during working with JDBC result set");
        }

        return city;
    }
}
