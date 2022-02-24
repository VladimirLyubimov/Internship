package internship.task1service2.repository;

import internship.task1service2.model.CityModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class CityRepository {
    private Connection con;

    public CityRepository(){}

    @PostConstruct
    public void openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "123456789");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void closeConnection(){
        try {
            if(con != null) {
                con.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<CityModel> getCityArray(){
        String query = "select * from city limit 7;";
        ArrayList<CityModel> result = new ArrayList<>();
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
            result.clear();
        }

        return result;
    }

    public Optional<CityModel> getCityById(int id){
        String query = "select * from city limit " + id + ';';
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
            result = Optional.empty();
        }

        return result;
    }

    private CityModel getCityFromResultSet(ResultSet rs){
        CityModel city = null;
        try {
            if (rs.next()) {
                city = new CityModel(rs.getString("Name"), rs.getString("CountryCode"), rs.getInt("Population"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return city;
    }
}