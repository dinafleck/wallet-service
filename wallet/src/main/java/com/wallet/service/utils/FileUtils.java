package com.wallet.service.utils;

import com.wallet.service.models.Model;
import com.wallet.service.models.ModelStrategy;

import java.io.*;

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

    public static Model read(String path, String fileName) {
        String databasePath = "database/" + path;
        try (BufferedReader br = new BufferedReader(new FileReader(databasePath + "/" + fileName))) {
            String line = br.readLine();

            Model model = ModelStrategy.findModel(fileName.replace(".csv", ""));

            return model == null ? null : model.fromString(line);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
