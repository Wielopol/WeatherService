package pl.sda.weather.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.sda.weather.Gui;
import pl.sda.weather.connection.HibernateUtil;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.IWeatherService;
import pl.sda.weather.service.impl.LocationServiceDbImpl;
import pl.sda.weather.service.impl.WeatherServiceImpl;

import java.util.List;
import java.util.UUID;

public class ViewController {

    private static final ILocationService locationService = new LocationServiceDbImpl();

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);


    private static final IWeatherService readWeathersService = new WeatherServiceImpl();


    public LocationModelEntity addLocation() {

        LocationModelEntity model = getDataFromUserToCompleteLocationModel();

        if(locationService.isLocationExiest(model)){
            System.out.println("This location already exists");
            return locationService.getLocationByIdAndName(model.getCityName());
        }else {
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

        System.out.println("--------------------------------");
        System.out.println("List of Localization !!! ");
        System.out.println("--------------------------------");
        list.forEach(System.out::println);
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

        System.out.println("--------------------------------");
        System.out.println("List of Weathers !!! ");
        System.out.println("--------------------------------");
        weathersList.forEach(System.out::println);
        System.out.println("--------------------------------");
    }

    public void cleanFile() {
        readWeathersService.cleanRecords();
        locationService.cleanRecords();
    }

    public void delateOneLocation(){

        String pattern = Gui.enterString("Enter name or id location with you looking ");

        locationService.delateLocationOnList(pattern);

        System.out.println("--------------------------------");
        System.out.println("The location "+ pattern+ " has been removed !!");
        System.out.println("--------------------------------");
    }

    public void showOneWeather(int day){
        LocationModelEntity location = addLocation();

        readWeathersService.listOneWeather(location, day);

        WeatherModelEntity weather = readWeathersService.getWeatherByLocationId(location);

        System.out.println("--------------------------------");
        System.out.println("Weather for " + location.getCityName() + " !!! ");
        System.out.println("--------------------------------");
        System.out.println(weather);
        System.out.println("--------------------------------");
    }

    public void locationSearch() {

        String pattern = Gui.enterString("Enter name or id location with you looking ");


            LocationModelEntity model = locationService.getLocationByIdAndName(pattern);
            System.out.println("--------------------------------");
            System.out.println("The location you are looking for is : ");
            System.out.println("--------------------------------");
            System.out.println(model);
            System.out.println("--------------------------------");

    }

    public void editLocationModelInDb(String whatsEdit, String pattern, String editData) {


        locationService.editLocation(whatsEdit,pattern, editData);

        System.out.println("--------------------------------");
        System.out.println("The location "+pattern+" has been edit : ");
        System.out.println("--------------------------------");


    }
}
