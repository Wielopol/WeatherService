package pl.sda.weather.repository.impl;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import pl.sda.weather.model.Weather;
import pl.sda.weather.model.WeatherApi;
import pl.sda.weather.repository.IWeatherRepository;

import java.io.IOException;

public class WeatherRepositoryImpl implements IWeatherRepository {

    private static final OkHttpClient client = new OkHttpClient();

    @Override
    public Weather readWeather(String city) {
        String API_KEY = "9a3c76de90c7d386f069ff3666d4cc7c";
        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q="+ city + "&appid="+ API_KEY + "&units=metric")
                .get()
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response.message());
            }
            String json = response.body().string();
            WeatherApi w = gson.fromJson(json,WeatherApi.class);
            return new Weather(w.getMainWeather().getTemp(), w.getMainWeather().getPressure(),
                    w.getMainWeather().getHumidity(), w.getWind().getDeg(), w.getWind().getSpeed(), w.getName());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.body().close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }

            }
        }
        return null;
    }

}
