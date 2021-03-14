package ru.sbt.mipt.oop.eventhandler;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.type.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.type.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
    private static final Logger logger = Logger.getLogger(LightEventHandler.class);

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (isLightEvent(event)) {
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            handleOnEvent(room, light);
                        } else {
                            handleOffEvent(room, light);
                        }
                    }
                }
            }
        }
    }

    private void handleOffEvent(Room room, Light light) {
        light.turnOff();
        logger.info("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }

    private void handleOnEvent(Room room, Light light) {
        light.turnOn();
        logger.info("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
