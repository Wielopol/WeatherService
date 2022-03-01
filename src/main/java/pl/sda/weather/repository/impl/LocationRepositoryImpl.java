package pl.sda.weather.repository.impl;


import com.google.gson.Gson;
import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.model.LocationModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocationRepositoryImpl implements ILocationRepository {

    private static final String FILE_LOCATION = "src/main/resources/location/locations.txt";
    private static final String FILE_LOCATION_JSON = "src/main/resources/location/locations.json";

    @Override

    public void addLocationModelToTxtDB(LocationModel locationModel) {
        try {
            Files.write(Paths.get(FILE_LOCATION),
                    (locationModel.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Unable to write the file.");

        }
    }

    @Override
    public void addLocationModelJsonToDB(LocationModel locationModel){
        Gson gson = new Gson();
        try {
            Files.write(Paths.get(FILE_LOCATION_JSON),
                    (gson.toJson(locationModel) + System.lineSeparator()) .getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.err.println("Unable to write the file.");

        }
    }



    @Override
    public List<LocationModel> getLocationModelFromFileTxt() {
        List<String[]> lines = getLinesFromFileTxt();

        return lines.stream()
                .map(line -> new LocationModel(line[0], line[1], line[2], line[3], line[4]))
                .collect(Collectors.toList());
    }
    @Override
    public List<LocationModel> getLocationModelFromFileJson() {
        List<String[]> lines = getLinesFromFileJson();

        return lines.stream()
                .map(line -> new LocationModel(line[0], line[1], line[2], line[3], line[4]))
                .collect(Collectors.toList());
    }



    private static List<String[]> lines = new ArrayList<>();

    public List<String[]> getLinesFromFileTxt() {
        try (Stream<String> stream = Files.lines(Paths.get(FILE_LOCATION))) {
            lines = stream
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        } catch (IOException  e) {
            System.err.println("Unable to read the file.");
        }
        return lines;
    }

    public List<String[]> getLinesFromFileJson() {
        try (Stream<String> stream = Files.lines(Paths.get(FILE_LOCATION_JSON))) {
            lines = stream
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        } catch (IOException  e) {
            System.err.println("Unable to read the file.");
        }
        return lines;
    }

}
