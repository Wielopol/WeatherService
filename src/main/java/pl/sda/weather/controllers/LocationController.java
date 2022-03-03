package pl.sda.weather.controllers;


import pl.sda.weather.Gui;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceImpl;
import pl.sda.weather.service.impl.ReadWeathersImpl;

import java.util.List;
import java.util.Map;

public class LocationController {

    private static final ILocationService locationService = new LocationServiceImpl();

    private static final Gui gui = new Gui();

    private static final ReadWeathersImpl readWeathers = new ReadWeathersImpl();


    public void addLocation() {

        locationService.addLocationModelJsonToDB(gui.getDataToLocalModelFromUser());

    }


    public void displayLocation() {

        List<LocationModel> list = locationService.getLocationModelFromFileJson();

        System.out.println("--------------------------------");
        System.out.println("List of Localization !!! ");
        System.out.println("--------------------------------");
        list
                .forEach(System.out::println);
        System.out.println("--------------------------------");


    }

    public void displayWeathers() {

        List<LocationModel> locationsList = locationService.getLocationModelFromFileJson();
        Map<String, Weather> weathersMap = readWeathers.getWeatherMap();

        readWeathers.listWeathers(locationsList, weathersMap)
                .forEach(w -> System.out.println(w == null ? "There is no weather for this location" : w));
    }

    public void cleanFile() {
        locationService.cleanFile();
    }


}
