package pl.sda.weather.dao.impl;


import pl.sda.weather.dao.ILocationDAO;
import pl.sda.weather.model.LocationModel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocationDAOImpl implements ILocationDAO {

    private static final String FILE_LOCATION = "location/locations.txt";

    @Override

    public void addLocationModelToDB(LocationModel locationModel) {

        try {

            Files.write(Paths.get(ClassLoader.getSystemResource(FILE_LOCATION).toURI()),
                    (locationModel.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException | URISyntaxException e) {
            System.err.println("Unable to write the file.");

        }

    }


    @Override
    public List<LocationModel> getLocationModelFromFile() {
        List<String[]> lines = getLinesFromFile();

        return lines.stream()
                .map(line -> new LocationModel(line[0], line[1], line[2],line[3], line[4]))
                .collect(Collectors.toList());
    }


    public List<String[]> getLinesFromFile() {
        List<String[]> lines = new ArrayList<>();


        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(FILE_LOCATION).toURI()))) {
            lines = stream

                    .map(line -> line.split(","))
                    .collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            System.err.println("Unable to read the file.");
        }

        return lines;
    }

}
