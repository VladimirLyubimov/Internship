package internship.task1service1.service;

import feign.FeignException;
import internship.task1service1.error_response.ErrorResponse;
import internship.task1service1.exceptions.EmptyResultException;
import internship.task1service1.exceptions.FailConnectionException;
import internship.task1service1.exceptions.ObviouslyIncorrectInputDataException;
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

    public CityModel[] getCityArray(Optional<Integer> count) throws EmptyResultException, ObviouslyIncorrectInputDataException, FailConnectionException{
        if(count.isPresent() && count.get() < 0){
            LOGGER.info("Can't get negative amount of cities");
            throw new ObviouslyIncorrectInputDataException("Input amount of cities = " + count.get() + ". It is impossible to get negative amount of cities");
        }
        Optional<CityModel[]> result;
        try {
            if (count.isPresent()) {
                result = Optional.ofNullable(cityFeignClient.getCityArray(count.get()));
            } else {
                result = Optional.ofNullable(cityFeignClient.getAllCities());
            }
        }
        catch (FeignException e){
            LOGGER.info("Can't connect to database service");
            throw new FailConnectionException(new ErrorResponse("Connection error", e.getMessage(), 503));
        }
        LOGGER.info("Successfully connected to database service");

        if (result.isPresent() && result.get().length != 0) {
            return result.get();
        }
        else{
            throw new EmptyResultException("Requested data hasn't been found");
        }
    }

    public CityModel getCityById(int id) throws EmptyResultException, FailConnectionException{
        Optional<CityModel> result;
        try {
            result = Optional.ofNullable(cityFeignClient.getCityById(id));
        }
        catch (FeignException e){
            LOGGER.info("Can't connect to database service");
            throw new FailConnectionException(new ErrorResponse("Connection error", e.getMessage(), 503));
        }
        LOGGER.info("Successfully connected to database service");

        if (result.isPresent() && !result.get().isEmpty()) {
            return result.get();
        } else {
            throw new EmptyResultException("City with id = " + id + " hasn't been found");
        }
    }

    public void getDatabaseSchema(){
        cityFeignClient.getDatabaseSchema();
        LOGGER.info("Successfully get database schema");
    }
}
