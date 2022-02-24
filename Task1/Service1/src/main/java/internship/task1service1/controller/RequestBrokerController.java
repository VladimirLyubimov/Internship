package internship.task1service1.controller;

import internship.task1service1.model.CityModel;
import internship.task1service1.service.RequestBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestBrokerController {
    private final RequestBrokerService requestBrokerService;


    @Autowired
    public RequestBrokerController(RequestBrokerService requestBrokerService){
        this.requestBrokerService = requestBrokerService;
    }

    @GetMapping("/city_model")
    public CityModel[] getCityArray(){
        return requestBrokerService.getCityArray();
    }

    @GetMapping("/city_model/{id}")
    public CityModel getCityById(@PathVariable int id){
        return requestBrokerService.getCityById(id);
    }
}