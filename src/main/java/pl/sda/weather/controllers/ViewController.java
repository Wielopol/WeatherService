package pl.sda.weather.controllers;


import pl.sda.weather.Gui;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.IWeatherService;
import pl.sda.weather.service.impl.LocationServiceDbImpl;

import pl.sda.weather.service.impl.WeatherServiceImpl;

import java.util.List;
import java.util.UUID;

public class ViewController {

    private static final ILocationService locationService = new LocationServiceDbImpl();


    private static final IWeatherService readWeathersService = new WeatherServiceImpl();


    public void addLocation() {

        LocationModelEntity model = getDataFromUserToCompleteLocationModel();

        if(locationService.isLocationExiest(model)){
            System.out.println("This location already exists");
        }else {
            locationService.saveLocationModel(model);
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

    public void displayWeathers() {

        List<LocationModelEntity> locationsList = locationService.getAllLocation();

        try {
            readWeathersService.listWeathers(locationsList)
                    .forEach(w -> System.out.println(w == null ? "There is no weather for this location" : w));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void cleanFile() {
        locationService.cleanRecords();
    }

    public void delateOneLocation(){

        String pattern = Gui.enterString("Enter name or id location with you looking ");

        locationService.delateLocationOnList(pattern);
    }

    public void locationSearch() {

        String pattern = Gui.enterString("Enter name or id location with you looking ");

        LocationModelEntity model = locationService.getLocationByIdAndName(pattern);

        if (model == null) {
            System.out.println("That location does exist");
        }
        System.out.println(model);

        System.out.println();

    }

    public void editLocationModelInDb(String whatsEdit, String pattern, String editData) {


        locationService.editLocation(whatsEdit,pattern, editData);


    }




}
