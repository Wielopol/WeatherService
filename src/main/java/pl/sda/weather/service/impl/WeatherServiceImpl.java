package pl.sda.weather.service.impl;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.sda.weather.model.Coords;
import pl.sda.weather.model.WeatherApi;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.properties.PropertiesReader;
import pl.sda.weather.repository.IWeatherRepository;
import pl.sda.weather.repository.impl.WeatherRepositoryImpl;
import pl.sda.weather.service.IWeatherService;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class WeatherServiceImpl implements IWeatherService {

    private static final OkHttpClient client = new OkHttpClient();
    private final Logger logger = LogManager.getLogger(WeatherServiceImpl.class);

    private Properties properties;

    public WeatherServiceImpl() {

        PropertiesReader propertiesReader = new PropertiesReader();
        this.properties = propertiesReader.loadFromFile("api.properties");
    }

    IWeatherRepository readWeatherRepository = new WeatherRepositoryImpl();

    @Override
    public boolean doesWeatherExistForLocation(LocationModelEntity model) {
        List<WeatherModelEntity> list = readWeatherRepository.getAllWeatherModelData();
        for (WeatherModelEntity weather : list) {
            if (weather.getLocation().getLocation_id().equals(model.getLocation_id())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void listOneWeather(LocationModelEntity location, int day) {
        WeatherModelEntity weather = readWeather(location, day);
        if (doesWeatherExistForLocation(location)) {
            readWeatherRepository.deleteRecord(getWeatherByLocationId(location));
        }
        readWeatherRepository.saveWeather(weather);
    }

    @Override
    public void listWeathers(List<LocationModelEntity> citiesList, int day) {
        cleanRecords();
        for (LocationModelEntity city : citiesList) {
            WeatherModelEntity weather = readWeather(city, day);
            readWeatherRepository.saveWeather(weather);
        }
    }

    @Override
    public WeatherModelEntity readWeather(LocationModelEntity city, int day) {
        Coords coords = getCoordFromCity(city);

        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/onecall?lat="+ coords.getLat() + "&lon=" + coords.getLon() +
                        "&exclude=current,minutely,hourly,alerts&appid="+ properties.getProperty("API_KEY") + "&units=metric")
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
            WeatherModelEntity weatherModel = new WeatherModelEntity();
            weatherModel.setTemperature(w.getDaily()[day].getTemp().getDay());
            weatherModel.setPressure(w.getDaily()[day].getPressure());
            weatherModel.setHumidity(w.getDaily()[day].getHumidity());
            weatherModel.setWindDir(w.getDaily()[day].getWind_deg());
            weatherModel.setWindSpeed(w.getDaily()[day].getWind_speed());
            weatherModel.setDate(Date.valueOf(LocalDate.ofInstant(Instant.ofEpochSecond(w.getDaily()[day].getDt()), ZoneId.of("CET"))));
            weatherModel.setLocation(city);
            return weatherModel;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (response != null) {
                try {
                    response.body().close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }

            }
        }
        return null;
    }

    @Override
    public Coords getCoordFromCity(LocationModelEntity city) {
        int length = 0;
        List<Coords> coords = new LinkedList<>();

        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/geo/1.0/direct?q="+ city.getCityName() + "&limit=5&appid="+ properties.getProperty("API_KEY"))
                .get()
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response.message());
            }
            String jsonText = response.body().string();
            JSONArray jsonArray = new JSONArray(jsonText);
            length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                coords.add(gson.fromJson(json.toString(),Coords.class));
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (response != null) {
                try {
                    response.body().close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return getAccurateCoords(coords, city, length);
    }

    @Override
    public Coords getAccurateCoords(List<Coords> coords, LocationModelEntity city, int limit) {
        HashMap<Double, Coords> coordsMap = new HashMap<>();
        Double deviation = 0d;
        Double minDev = 1000d;

        for (int i = 0; i < limit; i++) {
            String[] cityCoords = city.getLatitudeAndLongitude().split(",");
            deviation = Math.abs(coords.get(i).getLat() - Double.parseDouble(cityCoords[0])) + Math.abs(coords.get(i).getLon() - Double.parseDouble(cityCoords[1]));
            coordsMap.put(deviation, coords.get(i));
            if (deviation < minDev) {
                minDev = deviation;
            }
        }

        return coordsMap.get(minDev);
    }

    @Override
    public void cleanRecords() {
        List<WeatherModelEntity> listWeather = readWeatherRepository.getAllWeatherModelData();

        for (WeatherModelEntity weather : listWeather){
            this.readWeatherRepository.deleteRecord(weather);
        }
    }

    @Override
    public List<WeatherModelEntity> getAllWeathers() {
        return this.readWeatherRepository.getAllWeatherModelData();
    }

    @Override
    public WeatherModelEntity getWeatherByLocationId(LocationModelEntity location) {
        return this.readWeatherRepository.getWeatherModelDataByLocationId(location);
    }
}
