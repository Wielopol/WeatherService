package pl.sda.weather;



import pl.sda.weather.controllers.LocationController;

import java.util.Scanner;


public class Gui {

    private static final LocationController LOCATION_CONTROLLER = new LocationController();

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
                case "1":
                    LOCATION_CONTROLLER.addLocation();

                    break;
                case "2":
                   LOCATION_CONTROLLER.displayLocation();

                    break;
                case "3":
                    LOCATION_CONTROLLER.displayWeathers();

                    break;
                case "0":
                    break;
                default:
                    System.out.println("Wrong choice !!");
                    break;
            }

        }while (!choose1.equals("0"));

    }


}
