package internship.task1service1.http_client;

import internship.task1service1.model.CityModel;
import org.springframework.stereotype.Component;

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

    public Optional<CityModel> getCityById(int id){
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path + id)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return  Optional.ofNullable(ResponseDataParser.getOneCity(response.body()));
        }
        catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
