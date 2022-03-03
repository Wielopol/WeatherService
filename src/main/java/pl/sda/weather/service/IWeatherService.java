package pl.sda.weather.service;

import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;

import java.util.List;

public interface IWeatherService {

    List<Weather> listWeathers(List<LocationModel> citiesList) throws Exception;

}
