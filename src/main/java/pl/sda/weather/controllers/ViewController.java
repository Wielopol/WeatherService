package pl.sda.weather.controllers;


import pl.sda.weather.Gui;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.IWeatherService;
import pl.sda.weather.service.impl.LocationServiceImpl;

import pl.sda.weather.service.impl.IWeatherServiceImpl;

import java.util.List;

public class ViewController {

    private static final ILocationService locationService = new LocationServiceImpl();

    private static final Gui gui = new Gui();

    private static final IWeatherService readWeathersService = new IWeatherServiceImpl();


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

    public static void displayWeathers() {

        List<LocationModel> locationsList = locationService.getLocationModelFromBD();


        try {
            readWeathersService.listWeathers(locationsList)
                    .forEach(w -> System.out.println(w == null ? "There is no weather for this location" : w));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void cleanFile() {
        locationService.cleanDBWithLocalModel();
    }



}
