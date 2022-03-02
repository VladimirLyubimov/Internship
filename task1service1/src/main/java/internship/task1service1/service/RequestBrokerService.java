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
    private static  final String path = "http://localhost:8100/city_model/";
    private final Logger logger =  LoggerFactory.getLogger(RequestBrokerController.class);
    private final CityHttpClient cityHttpClient;

    @Autowired
    public RequestBrokerService(CityHttpClient cityHttpClient){
        this.cityHttpClient = cityHttpClient;
    }

    public CityModel[] getCityArray() throws EmptyResultException, FailConnectionException, SQLRequestException, DatabaseConnectionException{
        try {
            Optional<CityModel[]> result = cityHttpClient.getCityArray();
            if (result.isPresent() && result.get().length != 0) {
                logger.info("Successfully connected to " + path);
                return result.get();
            }
            else{
                throw new EmptyResultException("Requested data hasn't been found");
            }
        }
        catch (EmptyResultException | SQLRequestException | DatabaseConnectionException e){
            //TODO в логгерах лучше не использовать конкатенацию
            //TODO погугли, как лучше
            logger.info("Successfully connected to " + path + ", but didn't get data");
            throw e;
        }

        catch(FailConnectionException e){
            logger.info("Fail to make correct request to " + path);
            throw  e;
        }
    }

    public CityModel getCityById(int id) throws EmptyResultException, FailConnectionException, SQLRequestException, DatabaseConnectionException {
        try {
            Optional<CityModel> result = cityHttpClient.getCityById(id);
            if (result.isPresent() && !result.get().isEmpty()) {
                logger.info("Successfully connected to " + path + id);
                return result.get();
            } else {
                throw new EmptyResultException("City with id = " + id + " hasn't been found");
            }
        }
        catch(EmptyResultException | SQLRequestException | DatabaseConnectionException e){
            logger.info("Successfully connected to " + path + id + ", but didn't get data");
            throw e;
        }
        catch(FailConnectionException e){
            logger.info("Fail to make correct request to " + path + id);
            throw e;
        }
    }
}
