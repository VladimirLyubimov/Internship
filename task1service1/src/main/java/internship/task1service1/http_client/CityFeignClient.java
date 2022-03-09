package internship.task1service1.http_client;

import internship.task1service1.model.CityModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="database-client")
public interface CityFeignClient {
    @GetMapping("/city_model/{id}")
    public CityModel getCityById( @PathVariable("id") int id);

    @GetMapping("/city_model?count={count}")
    public CityModel[] getCityArray(@PathVariable(value = "count", required = false) Integer count);

    @GetMapping("/city_model?count=")
    public CityModel[] getAllCities();

    @GetMapping("/schema")
    public void getDatabaseSchema();
}
