package internship.task1service2.repository;

import internship.task1service2.model.CityModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityModel, Integer> {
    @Query(value = "select * from city limit :count", nativeQuery = true)
    List<CityModel> getCityArray(int count);
}