package internship.task1service1.http_client;

import feign.Response;
import feign.codec.ErrorDecoder;
import internship.task1service1.exceptions.FailConnectionException;

public class CityErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response){
        if(response.status() >= 400 && response.status() < 600){
            return new FailConnectionException("Can't connection to database service with code " + response.status());
        }

        return new FailConnectionException("Unknown error");
    }
}
