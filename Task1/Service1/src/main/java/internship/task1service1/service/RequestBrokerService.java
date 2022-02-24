package internship.task1service1.service;

import internship.task1service1.controller.RequestBrokerController;
import internship.task1service1.controller.ResponseDataParser;
import internship.task1service1.httpClient.MyHttpClient;
import internship.task1service1.model.CityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class RequestBrokerService {
    private final Logger logger =  LoggerFactory.getLogger(RequestBrokerController.class);
    private MyHttpClient myHttpClient;

    public RequestBrokerService(){}

    public void setClient(MyHttpClient myHttpClient){
        this.myHttpClient = myHttpClient;
    }

    public CityModel[] getResponseData(String path){
        Optional<HttpResponse<String>> response = myHttpClient.makeRequest(path);
        if(response.isPresent()){
            logger.info("Successful request to http://localhost:8100" + path);
            return ResponseDataParser.getAnswer(response.get().body());
        }
        else{
            logger.info("Fail to make correct request to http://localhost:8100" + path);
            return new CityModel[0];
        }
    }
}
