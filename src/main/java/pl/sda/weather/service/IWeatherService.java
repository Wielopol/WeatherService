package pl.sda.weather.service;

import pl.sda.weather.model.Coords;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;

import java.util.List;

public interface IWeatherService {

    boolean doesWeatherExistForLocation(LocationModelEntity model);

    void listOneWeather(LocationModelEntity location, int day);

    void listWeathers(List<LocationModelEntity> citiesList, int day) throws Exception;

    WeatherModelEntity readWeather(LocationModelEntity city, int day);

    Coords getCoordFromCity(LocationModelEntity city);

    Coords getAccurateCoords(List<Coords> coords, LocationModelEntity city, int limit);

    void cleanRecords();

    List<WeatherModelEntity> getAllWeathers();

    WeatherModelEntity getWeatherByLocation(LocationModelEntity location);
}
