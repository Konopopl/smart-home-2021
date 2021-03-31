package ru.sbt.mipt.oop.component;

import java.util.Objects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void close() {
        isOpen = false;
    }

    public void open() {
        isOpen = true;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Door door = (Door) o;
        return isOpen == door.isOpen &&
                id.equals(door.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isOpen);
    }
}
