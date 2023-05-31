package app.components.service;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;

@Service
public class SaveFileService {

    private final static String DIRECTORY_TO_SAVE = "C:\\Java projects\\expenses-manager\\files_directory\\";

    public String saveFileToDir(File fileToSave) throws FileNotFoundException {
        String path = DIRECTORY_TO_SAVE + LocalDateTime.now() + fileToSave.getName();
        try (FileInputStream fileInputStreamToSave = new FileInputStream(fileToSave);
             FileOutputStream out = new FileOutputStream(path)) {
            int n;
            while ((n = fileInputStreamToSave.read()) != -1) {
                out.write(n);
            }
        }
        catch (IOException e){
            throw new FileNotFoundException("files not found");
        }
        return path;
    }
}
