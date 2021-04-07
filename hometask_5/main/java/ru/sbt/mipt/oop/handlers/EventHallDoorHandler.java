package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class EventHallDoorHandler implements EventHandler {

    private SensorCommandSender sensorCommandSender;

    public EventHallDoorHandler(SensorCommandSender sensorCommandSender) {
        this.sensorCommandSender = sensorCommandSender;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    if (room.getName().equals("hall")) {
                        room.execute(doorP -> {
                            if (doorP instanceof Door) {
                                Door door = (Door) doorP;
                                if (door.getId().equals(event.getObjectId())) {
                                    smartHome.execute(component -> {
                                        if (component instanceof Light) {
                                            Light light = (Light) component;
                                            light.setOn(false);

                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            sensorCommandSender.sendCommand(command);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}