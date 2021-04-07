package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.components.Light;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class EventLightHandler implements EventHandler {
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            smartHome.execute(object ->
            {
                if (object instanceof Light) {
                    Light light = (Light) object;
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            logOn(light);
                        } else {
                            logOff(light);
                        }
                    }
                }
            });
        }
    }
    private void logOff(Light light){
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off.");
    }
    private void logOn(Light light){
        light.setOn(true);
        System.out.println("Light " + light.getId() + " was turned on.");
    }
}
