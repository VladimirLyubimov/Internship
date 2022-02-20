package internship.task1service2.controller;

import internship.task1service2.model.CityModel;
import internship.task1service2.repository.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DatabaseBrokerController {
    private final RequestService requestService;

    @Autowired
    public DatabaseBrokerController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public ArrayList<CityModel> getRequestResult(@RequestParam String query){
        ArrayList<CityModel> result;

        result = requestService.performQuery(query);

        return result;
    }
}
