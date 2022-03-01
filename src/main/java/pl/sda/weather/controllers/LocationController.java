package pl.sda.weather.controllers;


import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceImpl;
import pl.sda.weather.service.impl.ReadWeathersImpl;
import pl.sda.weather.validation.LocationValidator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class LocationController {

    private static final  ILocationService locationService = new LocationServiceImpl();

    private static final Scanner scanner = new Scanner(System.in);

    private static LocationValidator validation = new LocationValidator();

    ReadWeathersImpl readWeathers = new ReadWeathersImpl();


    public void addLocation() {

        String idUUID = String.valueOf(UUID.randomUUID());

       String longitudeAndLatitude = "Location TODO ";

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

        locationService.addLocationModelToDB(new LocationModel(idUUID, longitudeAndLatitude, cityName, region, countryName));

    }

    public void displayLocation() {

        List<LocationModel> list = locationService.getLocationModelFromFile();

        list.stream()
                .forEach(System.out::println);


    }

    public void displayWeathers() {

        List<LocationModel> locationsList = locationService.getLocationModelFromFile();
        Map<String, Weather> weathersMap = readWeathers.mapWeather();

        readWeathers.listWeathers(locationsList,weathersMap).forEach(System.out::println);
    }


}
