package internship.task1service2.repository;

import internship.task1service2.exceptions.DatabaseConnectionException;
import internship.task1service2.exceptions.SQLRequestException;
import internship.task1service2.model.CityModel;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CityRepository {

    public CityRepository(){}

    public Connection openConnection() throws DatabaseConnectionException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DatabaseConnectionException("Error with MySQL JDBC driver");
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "123456789");
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new DatabaseConnectionException("Can't connect to database. Check database URL, username and password");
        }
    }

    public List<CityModel> getCityArray() throws SQLRequestException, DatabaseConnectionException{
        String query = "select * from city limit 7;";
        List<CityModel> result = new ArrayList<>();
        try (Connection con = openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query);) {
            CityModel city;
            while((city = getCityFromResultSet(rs)) != null) {
                result.add(city);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new SQLRequestException("Can't make request to database correctly");
        }

        return result;
    }

    public Optional<CityModel> getCityById(int id) throws SQLRequestException, DatabaseConnectionException{
        String query = "select * from city where id = " + id + ';';
        Optional<CityModel> result;
        try (Connection con = openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query);) {
            result = Optional.ofNullable(getCityFromResultSet(rs));
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new SQLRequestException("Can't make request to database correctly");
        }

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