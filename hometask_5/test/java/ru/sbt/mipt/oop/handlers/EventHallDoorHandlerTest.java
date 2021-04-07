package ru.sbt.mipt.oop.handlers;

import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;

public class EventHallDoorHandlerTest {
    private final SensorCommandSender sensorCommandSender = new SensorCommandSender();
    private HomeInitializer homeInitializer = new HomeJsonInitializer();
    private EventHandler handler = new EventHallDoorHandler(sensorCommandSender);

    @Test
    public void TestDoorCloseEvent() {

        SmartHome smartHome = homeInitializer.initializeHome();
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        handler.handleEvent(smartHome, sensorEvent);
        assert (getLightsState(smartHome, "4"));
    }

    private boolean getLightsState(SmartHome smartHome, String id) {
        boolean state = true;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.isOn()) {
                    state = false;
                }
            }
        }
        return state;
    }
}