package internship.task1service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Task1service2Application {

    public static void main(String[] args) {
        SpringApplication.run(Task1service2Application.class, args);
    }

}
