package ru.sbt.mipt.oop.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeJsonFileWriter implements SmartHomeWriter {
    private final SmartHome smartHome;
    private final String filename;

    public SmartHomeJsonFileWriter(SmartHome smartHome, String filename) {
        this.smartHome = smartHome;
        this.filename = filename;
    }

    @Override
    public void writeSmartHome() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}
