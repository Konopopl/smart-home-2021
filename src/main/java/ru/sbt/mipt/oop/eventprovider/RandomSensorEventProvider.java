package ru.sbt.mipt.oop.eventprovider;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.type.SensorEventType;

public class RandomSensorEventProvider implements SensorEventProvider {

    @Override
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        int typeNumber = (int) (6 * Math.random());
        SensorEventType sensorEventType = SensorEventType.values()[typeNumber];
        String objectId = "";
        if (typeNumber < 4) {
            objectId += ((int) (10 * Math.random()));
        } else {
            //alarm event
            objectId += ((int) (10 * Math.random())) % 2;
        }
        return new SensorEvent(sensorEventType, objectId);
    }
}
