package internship.task1service2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import internship.task1service2.model.CityModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:test-properties.properties")
class DatabaseBrokerTest {
    private final static CityModel EMPTY_CITY = new CityModel("","", -1);
    private final static CityModel CITY_543 = new CityModel("Ruse", "BGR", 166467);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getCityById_IdNegative_EmptyCity(){
        ResponseEntity<String> actual = testRestTemplate.getForEntity("/city_model/-1", String.class);
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Assertions.assertEquals(cityToJson(EMPTY_CITY), actual.getBody());
    }

    @Test
    void getCityById_Id543_Correct(){
        ResponseEntity<String> actual = testRestTemplate.getForEntity("/city_model/543", String.class);
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Assertions.assertEquals(cityToJson(CITY_543), actual.getBody());
    }

    private String cityToJson(CityModel city){
        try{
            return mapper.writeValueAsString(city);
        }
        catch (JsonProcessingException e){
            return "";
        }
    }
}
