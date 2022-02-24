package internship.task1service1.controller;

import internship.task1service1.httpClient.CityHttpClient;
import internship.task1service1.model.CityModel;
import internship.task1service1.service.RequestBrokerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RequestBrokerController {
    private RequestBrokerService requestBrokerService;

    @Autowired
    public RequestBrokerController(RequestBrokerService requestBrokerService){
        this.requestBrokerService = requestBrokerService;
    }

    @GetMapping("/city_model")
    public CityModel[] makeRequest(HttpServletRequest servletRequest, @RequestParam(defaultValue = "") String id){
        requestBrokerService.setClient(new CityHttpClient());
        String path = servletRequest.getRequestURI();
        if(!path.endsWith("/")){
            path = path.concat("/");
        }
        return requestBrokerService.getResponseData(path + id);
    }
}