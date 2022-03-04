package internship.task1service2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
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

    public String getName() {
        return this.name;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public int getPopulation() {
        return this.population;
    }

    public int getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

}