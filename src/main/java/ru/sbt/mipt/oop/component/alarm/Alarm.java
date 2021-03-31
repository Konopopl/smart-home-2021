package ru.sbt.mipt.oop.component.alarm;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm() {
        this.state = new DeactivatedAlarmState(this);
        this.code = "";
    }

    public boolean isActivated() {
        return !(state instanceof DeactivatedAlarmState);
    }

    public boolean isOnAlertMode() {
        return state instanceof OnAlertModeAlarmState;
    }

    void setState(AlarmState state) {
        this.state = state;
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void toAlertMode() {
        state.toAlertMode();
    }
}
