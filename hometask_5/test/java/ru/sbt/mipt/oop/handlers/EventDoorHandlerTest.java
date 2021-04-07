package ru.sbt.mipt.oop.handlers;

import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;

public class EventDoorHandlerTest {
    private HomeInitializer homeInitializer = new HomeJsonInitializer();
    private EventHandler handler = new EventDoorHandler();

    @Test
    public void TestDoorOpenEvent() {

        SmartHome smartHome = homeInitializer.initializeHome();
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "4");
        handler.handleEvent(smartHome, sensorEvent);
        assert (getDoorState(smartHome, "4"));
    }

    @Test
    public void TestDoorCloseEvent() {

        SmartHome smartHome = homeInitializer.initializeHome();
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        handler.handleEvent(smartHome, sensorEvent);
        assert (!getDoorState(smartHome, "4"));
    }

    private boolean getDoorState(SmartHome smartHome, String id) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    return door.getState();
                }
            }
        }
        return false;
    }
}
