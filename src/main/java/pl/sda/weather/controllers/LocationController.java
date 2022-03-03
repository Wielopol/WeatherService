package pl.sda.weather.controllers;


import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceImpl;
import pl.sda.weather.service.impl.ReadWeatherApiImpl;
import pl.sda.weather.validation.LocationValidator;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LocationController {

    private static final ILocationService locationService = new LocationServiceImpl();

    private static final Scanner scanner = new Scanner(System.in);

    private static final LocationValidator validation = new LocationValidator();

    private static final ReadWeatherApiImpl weatherApi = new ReadWeatherApiImpl();

    public void addLocation() {

        String idUUID = String.valueOf(UUID.randomUUID());

        String longitude;
        String latitude;

        do {
            System.out.println("Write city latitude in format (000.00) : ");
            latitude = scanner.nextLine();
            if (!validation.validationCoordinates(latitude)) {
                System.out.println("Enter the name, this field cannot be empty !!! ");
            }
        } while (!validation.validationCoordinates(latitude));

        do {
            System.out.println("Write city longitude in format (000.00) : ");
            longitude = scanner.nextLine();
            if (!validation.validationCoordinates(longitude)) {
                System.out.println("Enter the name, this field cannot be empty !!! ");
            }
        } while (!validation.validationCoordinates(longitude));
        String longitudeAndLatitude = "latitude: " + latitude + "; longitude: " + longitude;


        String cityName;
        do {
            System.out.println("Write city name : ");
            cityName = scanner.nextLine();
            if (!validation.validationNames(cityName)) {
                System.out.println("Enter the name, this field cannot be empty !!! ");
            }
        } while (!validation.validationNames(cityName));


        System.out.println("Write region : ");
        String region = scanner.nextLine();
        if (region.equals("")) {
            region = "User don't add regional";
        }

        String countryName;
        do {
            System.out.println("Write country name :");
            countryName = scanner.nextLine();
            if (!validation.validationNames(countryName)) {
                System.out.println("Enter the name, this field cannot be empty !!! ");
            }

        } while (!validation.validationNames(countryName));
        locationService.addLocationModelJsonToDB(new LocationModel(idUUID, longitudeAndLatitude, cityName, region, countryName));

    }

    public void displayLocation() {

        List<LocationModel> listJson = locationService.getLocationModelFromFileJson();

        System.out.println("--------------------------------");
        System.out.println("List of Localization in format .json ");
        System.out.println("--------------------------------");

        listJson
                .forEach(System.out::println);
        System.out.println("--------------------------------");


    }

    public void displayWeathers() {

        List<LocationModel> locationsList = locationService.getLocationModelFromFileJson();

        weatherApi.listWeathers(locationsList)
                .forEach(w -> System.out.println(w == null ? "There is no weather for this location" : w));
    }
    public void cleanFile(){
        locationService.cleanFile();
    }


}
