package internship.task1service1.http_client;

import internship.task1service1.exceptions.DatabaseConnectionException;
import internship.task1service1.exceptions.FailConnectionException;
import internship.task1service1.exceptions.SQLRequestException;
import internship.task1service1.model.CityModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Component
public class CityHttpClient{

    //TODO Потоконебезопасно.
    private HttpClient client;

    //TODO статические переменные принято называть В_ТАКОМ_СТИЛЕ
    private static  final String path = "http://localhost:8100/city_model/";

    public CityHttpClient(){
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }

    public Optional<CityModel[]> getCityArray() throws FailConnectionException, SQLRequestException, DatabaseConnectionException{
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseErrorChecker(response);
            return Optional.ofNullable(ResponseDataParser.getCityArray(response.body()));
        }
        //TODO лучше используй || и &&. Одинарные | и & используются для побитовых сравнений и при сравнении чисел дают число
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new FailConnectionException("Fail to connect to " + path);
        }
    }

    public Optional<CityModel> getCityById(int id) throws FailConnectionException, SQLRequestException, DatabaseConnectionException {
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path + id)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseErrorChecker(response);
            return Optional.ofNullable(ResponseDataParser.getOneCity(response.body()));
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new FailConnectionException("Fail to connect to " + path + id);
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
}
