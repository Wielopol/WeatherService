package pl.sda.weather.model;

import java.util.Objects;

public class LocationModel {
    private String id;
    //longitude and latitude according to geographical values ​​(latitude: -90 -> S, 90 -> N, longitude: -180 -> W, 180 -> E)
    private String longitudeAndLatitude;
    //city name - cannot be empty
    private String cityName;
    //region - optional: may be null
    private String region;
    //Country name - cannot be empty
    private String countryName;

    public LocationModel(String id, String longitudeAndLatitude, String cityName, String region, String countryName) {



        this.id = id;
        this.longitudeAndLatitude = longitudeAndLatitude;
        this.cityName = cityName;
        this.region = region;
        this.countryName = countryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitudeAndLatitude() {
        return longitudeAndLatitude;
    }

    public void setLongitudeAndLatitude(String longitudeAndLatitude) {
        this.longitudeAndLatitude = longitudeAndLatitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return  id + ","
                + longitudeAndLatitude + ","
                + cityName + ","
                + region + ","
                + countryName;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationModel that = (LocationModel) o;
        return Objects.equals(id, that.id) && Objects.equals(longitudeAndLatitude, that.longitudeAndLatitude) && Objects.equals(cityName, that.cityName) && Objects.equals(region, that.region) && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitudeAndLatitude, cityName, region, countryName);
    }
}
