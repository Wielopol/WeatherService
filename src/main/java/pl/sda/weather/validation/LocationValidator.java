package pl.sda.weather.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocationValidator {

    public boolean validationLatitude(String name) {
        Pattern recipientPackage = Pattern.compile("^-?[0-9]{1,3}\\.[0-9]{1,5}$");
        Matcher n = recipientPackage.matcher(name);
        boolean rang = authenticationCoordinateInterval(-90, 90, name);
        return !name.isBlank() && n.find() && rang;
    }
    public boolean validationLongitude(String name) {
        Pattern recipientPackage = Pattern.compile("^-?[0-9]{1,3}\\.[0-9]{1,5}$");
        Matcher n = recipientPackage.matcher(name);
        boolean rang = authenticationCoordinateInterval(-180, 180, name);
        return !name.isBlank() && n.find() && rang;
    }


    public boolean validationNames(String name) {

        Pattern namePackage = Pattern.compile("^[a-zA-Z]+\s?[a-zA-Z]+$");
        Matcher n = namePackage.matcher(name);
        return !name.isBlank() && n.find();
    }

    public boolean authenticationCoordinateInterval(
            int lowestInterval, int highInterval, String value) {
        double valueInt = Double.parseDouble(value);

        return valueInt >= lowestInterval && valueInt <= highInterval;

    }
}