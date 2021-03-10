package ru.sbt.mipt.oop.eventhandler;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.component.Door;

import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    private static final Logger logger = Logger.getLogger(DoorEventHandler.class);

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) {
            return;
        }

        // событие от двери
        smartHome.execute(doorCandidate -> {
            if (!(doorCandidate instanceof Door)) {
                return;
            }

            Door door = (Door) doorCandidate;

            if (!door.getId().equals(event.getObjectId())) {
                return;
            }

            if (event.getType() == DOOR_OPEN) {
                handleOpenEvent(door);
            } else {
                handleClosedEvent(door);
            }
        });
    }


    private void handleClosedEvent(Door door) {
        door.close();
        logger.info("Door " + door.getId() + " was closed.");
    }

    private void handleOpenEvent(Door door) {
        door.open();
        logger.info("Door " + door.getId() + " was opened.");
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
