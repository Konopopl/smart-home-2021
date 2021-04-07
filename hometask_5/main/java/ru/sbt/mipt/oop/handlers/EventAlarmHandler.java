package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Scanner;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class EventAlarmHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (sensorEvent.getType() == ALARM_ACTIVATE) {
            Scanner in = new Scanner(System.in);
            System.out.print("Input a password: ");
            String code = in.nextLine();
            smartHome.getAlarm().activateAlarm(code);
        }
        if (sensorEvent.getType() == ALARM_DEACTIVATE) {
            Scanner in = new Scanner(System.in);
            System.out.print("Input a password: ");
            String code = in.nextLine();
            smartHome.getAlarm().deactivateAlarm(code);
        }
    }
}
