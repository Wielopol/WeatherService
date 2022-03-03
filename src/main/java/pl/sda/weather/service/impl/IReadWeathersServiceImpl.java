package pl.sda.weather.service.impl;

import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.model.WeatherLine;
import pl.sda.weather.repository.IReadWeatherRepository;
import pl.sda.weather.repository.impl.ReadWeatherRepositoryImpl;
import pl.sda.weather.service.IReadWeathersService;


import java.util.*;

public class IReadWeathersServiceImpl implements IReadWeathersService {

    IReadWeatherRepository weatherRepository = new ReadWeatherRepositoryImpl();

    @Override
    public Map<String, Weather> getWeatherMap() {
        List<WeatherLine> weatherLines = weatherRepository.readWeather();
        Map<String,Weather> weatherMap = new HashMap<>();
        for (WeatherLine w : weatherLines) {
            Weather weather = new Weather(w.getMainWeather().getTemp(), w.getMainWeather().getPressure(),
                    w.getMainWeather().getHumidity(), w.getWind().getDeg(), w.getWind().getSpeed(), w.getCity().getName());
            weatherMap.put(w.getCity().getName(),weather);
        }
        return weatherMap;
    }



    @Override
    public List<Weather> listWeathers(List<LocationModel> citiesList, Map<String,Weather> weatherMap) {
        List<Weather> weatherList = new LinkedList<>();
        for (LocationModel city : citiesList) {
            weatherList.add(weatherMap.get(city.getCityName()));
        }
        return weatherList;
    }
}
