package pl.sda.weather.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.sda.weather.Gui;
import pl.sda.weather.connection.HibernateUtil;
import pl.sda.weather.model.LocationModelDTO;
import pl.sda.weather.model.WeatherModelDTO;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.IWeatherService;
import pl.sda.weather.service.impl.LocationServiceDbImpl;
import pl.sda.weather.service.impl.WeatherServiceImpl;
import pl.sda.weather.transform.LocationTransform;
import pl.sda.weather.transform.WeatherTransform;

import java.util.List;
import java.util.UUID;

public class ViewController {

    private static final ILocationService locationService = new LocationServiceDbImpl();

    private static final LocationTransform locationTransform = new LocationTransform();

    private static final WeatherTransform weatherTransform = new WeatherTransform();

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);


    private static final IWeatherService readWeathersService = new WeatherServiceImpl();


    public LocationModelEntity addLocation() {

        LocationModelEntity model = getDataFromUserToCompleteLocationModel();

        if (locationService.isLocationExiest(model)) {
            System.out.println("This location already exists");
            return locationService.getLocationByIdAndName(model.getCityName());
        } else {
            locationService.saveLocationModel(model);
            return model;
        }
    }


    public LocationModelEntity getDataFromUserToCompleteLocationModel() {

        String idUUID = String.valueOf(UUID.randomUUID());
        String longitudeAndLatitude = Gui.getLongitudeAndLatitudeFromUser();
        String cityName = Gui.getCityNameFromUser();
        String region = Gui.getRegionFromUser();
        String countryName = Gui.getCountryNameFromUser();

        return new LocationModelEntity(idUUID, longitudeAndLatitude, cityName, region, countryName);
    }


    public void displayLocation() {

        List<LocationModelEntity> list = locationService.getAllLocation();

        List<LocationModelDTO> newList = locationTransform.translateList(list);


        System.out.println("--------------------------------");
        System.out.println("List of locations:");
        System.out.println("--------------------------------");
        newList.forEach(System.out::println);
        System.out.println("--------------------------------");


    }

    public void displayWeathers(int day) {

        List<LocationModelEntity> locationsList = locationService.getAllLocation();

        try {
            readWeathersService.listWeathers(locationsList, day);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        List<WeatherModelEntity> weathersList = readWeathersService.getAllWeathers();

        List<WeatherModelDTO> newList = weatherTransform.translateList(weathersList);

        System.out.println("--------------------------------");
        System.out.println("List of weathers:");
        System.out.println("--------------------------------");
        newList.forEach(System.out::println);
        System.out.println("--------------------------------");
    }

    public void cleanFile() {
        readWeathersService.cleanRecords();
        locationService.cleanRecords();
    }

    public void delateOneLocation() {

        String pattern = Gui.enterString("Enter city name or ID of location you're looking for");

        locationService.delateLocationOnList(pattern);

        System.out.println("--------------------------------");
        System.out.println("The location " + pattern + " has been removed");
        System.out.println("--------------------------------");
    }

    public void showOneWeather(int day) {
        LocationModelEntity location = addLocation();

        readWeathersService.listOneWeather(location, day);

        WeatherModelEntity weather = readWeathersService.getWeatherByLocation(location);
        WeatherModelDTO weatherDTO = weatherTransform.weatherTransformToView(weather);

        System.out.println("--------------------------------");
        System.out.println(weatherDTO);
        System.out.println("--------------------------------");
    }

    public void locationSearch() {

        String pattern = Gui.enterString("Enter name or ID of location you're looking for");


        LocationModelEntity model = locationService.getLocationByIdAndName(pattern);
        LocationModelDTO newModel = locationTransform.locationTransformToView(model);
        System.out.println("--------------------------------");
        System.out.println("The location you are looking for is:");
        System.out.println("--------------------------------");
        System.out.println(newModel);
        System.out.println("--------------------------------");

    }

    public void editLocationModelInDb(String whatsEdit, String pattern, String editData) {


        if (locationService.isLocationExiest(locationService.getLocationByIdAndName(pattern))) {
            locationService.editLocation(whatsEdit, pattern, editData);

            System.out.println("--------------------------------");
            System.out.println("The location " + pattern + " has been updated");
            System.out.println("--------------------------------");
        } else {
            System.out.println("That location is not exiest !!!");

        }
    }
}
