package internship.task1service2.service;

import internship.task1service2.exceptions.SQLRequestException;
import internship.task1service2.model.CityModel;
import internship.task1service2.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RequestService {
    private final CityRepository cityRepository;

    @Autowired
    public RequestService(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    public ArrayList<CityModel> getCityArray(){
        return  cityRepository.getCityArray();
    };

    public CityModel getCityById(int id) throws SQLRequestException {
        Optional<CityModel> city = cityRepository.getCityById(id);
        if(city.isPresent()){
            return city.get();
        }
        else {
            throw new SQLRequestException("No city with id = " + id + " exists in database");
        }
    }
}
