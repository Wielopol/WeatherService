package pl.sda.weather.service;

import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;

import java.util.List;

public interface IWeatherService {

    void listOneWeather(LocationModelEntity location, int day);

    void listWeathers(List<LocationModelEntity> citiesList, int day) throws Exception;

    void cleanRecords();

    List<WeatherModelEntity> getAllWeathers();

    WeatherModelEntity getWeatherByLocationId(LocationModelEntity location);
}
