package internship.task1service2.controller;

import internship.task1service2.model.CityModel;
import internship.task1service2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DatabaseBrokerController {

    private final RequestService requestService;

    @Autowired
    public DatabaseBrokerController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/city_model")
    public ArrayList<CityModel> getRequestResult(){
        ArrayList<CityModel> result;

        result = requestService.getCityList();

        return result;
    }

    @GetMapping("/city_model/{number}")
    public CityModel[] getRequestResult(@PathVariable int number){
        CityModel[] result;

        result = requestService.getCityById(number);

        return result;
    }
}