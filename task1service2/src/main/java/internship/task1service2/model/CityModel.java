package internship.task1service2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@ToString
@Table(name = "city")
public class CityModel {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "CountryCode")
    private String countryCode;

    @JsonIgnore
    @Column(name = "District")
    private String district;

    @Column(name = "Population")
    private int population;

    public CityModel(){}

    public CityModel(String name, String countryCode, int population) {
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof CityModel)){
            return false;
        }

        CityModel city = (CityModel) o;
        return this.name.equals(city.getName()) && this.countryCode.equals(city.getCountryCode()) && this.population == city.getPopulation();
    }
}