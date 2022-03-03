package internship.task1service1.http_client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import internship.task1service1.exceptions.DatabaseConnectionException;
import internship.task1service1.exceptions.FailConnectionException;
import internship.task1service1.exceptions.SQLRequestException;
import internship.task1service1.model.CityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Component
public class CityHttpClient{
    private static final Logger LOGGER =  LoggerFactory.getLogger(CityHttpClient.class.getName());
    private final EurekaClient eurekaClient;

    private static final String DATABASE_SERVICE_NAME = "database-client";

    @Autowired
    public CityHttpClient(EurekaClient eurekaClient){
        this.eurekaClient = eurekaClient;
    }

    public Optional<CityModel[]> getCityArray() throws FailConnectionException, SQLRequestException, DatabaseConnectionException{
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        String path = getDatabaseServicePath("/city_model/");
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info("Successfully connected to {}", path);
            responseErrorChecker(response);
            return Optional.ofNullable(ResponseDataParser.getCityArray(response.body()));
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.info("Fail to connect to {}", path);
            throw new FailConnectionException("Fail to connect to " + path);
        }
    }

    public Optional<CityModel> getCityById(int id) throws FailConnectionException, SQLRequestException, DatabaseConnectionException {
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        String path = getDatabaseServicePath("/city_model/"+id);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info("Successfully connected to {}", path);
            responseErrorChecker(response);
            return Optional.ofNullable(ResponseDataParser.getOneCity(response.body()));
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.info("Fail to connect to {}", path);
            throw new FailConnectionException("Fail to connect to " + path);
        }
    }

    private void responseErrorChecker(HttpResponse<String> response) throws  SQLRequestException, DatabaseConnectionException{
        if(response.statusCode() == 404){
            throw new SQLRequestException(ResponseDataParser.getErrorResponse(response.body()).getErrorDescriptionMessage());
        }
        if(response.statusCode() == 503){
            throw new DatabaseConnectionException(ResponseDataParser.getErrorResponse(response.body()).getErrorDescriptionMessage());
        }
    }

    private String getDatabaseServicePath(String pathEnd){
        StringBuilder pathMaker = new StringBuilder("http://");
        InstanceInfo databaseServiceInfo = eurekaClient.getApplication(DATABASE_SERVICE_NAME).getInstances().get(0);
        pathMaker.append(databaseServiceInfo.getHostName());
        pathMaker.append(':');
        pathMaker.append(databaseServiceInfo.getPort());
        pathMaker.append(pathEnd);
        return  pathMaker.toString();
    }
}
