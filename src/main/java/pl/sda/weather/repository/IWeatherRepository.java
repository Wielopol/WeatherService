package pl.sda.weather.repository;

import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;

import java.util.List;

public interface IWeatherRepository {

    void saveWeather(WeatherModelEntity weatherModelEntity);

    List<WeatherModelEntity> getAllWeatherModelData();

    WeatherModelEntity getWeatherModelDataByLocationId(LocationModelEntity location);

    void deleteRecord(WeatherModelEntity weatherModelEntity);
}
