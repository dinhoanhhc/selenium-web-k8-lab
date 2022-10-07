package test_data.computer;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public static<T> T buildDataObjectFrom(String fileLocation, Class<T> dataType){
        T data;
        String currentProjectLocation = System.getProperty("user.dir");
        String fileAbsoulutePath =currentProjectLocation +fileLocation;
        try (
                Reader jsonContentReader = Files.newBufferedReader(Paths.get(fileAbsoulutePath));
        ) {

            Gson gson = new Gson();
            data = gson.fromJson(jsonContentReader, dataType);

        } catch (Exception e) {
            throw new RuntimeException("[ERR] Error while reading the file" + fileLocation);
        }

        return data;

    }
}
