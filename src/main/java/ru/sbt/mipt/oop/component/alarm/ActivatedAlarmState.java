package ru.sbt.mipt.oop.component.alarm;

import org.apache.log4j.Logger;

public class ActivatedAlarmState implements AlarmState {
    private static final Logger logger = Logger.getLogger(ActivatedAlarmState.class);
    private final Alarm alarm;

    public ActivatedAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        //already activated
        logger.info("Alarm has been already activated. Turn on alert mode");
        alarm.setState(new OnAlertModeAlarmState(alarm));
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new DeactivatedAlarmState(alarm));
            logger.info("Alarm successfully deactivated.");
        } else {
            toAlertMode();
            logger.info("Wrong code. Turn on alert mode");
        }
    }

    @Override
    public void toAlertMode() {
        alarm.setState(new OnAlertModeAlarmState(alarm));
    }
}
