package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.components.Door;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class EventDoorHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Door) {
                    Door door = (Door) object;
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            logOpen(door);
                        } else {
                           logClose(door);
                        }
                    }
                }
            });
        }
    }
    private void logOpen(Door door){
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " was opened.");
    }
    private void logClose(Door door){
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " was closed.");
    }
}
