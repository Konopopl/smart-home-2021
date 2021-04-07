package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;

public class TurnHallLightOnCommand implements Command {
    private SmartHome smartHome;

    public TurnHallLightOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(light -> {
                        if (light instanceof Light) {
                            ((Light) light).setOn(true);
                        }
                    });
                }
            }
        });
    }
}
