package internship.task1service1.controller;

import internship.task1service1.model.CityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static internship.task1service1.controller.ResponseDataParser.getAnswer;

@RestController
public class RequestBrokerController {
    private final Logger logger =  LoggerFactory.getLogger(RequestBrokerController.class);

    @GetMapping
    public CityModel[] makeRequest(@RequestParam(defaultValue = "select * from city order by Population desc limit 5;") String query){
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8100?query=" + query.replace(' ', '+'))).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Successful connect to http://localhost:8090?query=" + query);
            return getAnswer(response.body());
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("Can't connect to http://localhost:8090?query=" + query);
            return new CityModel[0];
        }
    }
}
