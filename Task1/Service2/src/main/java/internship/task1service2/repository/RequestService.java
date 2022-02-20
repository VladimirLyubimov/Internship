package internship.task1service2.repository;

import internship.task1service2.model.CityModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;

@Component
public class RequestService {
    private Connection con;

    public RequestService(){}

    private ArrayList<CityModel> getData(ResultSet rs){
        ArrayList<CityModel> table = new ArrayList<>();
        try {
            while (rs.next()) {
                table.add(new CityModel(rs.getString("Name"), rs.getString("CountryCode"), rs.getInt("Population")));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            table.clear();
        }

        return table;
    }

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
            con.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<CityModel> performQuery(String query){
        ArrayList<CityModel> result = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            result = getData(rs);

            rs.close();
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            result.clear();
        }

        return result;
    }
}
