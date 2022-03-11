package pl.sda.weather.repository.impl;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.sda.weather.connection.HibernateUtil;
import pl.sda.weather.model.Coords;
import pl.sda.weather.model.Weather;
import pl.sda.weather.model.WeatherApi;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.properties.PropertiesReader;
import pl.sda.weather.repository.IWeatherRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class WeatherRepositoryImpl implements IWeatherRepository {

    private static final OkHttpClient client = new OkHttpClient();
    private final Logger logger = LogManager.getLogger(LocationRepositoryDbImpl.class);

    private Properties properties;

    public WeatherRepositoryImpl() {

        PropertiesReader propertiesReader = new PropertiesReader();
        this.properties = propertiesReader.loadFromFile("api.properties");
    }

    @Override
    public void saveWeather(WeatherModelEntity weatherModelEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(weatherModelEntity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void readWeather(LocationModelEntity city, int day) {
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
            weatherModel.setTemperature(w.getDaily()[day].getTemp().getDayTemp());
            weatherModel.setPressure(w.getDaily()[day].getPressure());
            weatherModel.setHumidity(w.getDaily()[day].getHumidity());
            weatherModel.setWindDir(w.getDaily()[day].getWind_deg());
            weatherModel.setWindSpeed(w.getDaily()[day].getWind_speed());
            weatherModel.setDate(Date.valueOf(LocalDate.ofInstant(Instant.ofEpochSecond(w.getDaily()[day].getDt()), ZoneId.of("CET"))));
            weatherModel.setLocation(city);
            saveWeather(weatherModel);
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
    }

    public Coords getCoordFromCity(LocationModelEntity city) {
        Coords coords = null;

        Gson gson = new Gson();

        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/geo/1.0/direct?q="+ city.getCityName() + "&limit=1&appid="+ properties.getProperty("API_KEY"))
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
            JSONObject json = jsonArray.getJSONObject(0);
            coords = gson.fromJson(json.toString(),Coords.class);
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
        return coords;
    }

    @Override
    public List<WeatherModelEntity> getAllWeatherModelData() {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<WeatherModelEntity> list = session.createQuery("SELECT n FROM WeatherModelEntity AS n", WeatherModelEntity.class)
                    .getResultList();

            transaction.commit();

            return list;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }

        return Collections.emptyList();

    }

    @Override
    public void deleteRecord(WeatherModelEntity weatherModelEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(weatherModelEntity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }
}
