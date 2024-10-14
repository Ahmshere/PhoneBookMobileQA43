package tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class FolderReader {

    public static List<File> getFilteredFiles(String folderPath, String fileExtension) throws IOException {
        List<File> files = new ArrayList<>();
        Files.walk(Paths.get(folderPath)).forEach(new Consumer<Path>() {
            @Override
            public void accept(Path path) {
                if (Files.isRegularFile(path) && path.toString().endsWith(fileExtension)) {
                    files.add(path.toFile());
                }
            }
        });
        return files;
    }
    public static void main(String[] args) throws IOException {
        List<File> list = getFilteredFiles("src/main/java/tasks/jsons",".json");
        for (File f : list){
            System.out.println(f.toString());
        }
    }
}
