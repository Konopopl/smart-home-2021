package ru.sbt.mipt.oop.eventhandler;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Room;

import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    private static final Logger logger = Logger.getLogger(DoorEventHandler.class);

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (isDoorEvent(event)) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            handleOpenEvent(room, door);
                        } else {
                            handleClosedEvent(room, door);
                        }
                    }
                }
            }
        }
    }

    private void handleClosedEvent(Room room, Door door) {
        door.close();
        logger.info("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }

    private void handleOpenEvent(Room room, Door door) {
        door.open();
        logger.info("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
