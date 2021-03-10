package ru.sbt.mipt.oop.command;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.type.CommandType;

public class CloseHallDoorCommand implements Command {
    private static final Logger logger = Logger.getLogger(CloseHallDoorCommand.class);

    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public CloseHallDoorCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.execute(roomCandidate -> {
            if (!(roomCandidate instanceof Room)) {
                return;
            }

            Room room = (Room) roomCandidate;

            if (!room.getName().equals("hall")) {
                return;
            }

            room.execute(doorCandidate -> {
                if (!(doorCandidate instanceof Door)) {
                    return;
                }
                Door door = (Door) doorCandidate;

                door.close();

                logger.info("Door " + door.getId() + " was closed.");

                SensorCommand sensorCommand = new SensorCommand(CommandType.DOOR_CLOSED, door.getId());
                commandSender.sendCommand(sensorCommand);
            });
        });
    }
}
