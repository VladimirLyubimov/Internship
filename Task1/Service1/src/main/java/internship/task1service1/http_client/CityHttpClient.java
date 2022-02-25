package internship.task1service1.http_client;

import internship.task1service1.exceptions.EmptyResultException;
import internship.task1service1.exceptions.FailConnectionException;
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
    private HttpClient client;
    private static  final String path = "http://localhost:8100/city_model/";

    public CityHttpClient(){
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }

    public Optional<CityModel[]> getCityArray(){
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.ofNullable(ResponseDataParser.getCityArray(response.body()));
        }
        catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<CityModel> getCityById(int id) throws EmptyResultException, FailConnectionException {
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path + id)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 404){
                throw new EmptyResultException(ResponseDataParser.getErrorResponse(response.body()).getErrorDescriptionMessage());
            }
            return  Optional.ofNullable(ResponseDataParser.getOneCity(response.body()));
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new FailConnectionException("Fail to connect to " + path + id);
        }
    }
}
