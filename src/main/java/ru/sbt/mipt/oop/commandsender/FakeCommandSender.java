package ru.sbt.mipt.oop.commandsender;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorCommand;

public class FakeCommandSender implements CommandSender {
    private static final Logger logger = Logger.getLogger(FakeCommandSender.class);

    @Override
    public void sendCommand(SensorCommand command) {
        logger.info("Pretent we're sending command " + command);
    }
}
