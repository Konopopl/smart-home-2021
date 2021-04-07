package ru.sbt.mipt.oop.alarm;

public interface AlarmState {
    AlarmState getState();

    void alert();

    void activateAlarm(String code);

    void deactivateAlarm(String code);
}
