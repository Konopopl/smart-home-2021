package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

import java.util.HashMap;

public class CCSensorEventAdapter extends SensorEvent {

    private static HashMap<String, SensorEventType> sensorEventTypes = new HashMap<String, SensorEventType>() {{
        put("LightIsOn", SensorEventType.LIGHT_ON);
        put("LightIsOff", SensorEventType.LIGHT_OFF);
        put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        put("DoorIsLocked", null);
        put("DoorIsUnlocked", null);
    }};

    public CCSensorEventAdapter(CCSensorEvent event) {
        super(sensorEventTypes.get(event.getEventType()), event.getObjectId());
    }
}
