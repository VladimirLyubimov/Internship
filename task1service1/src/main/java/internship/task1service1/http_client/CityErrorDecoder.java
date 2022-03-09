package internship.task1service1.http_client;

import feign.Response;
import feign.codec.ErrorDecoder;
import internship.task1service1.error_response.ErrorResponse;
import internship.task1service1.exceptions.BadErrorResponseException;
import internship.task1service1.exceptions.ClientSideErrorException;
import internship.task1service1.exceptions.FailConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CityErrorDecoder implements ErrorDecoder {
    private static final Logger LOGGER =  LoggerFactory.getLogger(CityErrorDecoder.class.getName());

    @Override
    public Exception decode(String methodKey, Response response){
        try (InputStreamReader responseBodyReader = new InputStreamReader(response.body().asInputStream())){
            StringBuilder errorResponseMessageBuilder = new StringBuilder();
            int ch;
            while((ch = responseBodyReader.read()) != -1){
                errorResponseMessageBuilder.append((char)ch);
            }
            String errorResponseMessage = errorResponseMessageBuilder.toString();
            if (response.status() >= 400 && response.status() < 500) {
                LOGGER.info("Can't connect to database service due to error with code " + response.status());
                return new ClientSideErrorException(new ErrorResponse("Client side error with code " + response.status(), errorResponseMessage, response.status()));
            }
            if (response.status() >= 500 && response.status() < 600) {
                LOGGER.info("Can't connect to database service due to error with code " + response.status());
                return new FailConnectionException(new ErrorResponse("Server side error with code " + response.status(), errorResponseMessage, response.status()));
            }
        }
        catch (IOException e){
            return new BadErrorResponseException("Can't correctly handle error response");
        }

        return new Default().decode(methodKey, response);
    }
}


