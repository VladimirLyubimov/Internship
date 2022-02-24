package internship.task1service2.service;

import internship.task1service2.model.CityModel;
import internship.task1service2.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RequestService {
    private CityRepository cityRepository;

    @Autowired
    public RequestService(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    public ArrayList<CityModel> getCityList(){
        String query = "select * from city limit 7;";
        return  cityRepository.performQuery(query);
    };

    public CityModel[] getCityById(int id){
        String query = "select * from city where id = " + Integer.toString(id);
        try {
            return new CityModel[] {cityRepository.performQuery(query).get(0)};
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("No city with this id exist!");
        }

        return new CityModel[] {new CityModel("", "", -1)};
    }
}
