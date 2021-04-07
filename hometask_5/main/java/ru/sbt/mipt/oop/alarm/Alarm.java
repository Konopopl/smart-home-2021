package ru.sbt.mipt.oop.alarm;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm() {
        this.state = new DeactivatedAlarmState(this);
    }

    public AlarmState getState() {
        return state;
    }

    public void changeState(AlarmState newState) {
        this.state = newState;
    }

    public void alert() {
        state.alert();
    }

    public void activateAlarm(String code) {
        state.activateAlarm(code);
    }

    public void deactivateAlarm(String code) {
        state.deactivateAlarm(code);
    }

    public boolean checkCode(String enteredCode) {
        return this.code.equals(enteredCode);
    }

    public void setCode(String newCode) {
        code = newCode;
    }
}
