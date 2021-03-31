package ru.sbt.mipt.oop.component;

import java.util.Objects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Light implements Actionable {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void turnOff() {
        isOn = false;
    }

    public void turnOn() {
        isOn = true;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Light light = (Light) o;
        return isOn == light.isOn &&
                id.equals(light.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOn, id);
    }
}
