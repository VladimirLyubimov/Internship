package internship.task1service1.service;

import internship.task1service1.controller.RequestBrokerController;
import internship.task1service1.exceptions.EmptyResultException;
import internship.task1service1.exceptions.FailConnectionException;
import internship.task1service1.http_client.CityHttpClient;
import internship.task1service1.model.CityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestBrokerService {
    private static  final String path = "http://localhost:8100/city_model/";
    private final Logger logger =  LoggerFactory.getLogger(RequestBrokerController.class);
    private final CityHttpClient cityHttpClient;

    @Autowired
    public RequestBrokerService(CityHttpClient cityHttpClient){
        this.cityHttpClient = cityHttpClient;
    }

    public Optional<CityModel[]> getCityArray(){
        Optional<CityModel[]> result = cityHttpClient.getCityArray();
        if(result.isPresent()){
            logger.info("Successful request to " + path);
        }
        else{
            logger.info("Fail to make correct request to " + path);
        }

        return result;
    }

    public CityModel getCityById(int id) throws EmptyResultException, FailConnectionException {
        try {
            Optional<CityModel> result = cityHttpClient.getCityById(id);
            if (result.isPresent()) {
                logger.info("Successful request to " + path + id);
                return result.get();
            } else {
                return new CityModel("", "", -1);
            }
        }
        catch(EmptyResultException e){
            logger.info("Successfully connected to " + path + id + ", but didn't get data");
            throw e;
        }
        catch(FailConnectionException e){
            logger.info("Fail to make correct request to " + path + id);
            throw e;
        }
    }
}
