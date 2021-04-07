package ru.sbt.mipt.oop.decorators;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.alarm.DeactivatedAlarmState;
import ru.sbt.mipt.oop.handlers.EventHandler;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmDecorator implements EventHandler {

    private EventHandler eventHandler;

    public AlarmDecorator(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        Alarm alarm = smartHome.getAlarm();

        if (alarm.getState() instanceof ActivatedAlarmState) {
            eventHandler.handleEvent(smartHome, sensorEvent);
            if (sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF ||
                    sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED) {
                alarm.alert();
            }
        } else if (alarm.getState() instanceof DeactivatedAlarmState) {
            eventHandler.handleEvent(smartHome, sensorEvent);
        } else {
            return;
        }
    }
}
