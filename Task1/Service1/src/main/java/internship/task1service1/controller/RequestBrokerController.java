package internship.task1service1.controller;

import internship.task1service1.model.CityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RequestBrokerController {
    private final Logger logger =  LoggerFactory.getLogger(RequestBrokerController.class);

    @GetMapping
    public CityModel[] makeRequest(@RequestParam String query){
        logger.info("http://localhost:8090?query=" + query);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8100?query=" + query.replace(' ', '+'), CityModel[].class);
    }
}
