package internship.task1service1.http_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import internship.task1service1.model.CityModel;

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
}