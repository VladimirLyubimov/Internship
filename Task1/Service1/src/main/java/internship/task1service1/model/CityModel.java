package internship.task1service1.model;

public class CityModel {
    private String name;
    private String country_code;
    private int population;

    public CityModel(){}

    public String getName() {
        return this.name;
    }

    public String getCountry_code() {
        return this.country_code;
    }

    public int getPopulation() {
        return this.population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}