package internship.task1service1.service;

import internship.task1service1.controller.RequestBrokerController;
import internship.task1service1.exceptions.DatabaseConnectionException;
import internship.task1service1.exceptions.EmptyResultException;
import internship.task1service1.exceptions.FailConnectionException;
import internship.task1service1.exceptions.SQLRequestException;
import internship.task1service1.http_client.CityHttpClient;
import internship.task1service1.model.CityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestBrokerService {
    private final CityHttpClient cityHttpClient;

    @Autowired
    public RequestBrokerService(CityHttpClient cityHttpClient){
        this.cityHttpClient = cityHttpClient;
    }

    public CityModel[] getCityArray(String count) throws EmptyResultException, FailConnectionException, SQLRequestException, DatabaseConnectionException{
        Optional<CityModel[]> result = cityHttpClient.getCityArray(count);
        if (result.isPresent() && result.get().length != 0) {
            return result.get();
        }
        else{
            throw new EmptyResultException("Requested data hasn't been found");
        }
    }

    public CityModel getCityById(int id) throws EmptyResultException, FailConnectionException, SQLRequestException, DatabaseConnectionException {
        Optional<CityModel> result = cityHttpClient.getCityById(id);
        if (result.isPresent() && !result.get().isEmpty()) {
            return result.get();
        } else {
            throw new EmptyResultException("City with id = " + id + " hasn't been found");
        }
    }
}
