package pl.sda.weather.controllers;


import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static pl.sda.weather.Gui.showMainMenu;

public class ControllersLocation {
    ILocationService locationService = new LocationServiceImpl();

    private static final Scanner scanner = new Scanner(System.in);


    public  void addLocation() {

        String idUUID = String.valueOf(UUID.randomUUID());

        System.out.println("Write longitude and latitude :");
        String longitudeAndLatitude = scanner.next();

        System.out.println("Write city name : ");
        String cityName = scanner.next();

        System.out.println("Write region : ");
        String region = scanner.next();

        System.out.println("Write country name :");
        String countryName = scanner.next();


        locationService.addLocationModelToDB(new LocationModel(idUUID,longitudeAndLatitude,cityName,region,countryName));

        showMainMenu();

    }

    public void displayLocation() {

        List<LocationModel> list = locationService.getLocationModelFromFile();

        for (LocationModel locationModel : list) {
            System.out.println(locationModel);
        }
        showMainMenu();

    }



}
