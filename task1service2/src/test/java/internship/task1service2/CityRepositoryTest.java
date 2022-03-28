package internship.task1service2;

import internship.task1service2.model.CityModel;
import internship.task1service2.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CityRepositoryTest {
    @Autowired
    private CityRepository cityRepository;

    @Test
    void getCityArray_OneCity_Correct(){
        CityModel city = CityModel.builder()
                .name("Kabul")
                .countryCode("AFG")
                .population(1780000)
                .build();

        List<CityModel> expected = new ArrayList<>();
        expected.add(city);

        List<CityModel> actual = cityRepository.getCityArray(1);

        Assertions.assertEquals(expected, actual);
    }
}
