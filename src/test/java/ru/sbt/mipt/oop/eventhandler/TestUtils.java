package ru.sbt.mipt.oop.eventhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Light;

public class TestUtils {
    public static Light getLightById(SmartHome smartHome, String lightId) {
        AtomicReference<Light> light = new AtomicReference<>();

        smartHome.execute(lightCandidate -> {
            if (!(lightCandidate instanceof Light)) {
                return;
            }

            Light tmpLight = (Light) lightCandidate;

            if (tmpLight.getId().equals(lightId)) {
                light.set(tmpLight);
            }
        });

        return light.get();
    }

    public static Door getDoorById(SmartHome smartHome, String doorId) {
        AtomicReference<Door> door = new AtomicReference<>();

        smartHome.execute(doorCandidate -> {
            if (!(doorCandidate instanceof Door)) {
                return;
            }

            Door tmpDoor = (Door) doorCandidate;

            if (tmpDoor.getId().equals(doorId)) {
                door.set(tmpDoor);
            }
        });

        return door.get();
    }

    public static List<Light> getAllLightsCopy(SmartHome smartHome) {
        List<Light> lights = new ArrayList<>();

        smartHome.execute(lightCandidate -> {
            if (!(lightCandidate instanceof Light)) {
                return;
            }

            Light tmpLight = (Light) lightCandidate;

            lights.add(new Light(tmpLight.getId(), tmpLight.isOn()));
        });

        return lights;
    }

    public static List<Door> getAllDoorsCopy(SmartHome smartHome) {
        List<Door> doors = new ArrayList<>();

        smartHome.execute(doorCandidate -> {
            if (!(doorCandidate instanceof Door)) {
                return;
            }

            Door tmpDoor = (Door) doorCandidate;

            doors.add(new Door(tmpDoor.getId(), tmpDoor.isOpen()));
        });

        return doors;
    }

    public static List<Light> getAllLightsExceptOneCopy(SmartHome smartHome, String lightId) {
        List<Light> lights = new ArrayList<>();

        smartHome.execute(lightCandidate -> {
            if (!(lightCandidate instanceof Light)) {
                return;
            }

            Light tmpLight = (Light) lightCandidate;

            if (!tmpLight.getId().equals(lightId)) {
                lights.add(new Light(tmpLight.getId(), tmpLight.isOn()));
            }
        });

        return lights;
    }

    public static List<Door> getAllDoorsExceptOneCopy(SmartHome smartHome, String doorId) {
        List<Door> doors = new ArrayList<>();

        smartHome.execute(doorCandidate -> {
            if (!(doorCandidate instanceof Door)) {
                return;
            }

            Door tmpDoor = (Door) doorCandidate;

            if (!tmpDoor.getId().equals(doorId)) {
                doors.add(new Door(tmpDoor.getId(), tmpDoor.isOpen()));
            }
        });

        return doors;
    }
}
