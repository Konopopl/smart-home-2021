package ru.sbt.mipt.oop.command;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.type.CommandType;

public class TurnOffAllLightsCommand implements Command {
    private static final Logger logger = Logger.getLogger(TurnOffAllLightsCommand.class);

    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public TurnOffAllLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.execute(lightCandidate -> {
            if (!(lightCandidate instanceof Light)) {
                return;
            }

            Light light = (Light) lightCandidate;

            light.turnOff();
            logger.info("Light " + light.getId() + " was turned off.");

            SensorCommand sensorCommand = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(sensorCommand);
        });
    }
}
