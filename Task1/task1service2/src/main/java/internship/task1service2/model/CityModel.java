package internship.task1service2.model;

public class CityModel {
    private String name;
    private String countryCode;
    private int population;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}