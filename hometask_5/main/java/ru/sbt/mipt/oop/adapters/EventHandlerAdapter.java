package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SmartHome;

public class EventHandlerAdapter implements EventHandler {

    private final SmartHome smartHome;
    private final ru.sbt.mipt.oop.handlers.EventHandler eventHandler;

    public EventHandlerAdapter(SmartHome smartHome, ru.sbt.mipt.oop.handlers.EventHandler eventHandler){
        this.smartHome = smartHome;
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventHandler.handleEvent(smartHome, new CCSensorEventAdapter(event));
    }
}
