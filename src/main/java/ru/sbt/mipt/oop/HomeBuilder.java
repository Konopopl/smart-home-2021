package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.component.alarm.Alarm;
import ru.sbt.mipt.oop.writer.SmartHomeJsonFileWriter;
import ru.sbt.mipt.oop.writer.SmartHomeWriter;

import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) {
        SmartHome smartHome = buildSmartHome();

        SmartHomeWriter smartHomeWriter = new SmartHomeJsonFileWriter(smartHome, "output.js");

        smartHomeWriter.writeSmartHome();
    }

    private static SmartHome buildSmartHome() {
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door("1", false)),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door("2", false)),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door("3", true)),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door("4", false)),
                "hall");
        return new SmartHome(new Alarm(), Arrays.asList(kitchen, bathroom, bedroom, hall));
    }

}
