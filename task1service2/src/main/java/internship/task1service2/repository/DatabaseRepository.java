package internship.task1service2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseRepository {
    private final DataSource dataSource;

    @Autowired
    public DatabaseRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
