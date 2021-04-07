package ru.sbt.mipt.oop.handlers;

import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;

import static org.junit.Assert.assertThat;


public class EventLightHandlerTest {
    private HomeInitializer homeInitializer = new HomeJsonInitializer();
    private EventHandler handler = new EventLightHandler();

    @Test
    public void TestLightOnEvent() {

        SmartHome smartHome = homeInitializer.initializeHome();
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_ON, "4");
        handler.handleEvent(smartHome, sensorEvent);
        assert (getLightState(smartHome, "4"));
    }

    @Test
    public void TestLightOffEvent() {

        SmartHome smartHome = homeInitializer.initializeHome();
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_OFF, "4");
        handler.handleEvent(smartHome, sensorEvent);
        assert (!getLightState(smartHome, "4"));
    }

    private boolean getLightState(SmartHome smartHome, String id) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
                    return light.isOn();
                }
            }
        }
        return false;
    }
}
