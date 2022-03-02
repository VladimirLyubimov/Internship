package internship.task1service2.service;

import internship.task1service2.exceptions.DatabaseConnectionException;
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

    // TODO рекомендуется использоватьо более широкий тип (Array)
    public ArrayList<CityModel> getCityArray() throws SQLRequestException, DatabaseConnectionException{
        return  cityRepository.getCityArray();
    }

    //TODO
    public CityModel getCityById(int id) throws SQLRequestException, DatabaseConnectionException {
        Optional<CityModel> city = cityRepository.getCityById(id);
        if(city.isPresent()){
            return city.get();
        }
        else {
            return new CityModel("","", -1);
        }
    }
}
