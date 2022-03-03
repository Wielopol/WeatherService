package pl.sda.weather.controllers;


import pl.sda.weather.Gui;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceImpl;
import pl.sda.weather.service.impl.IReadWeathersServiceImpl;

import java.util.List;
import java.util.Map;

public class ViewController {

    private static final ILocationService locationService = new LocationServiceImpl();

    private static final Gui gui = new Gui();

    private static final IReadWeathersServiceImpl readWeathersService = new IReadWeathersServiceImpl();


    public void addLocation() {

        locationService.addLocationModelToDB(gui.getDataToLocalModelFromUser());

    }


    public void displayLocation() {

        List<LocationModel> list = locationService.getLocationModelFromBD();

        System.out.println("--------------------------------");
        System.out.println("List of Localization !!! ");
        System.out.println("--------------------------------");
        list
                .forEach(System.out::println);
        System.out.println("--------------------------------");


    }

    public void displayWeathers() {

        List<LocationModel> locationsList = locationService.getLocationModelFromBD();
        Map<String, Weather> weathersMap = readWeathersService.getWeatherMap();

        readWeathersService.listWeathers(locationsList, weathersMap)
                .forEach(w -> System.out.println(w == null ? "There is no weather for this location" : w));
    }

    public void cleanFile() {
        locationService.cleanDBWithLocalModel();
    }


}
