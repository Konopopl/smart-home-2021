package ru.sbt.mipt.oop.command;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.type.CommandType;

public class ActivateAlarmCommand implements Command {
    private static final Logger logger = Logger.getLogger(ActivateAlarmCommand.class);

    private final SmartHome smartHome;
    private final CommandSender commandSender;
    private final String code;

    public ActivateAlarmCommand(SmartHome smartHome, CommandSender commandSender, String code) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(code);
        SensorCommand sensorCommand = new SensorCommand(CommandType.ALARM_ACTIVATE, code);
        commandSender.sendCommand(sensorCommand);
    }
}
