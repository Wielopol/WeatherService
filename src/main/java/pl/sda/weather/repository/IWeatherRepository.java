package pl.sda.weather.repository;

import pl.sda.weather.model.Weather;

public interface IWeatherRepository {

    Weather readWeather(String city);
}
