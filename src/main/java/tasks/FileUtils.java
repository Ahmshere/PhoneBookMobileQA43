package tasks;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    // Метод, который получает список только .json файлов в папке
    public static List<String> getJsonFilesFromFolder(String folderPath) throws IOException {
        return Files.walk(Paths.get(folderPath)) // Проходим по всем файлам и подпапкам в указанной директории
                .filter(Files::isRegularFile)    // Оставляем только файлы (не директории)
                .filter(path -> path.toString().endsWith(".json")) // Фильтруем файлы, оставляем только .json
                .map(Path::toString)  // Преобразуем каждый Path в строковое представление пути
                .collect(Collectors.toList()); // Собираем результат в список
    }

    //***
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
    //**

    public static void main(String[] args) throws IOException {
        String folderPath = "src/main/resources";
        List<String> jsonFiles = getJsonFilesFromFolder(folderPath);
        List arrayObjects= new ArrayList();
        arrayObjects.add("testJob1.json");
        arrayObjects.add("testJob2.json");

        List<DataFile> dataFiles = DataFileParser.parseDataFilesFromJsonFiles(jsonFiles);
        dataFiles.forEach(System.out::println);
    }
}
