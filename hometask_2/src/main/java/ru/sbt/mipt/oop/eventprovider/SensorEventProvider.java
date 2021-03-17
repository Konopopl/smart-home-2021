package ru.sbt.mipt.oop.eventprovider;

import ru.sbt.mipt.oop.SensorEvent;

public interface SensorEventProvider {
    SensorEvent getNextSensorEvent();
}
