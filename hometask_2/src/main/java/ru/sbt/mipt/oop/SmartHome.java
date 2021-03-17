package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

import ru.sbt.mipt.oop.component.Room;

public class SmartHome implements Actionable {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
        action.accept(this);

        rooms.forEach(room -> room.execute(action));
    }
}
