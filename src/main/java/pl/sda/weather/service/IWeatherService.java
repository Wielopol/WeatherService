package pl.sda.weather.service;

import pl.sda.weather.model.Weather;
import pl.sda.weather.model.entity.LocationModelEntity;

import java.util.List;

public interface IWeatherService {

    List<Weather> listWeathers(List<LocationModelEntity> citiesList) throws Exception;

}
