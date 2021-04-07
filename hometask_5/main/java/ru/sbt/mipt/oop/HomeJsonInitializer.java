package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeJsonInitializer implements HomeInitializer{
    @Override
    public SmartHome initializeHome() {
        Gson gson = new Gson();
        String json = "";
        try {
            json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        } catch (IOException e) {
            System.err.println("Couldnt read file");
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
