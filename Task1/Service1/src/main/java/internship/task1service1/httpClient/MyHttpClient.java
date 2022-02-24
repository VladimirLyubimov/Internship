package internship.task1service1.httpClient;

import java.net.http.HttpResponse;

import java.util.Optional;

public interface MyHttpClient {
    public Optional<HttpResponse<String>> makeRequest(String path);
}
