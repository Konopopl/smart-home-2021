package ru.sbt.mipt.oop.commandsender;

import ru.sbt.mipt.oop.SensorCommand;

public interface CommandSender {
    void sendCommand(SensorCommand command);
}
