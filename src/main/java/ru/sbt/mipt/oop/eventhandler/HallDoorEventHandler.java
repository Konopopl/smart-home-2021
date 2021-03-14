package ru.sbt.mipt.oop.eventhandler;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.type.CommandType;

import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_OPEN;

public class HallDoorEventHandler implements EventHandler {
    private static final Logger logger = Logger.getLogger(HallDoorEventHandler.class);
    private final CommandSender commandSender;

    public HallDoorEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (isDoorEvent(event) && isHallDoorEvent(smartHome, event)) {
            if (event.getType() == DOOR_CLOSED) {
                handleClosedEvent(smartHome);
            }
        }
    }

    private void handleClosedEvent(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.turnOff();
                logger.info("Light " + light.getId() + " in room " + room.getName() + " was turned off.");

                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN;
    }

    private boolean isHallDoorEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (room.getName().equals("hall")) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
