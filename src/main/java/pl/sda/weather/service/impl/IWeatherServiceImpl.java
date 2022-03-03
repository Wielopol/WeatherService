package pl.sda.weather.service.impl;

import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.repository.IWeatherRepository;
import pl.sda.weather.repository.impl.WeatherRepositoryImpl;
import pl.sda.weather.service.IWeatherService;

import java.util.LinkedList;
import java.util.List;

public class IWeatherServiceImpl implements IWeatherService {

    private static IWeatherRepository readWeatherRepository = new WeatherRepositoryImpl();

    @Override
    public List<Weather> listWeathers(List<LocationModel> citiesList) {
        List<Weather> weatherList = new LinkedList<>();
        for (LocationModel city : citiesList) {
            weatherList.add(readWeatherRepository.readWeather(city.getCityName()));
        }
        return weatherList;
    }

    public static void main(String[] args) {

    }



}