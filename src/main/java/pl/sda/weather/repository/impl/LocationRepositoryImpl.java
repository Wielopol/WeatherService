package pl.sda.weather.repository.impl;


import com.google.gson.Gson;
import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.model.LocationModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocationRepositoryImpl implements ILocationRepository {

    private static final String FILE_LOCATION_JSON = "src/main/resources/location/locations.json";



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
    public List<LocationModel> getLocationModelFromFileJson() {
//        List<String[]> lines = getLinesFromFileJson();

        Gson gson = new Gson();

        try (Stream<String> stream = Files.lines(Paths.get(FILE_LOCATION_JSON))) {
            return stream.map(s -> gson.fromJson(s,LocationModel.class)).toList();
        } catch (IOException  e) {
            System.err.println("Unable to read the file.");
        }
        return Collections.emptyList();
    }

    @Override
    public void cleanFile() {
        try {
            Files.write(Paths.get(FILE_LOCATION_JSON), "".getBytes());
        } catch (IOException e) {
            System.err.println("Unable to clear the file.");
        }
    }



    public List<String[]> getLinesFromFileJson() {
       List<String[]> lines = new ArrayList<>();
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
