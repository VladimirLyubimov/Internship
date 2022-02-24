package internship.task1service1.service;

import internship.task1service1.controller.ResponseDataParser;
import internship.task1service1.httpClient.MyHttpClient;
import internship.task1service1.model.CityModel;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class RequestBrokerService {
    private MyHttpClient myHttpClient;

    public RequestBrokerService(){}

    public void setClient(MyHttpClient myHttpClient){
        this.myHttpClient = myHttpClient;
    }

    public CityModel[] getResponseData(String path){
        Optional<HttpResponse<String>> response = myHttpClient.makeRequest(path);
        if(response.isPresent()){
            return ResponseDataParser.getAnswer(response.get().body());
        }
        else{
            return new CityModel[0];
        }
    }
}
