package ru.sbt.mipt.oop.alarm;

public class DeactivatedAlarmState implements AlarmState {
    private Alarm alarm;
    private AlarmState state;

    public DeactivatedAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public AlarmState getState() {
        return state;
    }

    @Override
    public void alert() {

    }

    @Override
    public void activateAlarm(String code) {
        AlarmState state = new ActivatedAlarmState(alarm);
        alarm.changeState(state);
        System.out.println("Alarm activated");
    }

    @Override
    public void deactivateAlarm(String code) {
        System.out.println("Already deactivated.");
    }
}
