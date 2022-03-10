package internship.task1service2.service;

import internship.task1service2.exceptions.DatabaseConnectionException;
import internship.task1service2.exceptions.SQLRequestException;
import internship.task1service2.model.CityModel;
import internship.task1service2.model.TableModel;
import internship.task1service2.repository.CityRepository;
import internship.task1service2.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final CityRepository cityRepository;
    private final DatabaseRepository databaseRepository;

    @Autowired
    public RequestService(CityRepository cityRepository, DatabaseRepository databaseRepository){
        this.cityRepository = cityRepository;
        this.databaseRepository = databaseRepository;
    }

    public List<TableModel> getDatabaseStructure() throws DatabaseConnectionException, SQLRequestException {
        return databaseRepository.getDatabaseStructure();
    }

    public List<CityModel> getCityArray(Optional<Integer> count){
        if(count.isPresent()){
            return cityRepository.getCityArray(count.get());
        }
        else{
            return cityRepository.findAll();
        }
    }

    @Transactional
    public CityModel getCityById(int id){
        Optional<CityModel> city = cityRepository.findById(id);
        if(city.isPresent()){
            return city.get();
        }
        else {
            return new CityModel("","", -1);
        }
    }
}
