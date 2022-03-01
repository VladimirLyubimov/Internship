package internship.service_dicovery_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryMicroserviceApplication {
    public static void main(String[] args){
        SpringApplication.run(ServiceDiscoveryMicroserviceApplication.class, args);
    }
}
