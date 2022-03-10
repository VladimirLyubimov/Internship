package internship.task1service1.controller;

import internship.task1service1.exceptions.*;
import internship.task1service1.model.CityModel;
import internship.task1service1.model.TableModel;
import internship.task1service1.service.RequestBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RequestBrokerController {
    private final RequestBrokerService requestBrokerService;

    @Autowired
    public RequestBrokerController(RequestBrokerService requestBrokerService){
        this.requestBrokerService = requestBrokerService;
    }

    @GetMapping("/city_model")
    public CityModel[] getCityArray(@RequestParam("count") Optional<Integer> count) throws EmptyResultException, FailConnectionException, ObviouslyIncorrectInputDataException{
        return requestBrokerService.getCityArray(count);
    }

    @GetMapping("/city_model/{id}")
    public CityModel getCityById(@PathVariable int id) throws EmptyResultException, FailConnectionException{
        return requestBrokerService.getCityById(id);
    }

    @GetMapping("/schema")
    public TableModel[] getDatabaseSchema()  throws EmptyResultException, FailConnectionException {
        return requestBrokerService.getDatabaseSchema();
    }
}