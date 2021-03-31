package ru.sbt.mipt.oop.component.alarm;

import org.apache.log4j.Logger;

public class OnAlertModeAlarmState implements AlarmState {
    private static final Logger logger = Logger.getLogger(OnAlertModeAlarmState.class);
    private final Alarm alarm;

    public OnAlertModeAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        //already activated
        logger.info("Alarm has been already activated.");
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new DeactivatedAlarmState(alarm));
            logger.info("Alarm successfully deactivated.");
        } else {
            logger.info("Wrong code.");
        }
    }

    @Override
    public void toAlertMode() {
        //already on alert mode
    }
}
