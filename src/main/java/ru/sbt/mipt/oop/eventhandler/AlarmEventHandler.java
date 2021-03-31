package ru.sbt.mipt.oop.eventhandler;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.type.SensorEventType;

public class AlarmEventHandler implements EventHandler {
    private static final Logger logger = Logger.getLogger(AlarmEventHandler.class);

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {

        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.getAlarm().activate(event.getObjectId());
        }

        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getAlarm().deactivate(event.getObjectId());
        }
    }
}
