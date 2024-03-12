package com.miu.pmtbackendapi.service.property.impl;

import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PropertyImageImpl {

    String defaultpatth = "/Users/rajendramaharjan/Downloads/projectdummy/";

    public String uploadFile(String path) throws FileNotFoundException, ItemNotFoundException {
        File file = new File(path);
        String originalFileName = file.getName();
        String randomString = UUID.randomUUID().toString();

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExtension = randomString.concat(extension);
        String fullPathWithFileName = path.concat(fileNameWithExtension);

        if (extension.equalsIgnoreCase(".jpg")
                || extension.equalsIgnoreCase(".png")
                || extension.equalsIgnoreCase(".jpeg")) {

            File folder = new File(defaultpatth);

            if (!folder.exists())
                folder.mkdirs();
            try {
                Files.copy(new FileInputStream(file), Paths.get(fullPathWithFileName));
                return fileNameWithExtension;
            } catch (IOException e) {
                throw new ItemNotFoundException("File upload failed");
            }

        } else {
            throw new FileNotFoundException("File format " + extension + " is not accepted");
        }


    }

    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        String fullPath = path.concat(filename);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fullPath);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        return inputStream;
    }
}
