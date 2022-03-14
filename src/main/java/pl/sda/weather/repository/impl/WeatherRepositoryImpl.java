package pl.sda.weather.repository.impl;

import com.google.gson.Gson;
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
import java.util.*;

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
            weatherModel.setTemperature(w.getDaily()[day].getTemp().getDayTemp());
            weatherModel.setPressure(w.getDaily()[day].getPressure());
            weatherModel.setHumidity(w.getDaily()[day].getHumidity());
            weatherModel.setWindDir(w.getDaily()[day].getWind_deg());
            weatherModel.setWindSpeed(w.getDaily()[day].getWind_speed());
            weatherModel.setDate(Date.valueOf(LocalDate.ofInstant(Instant.ofEpochSecond(w.getDaily()[day].getDt()), ZoneId.of("CET"))));
            weatherModel.setLocation(city);
            return weatherModel;
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
        return getAccurateCoords(coords, city, length);
    }

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
    public WeatherModelEntity getWeatherModelDataByLocationId(LocationModelEntity location) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            WeatherModelEntity model = (WeatherModelEntity) session.createQuery("FROM WeatherModelEntity w WHERE w.location =:location")
                    .setParameter("location", location)
                    .getSingleResult();

            transaction.commit();
            return model;

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
        return null;
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
