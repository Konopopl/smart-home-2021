package ru.sbt.mipt.oop.eventhandler;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.type.SensorEventType;

import java.util.List;

public class SecurityEventHandlerDecorator implements EventHandler {
    private static final Logger logger = Logger.getLogger(SecurityEventHandlerDecorator.class);

    private final List<EventHandler> eventHandlers;

    public SecurityEventHandlerDecorator(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {

        if (smartHome.getAlarm().isOnAlertMode() && !isAlarmEvent(event)) {
            // may be there will be some logic later
            logger.error("Sensor detection while alert mode is on.");
            logger.info("Sending sms.");
            return;
        }

        eventHandlers.forEach(handler -> handler.handleEvent(smartHome, event));

        if (smartHome.getAlarm().isActivated()) {
            smartHome.getAlarm().toAlertMode();
            logger.error("Sensor detection while alarm is on. Turn on alert mode.");
            logger.info("Sending sms.");
        }
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_DEACTIVATE || event.getType() == SensorEventType.ALARM_ACTIVATE;
    }
}
