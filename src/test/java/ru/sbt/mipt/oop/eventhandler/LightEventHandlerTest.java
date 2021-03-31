package ru.sbt.mipt.oop.eventhandler;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.component.alarm.Alarm;
import ru.sbt.mipt.oop.type.SensorEventType;

public class LightEventHandlerTest {

    private SmartHome smartHome;
    private LightEventHandler lightEventHandler;

    @Before
    public void setup() {
        Room testRoom = new Room(
                Arrays.asList(
                        new Light("1", false),
                        new Light("2", true),
                        new Light("3", false),
                        new Light("4", false)),
                Arrays.asList(
                        new Door("1", false),
                        new Door("2", false)),
                "tesRoom");

        smartHome = new SmartHome(new Alarm(), Arrays.asList(testRoom));
        lightEventHandler = new LightEventHandler();
    }

    @Test
    public void turnOnExistingLightEventTest() {
        final String lightId = "1";
        TestUtils.getLightById(smartHome, lightId).turnOff();
        List<Light> initialAllLightsCopy = TestUtils.getAllLightsExceptOneCopy(smartHome, lightId);

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
        lightEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllLightsCopy, TestUtils.getAllLightsExceptOneCopy(smartHome, lightId));
        Assert.assertTrue(TestUtils.getLightById(smartHome, lightId).isOn());
    }

    @Test
    public void turnOffExistingLightEventTest() {
        final String lightId = "1";
        TestUtils.getLightById(smartHome, lightId).turnOn();
        List<Light> initialAllLightsCopy = TestUtils.getAllLightsExceptOneCopy(smartHome, lightId);

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);
        lightEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllLightsCopy, TestUtils.getAllLightsExceptOneCopy(smartHome, lightId));
        Assert.assertFalse(TestUtils.getLightById(smartHome, lightId).isOn());
    }

    @Test
    public void turnOnNonExistingLightEventTest() {
        final String lightId = "100";
        List<Light> initialAllLightsCopy = TestUtils.getAllLightsCopy(smartHome);

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
        lightEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllLightsCopy, TestUtils.getAllLightsCopy(smartHome));
    }

    @Test
    public void turnOffNonExistingLightEventTest() {
        final String lightId = "100";
        List<Light> initialAllLightsCopy = TestUtils.getAllLightsCopy(smartHome);

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);
        lightEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllLightsCopy, TestUtils.getAllLightsCopy(smartHome));
    }

    @Test
    public void handleNotLightEventTest() {
        List<Light> initialAllLightsCopy = TestUtils.getAllLightsCopy(smartHome);

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        lightEventHandler.handleEvent(smartHome, event);

        Assert.assertEquals(initialAllLightsCopy, TestUtils.getAllLightsCopy(smartHome));
    }
}