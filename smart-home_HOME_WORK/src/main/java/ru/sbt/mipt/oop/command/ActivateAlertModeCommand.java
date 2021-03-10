package ru.sbt.mipt.oop.command;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.type.CommandType;

public class ActivateAlertModeCommand implements Command {
    private static final Logger logger = Logger.getLogger(ActivateAlertModeCommand.class);

    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public ActivateAlertModeCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().toAlertMode();
        SensorCommand sensorCommand = new SensorCommand(CommandType.ALERT_MODE_ON, "");
        commandSender.sendCommand(sensorCommand);
    }
}
