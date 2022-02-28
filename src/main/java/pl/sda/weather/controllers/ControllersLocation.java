package pl.sda.weather.controllers;


import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceImpl;
import pl.sda.weather.service.impl.ReadWeathersImpl;
import pl.sda.weather.validation.LocationValidation;

import javax.xml.validation.ValidatorHandler;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import static pl.sda.weather.Gui.showMainMenu;

public class ControllersLocation {
    ILocationService locationService = new LocationServiceImpl();
    ReadWeathersImpl readWeathers = new ReadWeathersImpl();

    private static final Scanner scanner = new Scanner(System.in);

    private static LocationValidation validation = new LocationValidation();


    public void addLocation() {

        String idUUID = String.valueOf(UUID.randomUUID());

       String longitudeAndLatitude;
        String longitudeS;
        String longitudeN;
        String latitudeW;
        String latitudeE;
       do {
           System.out.println("Write longitude S :");
           longitudeS = scanner.nextLine();

           System.out.println("Write longitude  N :");
           longitudeN = scanner.nextLine();

           System.out.println("Write latitude W :");
           latitudeW = scanner.nextLine();

           System.out.println("Write latitude E :");
           latitudeE = scanner.nextLine();

           longitudeAndLatitude = "(latitude: " + longitudeS + " -> S; " + longitudeN + " -> N; longitude: " + latitudeW + " -> W; " + latitudeE + " -> E)";


       }while (latitudeE.equals("") || latitudeW.equals("") || longitudeN.equals("") || longitudeS.equals(""));

        String cityName;
        do {
            System.out.println("Write city name : ");
            cityName = scanner.next();

            if (cityName.equals("")) {
                System.out.println("Enter the name, this field cannot be empty !!! ");
            }
        } while (cityName.equals(""));


        System.out.println("Write region : ");
        String region = scanner.next();
        if (region.equals("")) {
            region = "User don't add regional";
        }

        String countryName;
        do {
            System.out.println("Write country name :");
            countryName = scanner.next();

            if (countryName.equals("")) {
                System.out.println("Enter the country name, this field cannot be empty !!! ");
            }

        } while (countryName.equals(""));


        locationService.addLocationModelToDB(new LocationModel(idUUID, longitudeAndLatitude, cityName, region, countryName));

        showMainMenu();

    }

    public void displayLocation() {

        List<LocationModel> list = locationService.getLocationModelFromFile();

        for (LocationModel locationModel : list) {
            System.out.println(locationModel);
        }
        showMainMenu();

    }

    public void displayWeathers() {

        List<LocationModel> locationsList = locationService.getLocationModelFromFile();
        Map<String, Weather> weathersMap = readWeathers.mapWeather();

        readWeathers.listWeathers(locationsList,weathersMap).forEach(System.out::println);
    }
}
