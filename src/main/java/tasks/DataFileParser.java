package tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataFileParser {


    public static List<DataFile> parseDataFilesFromJsonFiles(List<String> jsonFiles) throws IOException {
        Gson gson = new Gson();
        List<DataFile> dataFiles = new ArrayList<>();

        for (String filePath : jsonFiles) {
            String jsonContent = new String(Files.readAllBytes(Path.of(filePath)));
            List<DataFile> dataFileList = gson.fromJson(jsonContent, new TypeToken<List<DataFile>>(){}.getType());
            dataFiles.addAll(dataFileList);
        }
        return dataFiles;
    }
}
