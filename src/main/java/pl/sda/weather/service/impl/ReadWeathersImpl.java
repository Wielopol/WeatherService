package pl.sda.weather.service.impl;

import com.google.gson.Gson;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.model.WeatherLine;
import pl.sda.weather.service.ReadWeathers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class ReadWeathersImpl implements ReadWeathers {

    @Override
    public Map<String, Weather> mapWeather() {
        List<WeatherLine> weatherLines = readWeather();
        Map<String,Weather> weatherMap = new HashMap<>();
        for (WeatherLine w : weatherLines) {
            Weather weather = new Weather(w.getMainWeather().getTemp(), w.getMainWeather().getPressure(),
                    w.getMainWeather().getHumidity(), w.getWind().getDeg(), w.getWind().getSpeed(), w.getCity().getName());
            weatherMap.put(w.getCity().getName(),weather);
        }
        return weatherMap;
    }

    @Override
    public List<WeatherLine> readWeather() {
        String FILE_PATH = "location/weather.json";
        Gson gson = new Gson();

        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(FILE_PATH).toURI()))) {
            return stream.map(s -> gson.fromJson(s, WeatherLine.class)).toList();
        } catch (IOException | URISyntaxException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
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
