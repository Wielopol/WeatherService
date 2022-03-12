package pl.sda.weather.service;

import pl.sda.weather.model.Weather;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;

import java.util.List;

public interface IWeatherService {

    void listWeathers(List<LocationModelEntity> citiesList, int day) throws Exception;

    void cleanRecords();

    List<WeatherModelEntity> getAllLocation();

    WeatherModelEntity getWeatherByLocationId(LocationModelEntity location);
}
