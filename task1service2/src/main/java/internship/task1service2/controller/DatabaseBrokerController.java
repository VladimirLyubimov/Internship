package internship.task1service2.controller;

import internship.task1service2.model.CityModel;
import internship.task1service2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DatabaseBrokerController {

    private final RequestService requestService;

    @Autowired
    public DatabaseBrokerController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/city_model")
    public List<CityModel> getCityArray(@RequestParam Optional<Integer> count){
        return requestService.getCityArray(count);
    }

    @GetMapping("/city_model/{id}")
    public CityModel getCityById(@PathVariable int id){
        return requestService.getCityById(id);
    }

    @GetMapping("/schema")
    public void checkCon(){
        requestService.checkCon();
    }
}