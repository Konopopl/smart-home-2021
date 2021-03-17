package ru.sbt.mipt.oop.component;

import java.util.Collection;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Room implements Actionable {
    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);

        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
    }
}
