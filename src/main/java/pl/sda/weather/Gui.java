package pl.sda.weather;



import pl.sda.weather.controllers.ViewController;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.validation.LocationValidator;

import java.util.Scanner;
import java.util.UUID;


public class Gui {

    private static final ViewController LOCATION_CONTROLLER = new ViewController();

    private static final LocationValidator validation = new LocationValidator();

    private static final Scanner scanner = new Scanner(System.in);

    public static void showMainMenu() {

        String choose1;
        do{

            System.out.println("[1]. Adding a location");
            System.out.println("[2]. Display of available locations");
            System.out.println("[3]. Downloading weather data");
            System.out.println("[0]. Exit");

            choose1 = scanner.nextLine();


            switch (choose1) {
                case "1" -> LOCATION_CONTROLLER.addLocation();
                case "2" -> LOCATION_CONTROLLER.displayLocation();
                case "3" -> LOCATION_CONTROLLER.displayWeathers();
                case "0" -> LOCATION_CONTROLLER.cleanFile();
                default -> System.out.println("Wrong choice !!");
            }

        }while (!choose1.equals("0"));

    }public LocationModel getDataToLocalModelFromUser() {

        String idUUID = String.valueOf(UUID.randomUUID());
        String longitude;
        String latitude;

        do {
            System.out.println("Write city latitude : ");
            latitude = scanner.nextLine();
            if (!validation.validationCoordinates(latitude)) {
                System.out.println("Enter the name, this field cannot be empty format (00.00) !!! ");
            }
        } while (!validation.validationCoordinates(latitude));

        do {
            System.out.println("Write city longitude : ");
            longitude = scanner.nextLine();
            if (!validation.validationCoordinates(longitude)) {
                System.out.println("Enter the name, this field cannot be empty format (00.00) !!! ");
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
            region = "User do not add regional";
        }

        String countryName;
        do {
            System.out.println("Write country name :");
            countryName = scanner.nextLine();
            if (!validation.validationNames(countryName)) {
                System.out.println("Enter the name, this field cannot be empty !!! ");
            }

        } while (!validation.validationNames(countryName));

        return new LocationModel(idUUID, longitudeAndLatitude, cityName, region, countryName);
    }


}
