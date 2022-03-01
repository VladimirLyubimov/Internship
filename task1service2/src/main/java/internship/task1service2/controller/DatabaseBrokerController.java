package internship.task1service2.controller;

import internship.task1service2.exceptions.DatabaseConnectionException;
import internship.task1service2.exceptions.SQLRequestException;
import internship.task1service2.model.CityModel;
import internship.task1service2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class DatabaseBrokerController {

    private final RequestService requestService;

    @Autowired
    public DatabaseBrokerController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/city_model")
    public ArrayList<CityModel> getCityArray() throws SQLRequestException, DatabaseConnectionException{
        ArrayList<CityModel> result;

        result = requestService.getCityArray();

        return result;
    }

    @GetMapping("/city_model/{id}")
    public CityModel getCityById(@PathVariable int id) throws SQLRequestException, DatabaseConnectionException {
        return requestService.getCityById(id);
    }
}