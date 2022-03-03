package pl.sda.weather.repository.impl;

import com.google.gson.Gson;
import pl.sda.weather.model.WeatherLine;
import pl.sda.weather.repository.IReadWeatherRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ReadWeatherRepositoryImpl implements IReadWeatherRepository {

    String FILE_PATH = "src/main/resources/location/weather.json";

    private static final Gson gson = new Gson();

    @Override

    public List<WeatherLine> readWeather() {

        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {
            return stream.map(s -> gson.fromJson(s, WeatherLine.class)).toList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }


}
