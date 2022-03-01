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
            System.out.println("[4]. Optional functionalities");
            System.out.println("[0]. Exit");

            choose1 = scanner.nextLine();


            switch (choose1) {
                case "1":
                    LOCATION_CONTROLLER.addLocation();
                    //TODO
                    // The user should be able to add a location to the file by entering the following values:
                    break;
                case "2":
                   LOCATION_CONTROLLER.displayLocation();
                    //TODO
                    // By selecting the menu option, the user should be able to see all locations entered into the file.
                    break;
                case "3":
                    LOCATION_CONTROLLER.displayWeathers();
                    //TODO
                    //Downloading weather data from external services
                    break;
                case "4":
//                    showInfo("opcja 4");
                    //TODO
                    //Edit location
                    //Location search
                    //Statistic data
                    //Data write/read
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

//TODO
//    If incorrect data are entered, the user should be notified via an appropriate message.


