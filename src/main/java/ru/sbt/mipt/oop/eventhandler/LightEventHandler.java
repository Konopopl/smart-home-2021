package ru.sbt.mipt.oop.eventhandler;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.component.Light;

import static ru.sbt.mipt.oop.type.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.type.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
    private static final Logger logger = Logger.getLogger(LightEventHandler.class);

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) {
            return;
        }
        // событие от источника света
        smartHome.execute(lightCandidate -> {
            if (!(lightCandidate instanceof Light)) {
                return;
            }

            Light light = (Light) lightCandidate;

            if (!light.getId().equals(event.getObjectId())) {
                return;
            }

            if (event.getType() == LIGHT_ON) {
                handleOnEvent(light);
            } else {
                handleOffEvent(light);
            }

        });
    }


    private void handleOffEvent(Light light) {
        light.turnOff();
        logger.info("Light " + light.getId() + " was turned off.");
    }

    private void handleOnEvent(Light light) {
        light.turnOn();
        logger.info("Light " + light.getId() + " was turned on.");
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
