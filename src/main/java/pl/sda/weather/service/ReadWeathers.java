package pl.sda.weather.service;

import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.model.WeatherLine;

import java.util.List;
import java.util.Map;

public interface ReadWeathers {

    Map<String,Weather> mapWeather();

    List<WeatherLine> readWeather();

    List<Weather> listWeathers(List<LocationModel> citiesList, Map<String,Weather> weatherMap);
}
