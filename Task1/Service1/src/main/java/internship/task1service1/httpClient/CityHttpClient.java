package internship.task1service1.httpClient;

import internship.task1service1.model.CityModel;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Component
public class CityHttpClient implements MyHttpClient{
    private HttpClient client;

    public CityHttpClient(){
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }

    @Override
    public Optional<HttpResponse<String>> makeRequest(String path){
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8100" + path)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(response);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Optional.ofNullable(null);
        }
    }
}
