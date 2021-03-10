package ru.sbt.mipt.oop.command;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.type.CommandType;

public class TurnOnHallLightsCommand implements Command {
    private static final Logger logger = Logger.getLogger(TurnOnHallLightsCommand.class);

    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public TurnOnHallLightsCommand(SmartHome smartHome, CommandSender commandSender) {
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

            room.execute(lightCandidate -> {
                if (!(lightCandidate instanceof Light)) {
                    return;
                }

                Light light = (Light) lightCandidate;

                light.turnOn();
                logger.info("Light " + light.getId() + " was turned on.");

                SensorCommand sensorCommand = new SensorCommand(CommandType.LIGHT_ON, light.getId());
                commandSender.sendCommand(sensorCommand);
            });
        });
    }
}
