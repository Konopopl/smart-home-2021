package ru.sbt.mipt.oop.decorators;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.handlers.EventHandler;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class SmsDecorator implements EventHandler {

    private EventHandler eventHandler;

    public SmsDecorator(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        Alarm alarm = smartHome.getAlarm();
        if (sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF ||
                sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED) {
            if (alarm.getState() instanceof ActivatedAlarmState) {
                System.out.println("SMS");
            }
            return;
        }
    }
}

