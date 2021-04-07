package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmState;
import ru.sbt.mipt.oop.alarm.AlertAlarmState;

public class AlertActivateCommand implements Command {

    private SmartHome smartHome;
    public AlertActivateCommand(SmartHome smartHome){
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        Alarm alarm = smartHome.getAlarm();
        AlarmState alertState = new AlertAlarmState(alarm);
        alarm.changeState(alertState);
    }
}
