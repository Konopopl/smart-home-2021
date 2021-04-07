package ru.sbt.mipt.oop.alarm;

public class AlertAlarmState implements AlarmState {

    private Alarm alarm;
    private AlarmState state;

    public AlertAlarmState(Alarm alarm) {
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

    }

    @Override
    public void deactivateAlarm(String code) {
        if (alarm.checkCode(code)) {
            AlarmState state = new DeactivatedAlarmState(alarm);
            alarm.changeState(state);
            System.out.println("Alarm deactivated");
        } else {
            System.out.println("Wrong password entered!");
        }
    }
}
