package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.type.SensorEventType;

import java.util.Map;


public class CCEventHandlerAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.eventhandler.EventHandler eventHandler;
    private final SmartHome smartHome;
    private final Map<String, SensorEventType> ccEventTypeToEventTypeMap;

    public CCEventHandlerAdapter(ru.sbt.mipt.oop.eventhandler.EventHandler eventHandler, SmartHome smartHome, Map<String, SensorEventType> ccEventTypeToEventTypeMap) {
        this.eventHandler = eventHandler;
        this.smartHome = smartHome;
        this.ccEventTypeToEventTypeMap = ccEventTypeToEventTypeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = new SensorEvent(
                ccEventTypeToEventTypeMap.getOrDefault(event.getEventType(), SensorEventType.UNSUPPORTED_EVENT),
                event.getObjectId()
        );

        eventHandler.handleEvent(smartHome, sensorEvent);
    }
}
