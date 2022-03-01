package pl.sda.weather.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocationValidator {

    public boolean validationCoordinates(String name) {
        Pattern recipientPackage = Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,2}$");
        Matcher n = recipientPackage.matcher(name);
        return !name.isBlank() && n.find();
    }

    public boolean validationNames(String name) {

        Pattern namePackage = Pattern.compile("^[a-zA-Z]+\s?[a-zA-Z]+$");
        Matcher n = namePackage.matcher(name);
        return !name.isBlank() && n.find();
    }
}
