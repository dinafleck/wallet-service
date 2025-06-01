package com.wallet.service.utils;

import com.wallet.service.models.Model;
import com.wallet.service.models.ModelStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void write(String path, String fileName, String content) throws IOException {
        String databasePath = getDatabasePath(path);
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

    private static String getDatabasePath(String path) {
        return "database/" + path;
    }

    public static Model read(String path, String fileName, String forceType) {
        String databasePath = getDatabasePath(path);
        try (BufferedReader br = new BufferedReader(new FileReader(databasePath + "/" + fileName))) {
            String line = br.readLine();

            String type = forceType == null ? fileName.replace(".csv", "") : forceType;

            Model model = ModelStrategy.findModel(type);

            return model == null ? null : model.fromString(line);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Model> readAll(String path, String forceType) {
        String databasePath = getDatabasePath(path);
        File folder = new File(databasePath);
        File[] files = folder.listFiles();

        if (files != null) {
            List<Model> models = new ArrayList<>();
            for (File file : files) {
                if (file.isFile()) {
                    models.add(read(path, file.getName(), forceType));
                }
            }
            return models;
        }
        return new ArrayList<>();
    }

    public static int next(String path) {
        String databasePath = getDatabasePath(path);
        File folder = new File(databasePath);
        File[] files = folder.listFiles();
        int counter = 0;
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName().replace(".csv", "");
                    if (Integer.parseInt(fileName) > counter) {
                        counter = Integer.parseInt(fileName);
                    }
                }
            }

        } else {
            System.out.println("Could not find the directory");
        }

        return counter + 1;
    }
}
