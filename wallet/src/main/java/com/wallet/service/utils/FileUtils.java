package com.wallet.service.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static void write(String path, String fileName, String content) throws IOException {
        String databasePath = "database/" + path;
        if (new File(databasePath).mkdirs()) {
            System.out.println("Directory created");
        } else {
            System.out.println("Directory not created");
        }

        File file = new File(databasePath + "/" + fileName);

        if (file.createNewFile()) {
            System.out.println("File created");
        } else {
            System.out.println("File already exists");
        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
        }
    }
}
