package internship.task1service1.service;

import internship.task1service1.exceptions.*;
import internship.task1service1.http_client.CityFeignClient;
import internship.task1service1.model.CityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestBrokerService {
    private static final Logger LOGGER =  LoggerFactory.getLogger(RequestBrokerService.class.getName());
    private final CityFeignClient cityFeignClient;


    @Autowired
    public RequestBrokerService(CityFeignClient cityFeignClient){
        this.cityFeignClient = cityFeignClient;
    }

    public CityModel[] getCityArray(Optional<Integer> count) throws EmptyResultException, FailConnectionException, ObviouslyIncorrectInputDataException, SQLRequestException, DatabaseConnectionException{
        if(count.isPresent() && count.get() < 0){
            throw new ObviouslyIncorrectInputDataException("Input amount of cities = " + count.get() + ". It is impossible to get negative amount of cities");
        }
        Optional<CityModel[]> result;
        try {
            if(count.isPresent()) {
                result = Optional.ofNullable(cityFeignClient.getCityArray(count.get()));
            }
            else{
                result = Optional.ofNullable(cityFeignClient.getAllCities());
            }
            LOGGER.info("Successfully connected to database service");
        }
        catch (Exception e){
            LOGGER.info("Fail to connect to database service");
            throw e;
        }

        if (result.isPresent() && result.get().length != 0) {
            return result.get();
        }
        else{
            throw new EmptyResultException("Requested data hasn't been found");
        }
    }

    public CityModel getCityById(int id) throws EmptyResultException, FailConnectionException, SQLRequestException, DatabaseConnectionException {
        Optional<CityModel> result;
        try {
            result = Optional.ofNullable(cityFeignClient.getCityById(id));
            LOGGER.info("Successfully connected to database service");
        }
        catch (Exception e){
            LOGGER.info("Fail to connect to database service");
            throw e;
        }

        if (result.isPresent() && !result.get().isEmpty()) {
            return result.get();
        } else {
            throw new EmptyResultException("City with id = " + id + " hasn't been found");
        }
    }
}
