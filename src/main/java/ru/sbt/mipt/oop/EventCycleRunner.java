package ru.sbt.mipt.oop;

import java.util.List;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.eventprovider.SensorEventProvider;

public class EventCycleRunner {
    private static final Logger logger = Logger.getLogger(EventCycleRunner.class);
    private final SensorEventProvider sensorEventProvider;
    private final List<EventHandler> eventHandlers;

    public EventCycleRunner(SensorEventProvider sensorEventProvider, List<EventHandler> eventHandlers) {
        this.sensorEventProvider = sensorEventProvider;
        this.eventHandlers = eventHandlers;
    }

    public void run(SmartHome smartHome) {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            logger.info("Got event: " + event);

            for (EventHandler handler : eventHandlers) {
                handler.handleEvent(smartHome, event);
            }

            event = sensorEventProvider.getNextSensorEvent();
        }
    }
}
