package pl.sda.weather.service.impl;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.WeatherLine;
import pl.sda.weather.repository.IReadWeatherRepository;
import pl.sda.weather.repository.impl.ReadWeatherRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IReadWeathersServiceImplTest {

    IReadWeatherRepository readWeathers = new ReadWeatherRepositoryImpl();

    @Test
    void readWeather() {

        List<WeatherLine> expected = new ArrayList<>();

        List<WeatherLine> result = readWeathers.readWeather();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void mapWeather() {
    }

    @Test
    void listWeathers() {
    }
}