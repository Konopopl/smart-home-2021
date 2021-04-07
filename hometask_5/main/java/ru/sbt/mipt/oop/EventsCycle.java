package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.decorators.AlarmDecorator;
import ru.sbt.mipt.oop.decorators.SmsDecorator;
import ru.sbt.mipt.oop.handlers.*;

import java.util.Arrays;
import java.util.List;

public class EventsCycle implements EventsCycles {

    private SensorCommandSender sensorCommandSender;
    private final List<EventHandler> handlers = Arrays.asList(new EventAlarmHandler(),
            new AlarmDecorator(new EventDoorHandler()),
            new AlarmDecorator(new EventLightHandler()),
            new AlarmDecorator(new EventHallDoorHandler(sensorCommandSender)),
            new SmsDecorator(new EventDoorHandler()),
            new SmsDecorator(new EventLightHandler()),
            new SmsDecorator(new EventHallDoorHandler(sensorCommandSender)));

    @Override
    public void runCycle(SmartHome smartHome) {
        SensorEvent event = EventGetter.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler : handlers) {
                handler.handleEvent(smartHome, event);
            }
            event = EventGetter.getNextSensorEvent();
        }
    }
}