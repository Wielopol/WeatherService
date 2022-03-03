package pl.sda.weather.repository;

import pl.sda.weather.model.WeatherLine;

import java.util.List;

public interface IReadWeatherRepository {
    List<WeatherLine> readWeather();
}
