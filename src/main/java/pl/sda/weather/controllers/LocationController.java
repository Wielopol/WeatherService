package pl.sda.weather.controllers;


import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceImpl;
import pl.sda.weather.validation.LocationValidation;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LocationController {
    ILocationService locationService = new LocationServiceImpl();

    private static final Scanner scanner = new Scanner(System.in);

    private static LocationValidation validation = new LocationValidation();


    public void addLocation() {

        String idUUID = String.valueOf(UUID.randomUUID());

       String longitudeAndLatitude = "lok";
//        String longitudeS;
//        String longitudeN;
//        String latitudeW;
//        String latitudeE;
//       do {
//           System.out.println("Write longitude S :");
//           longitudeS = scanner.nextLine();
//
//           System.out.println("Write longitude  N :");
//           longitudeN = scanner.nextLine();
//
//           System.out.println("Write latitude W :");
//           latitudeW = scanner.nextLine();
//
//           System.out.println("Write latitude E :");
//           latitudeE = scanner.nextLine();
//
//           longitudeAndLatitude = "(latitude: " + longitudeS + " -> S; " + longitudeN + " -> N; longitude: " + latitudeW + " -> W; " + latitudeE + " -> E)";
//
//
//       }while (latitudeE.equals("") || latitudeW.equals("") || longitudeN.equals("") || longitudeS.equals(""));

        String cityName;
        do {
            System.out.println("Write city name : ");
            cityName = scanner.nextLine();
            if (!validation.valName(cityName)) {
                System.out.println("Enter the name, this field cannot be empty !!! ");
            }
        } while (!validation.valName(cityName));


        System.out.println("Write region : ");
        String region = scanner.nextLine();
        if (region.equals("")) {
            region = "User don't add regional";
        }

        String countryName;
        do {
            System.out.println("Write country name :");
            countryName = scanner.nextLine();
            if (!validation.valName(countryName)) {
                System.out.println("Enter the name, this field cannot be empty !!! ");
            }

        } while (!validation.valName(countryName));

        locationService.addLocationModelToDB(new LocationModel(idUUID, longitudeAndLatitude, cityName, region, countryName));

    }

    public void displayLocation() {

        List<LocationModel> list = locationService.getLocationModelFromFile();

        list.stream()
                .forEach(System.out::println);


    }


}
