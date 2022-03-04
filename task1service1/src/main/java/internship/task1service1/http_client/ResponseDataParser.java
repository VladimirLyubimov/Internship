package internship.task1service1.http_client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import internship.task1service1.error_response.ErrorResponse;
import internship.task1service1.model.CityModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDataParser {
    static public CityModel[] getCityArray(String responseBody){
        CityModel[] result;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(responseBody, CityModel[].class);
        }
        catch(Exception e){
            e.printStackTrace();
            return new CityModel[0];
        }

        return result;
    }

    static public CityModel getOneCity(String responseBody){
        CityModel result;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(responseBody, CityModel.class);
        }
        catch(Exception e){
            e.printStackTrace();
            return new CityModel("", "", -1);
        }

        return result;
    }

    static public ErrorResponse getErrorResponse(String responseBody){
        ErrorResponse result;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(responseBody, ErrorResponse.class);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ErrorResponse("Corrupted error response","Error response is corrupted");
        }

        return result;
    }
}