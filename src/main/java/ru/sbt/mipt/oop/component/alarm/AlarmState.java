package ru.sbt.mipt.oop.component.alarm;

public interface AlarmState {
    void activate(String code);

    void deactivate(String code);

    void toAlertMode();
}
