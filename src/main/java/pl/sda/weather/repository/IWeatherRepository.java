package pl.sda.weather.repository;

import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;

import java.util.List;

public interface IWeatherRepository {

    void saveWeather(WeatherModelEntity weatherModelEntity);

    void readWeather(LocationModelEntity city, int day);

    List<WeatherModelEntity> getAllWeatherModelData();

    void deleteRecord(WeatherModelEntity weatherModelEntity);
}
