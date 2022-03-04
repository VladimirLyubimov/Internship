package internship.task1service2.service;

import internship.task1service2.exceptions.DatabaseConnectionException;
import internship.task1service2.exceptions.SQLRequestException;
import internship.task1service2.model.CityModel;
import internship.task1service2.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class RequestService {
    private final CityRepository cityRepository;

    @Autowired
    public RequestService(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    public List<CityModel> getCityArray(String count) throws SQLRequestException, DatabaseConnectionException{
        try{
            int num = parseInt(count);
            return cityRepository.getCityArray(num);
        }
        catch (NumberFormatException e){
            return (List<CityModel>) cityRepository.findAll();
        }
    }

    @Transactional
    public CityModel getCityById(int id) throws SQLRequestException, DatabaseConnectionException {
        Optional<CityModel> city = cityRepository.findById(id);
        if(city.isPresent()){
            return city.get();
        }
        else {
            return new CityModel("","", -1);
        }
    }
}
