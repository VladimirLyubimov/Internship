package internship.task1service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Task1service1Application {
//test
    public static void main(String[] args) {
        SpringApplication.run(Task1service1Application.class, args);
    }

}
