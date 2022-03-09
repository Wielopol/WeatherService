package pl.sda.weather.controllers;


import pl.sda.weather.Gui;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.IWeatherService;
import pl.sda.weather.service.impl.LocationServiceImpl;

import pl.sda.weather.service.impl.WeatherServiceImpl;

import java.util.List;
import java.util.UUID;

public class ViewController {

    private static final ILocationService locationService = new LocationServiceImpl();

    private static final Gui gui = new Gui();

    private static final IWeatherService readWeathersService = new WeatherServiceImpl();


    public void addLocation() {

        locationService.addLocationModelToDB(getDataFromUserToCompleteLocationModel());

    }

    public LocationModel getDataFromUserToCompleteLocationModel() {

        String idUUID = String.valueOf(UUID.randomUUID());
        String longitudeAndLatitude = gui.getLongitudeAndLatitudeFromUser();
        String cityName = gui.getCityNameFromUser();
        String region = gui.getRegionFromUser();
        String countryName = gui.getCountryNameFromUser();

        return new LocationModel(idUUID, longitudeAndLatitude, cityName, region, countryName);
    }


    public void displayLocation() {

        List<LocationModel> list = locationService.getLocationModelFromBD();

        System.out.println("--------------------------------");
        System.out.println("List of Localization !!! ");
        System.out.println("--------------------------------");
        list.forEach(System.out::println);
        System.out.println("--------------------------------");


    }

    public void displayWeathers() {

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

    public void locationSearch() {

        String pattern = gui.enterString("Enter name or id Location with you looking ");

        LocationModel model = locationService.getLocationModelFromDbAfterIdOrName(pattern);

        if (model == null) {
            System.out.println("That location does exist");
        }
        System.out.println(model);

        System.out.println();

    }

    public void editLocationModelInDb(String whatsEdit) {

        String pattern = gui.enterString("Enter name city location to edit");
        String editData = gui.enterString("Enter new data ");

        locationService.editLocationModel(whatsEdit,pattern, editData);


    }




}
