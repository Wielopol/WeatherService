package pl.sda.weather.transform;

import pl.sda.weather.model.WeatherModelDTO;
import pl.sda.weather.model.entity.WeatherModelEntity;

import java.util.ArrayList;
import java.util.List;

public class WeatherTransform {

    private final LocationTransform locationTransform = new LocationTransform();

    public WeatherModelDTO weatherTransformToView(WeatherModelEntity weatherModelEntity) {
        WeatherModelDTO weatherModel = new WeatherModelDTO();

        weatherModel.setId(weatherModelEntity.getId());
        weatherModel.setTemperature(weatherModelEntity.getTemperature());
        weatherModel.setPressure(weatherModelEntity.getPressure());
        weatherModel.setHumidity(weatherModelEntity.getHumidity());
        weatherModel.setWindDir(weatherModelEntity.getWindDir());
        weatherModel.setWindSpeed(weatherModelEntity.getWindSpeed());
        weatherModel.setDate(weatherModelEntity.getDate());
        weatherModel.setLocation(locationTransform.locationTransformToView(weatherModelEntity.getLocation()));

        return weatherModel;
    }

    public List<WeatherModelDTO> translateList(List<WeatherModelEntity> list) {
        List<WeatherModelDTO> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            newList.add(i, weatherTransformToView(list.get(i)));
        }

        return newList;
    }
}
