package internship.task1service1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import internship.task1service1.model.CityModel;

public class ResponseDataParser {
    static CityModel[] getAnswer(String responseBody){
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
}
