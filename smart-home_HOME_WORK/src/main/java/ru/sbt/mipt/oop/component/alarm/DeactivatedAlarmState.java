package ru.sbt.mipt.oop.component.alarm;

import org.apache.log4j.Logger;

public class DeactivatedAlarmState implements AlarmState {
    private static final Logger logger = Logger.getLogger(DeactivatedAlarmState.class);
    private final Alarm alarm;

    public DeactivatedAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.setCode(code);
        alarm.setState(new ActivatedAlarmState(alarm));
        logger.info("Alarm activated.");
    }

    @Override
    public void deactivate(String code) {
        //already deactivated
        logger.info("Alarm has been already deactivated.");
    }

    @Override
    public void toAlertMode() {
        //can not set Alert mode from this state
    }
}
