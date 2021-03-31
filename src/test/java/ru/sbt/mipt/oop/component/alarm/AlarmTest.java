package ru.sbt.mipt.oop.component.alarm;

import org.junit.Assert;
import org.junit.Test;

public class AlarmTest {

    @Test
    public void activateAlarmFirstTimeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");

        Assert.assertTrue(alarm.isActivated());
    }

    @Test
    public void onAlertModeFromActivatedAlarmTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");

        alarm.toAlertMode();

        Assert.assertTrue(alarm.isOnAlertMode());
    }

    @Test
    public void deactivateActivatedAlarmWithRightCodeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");

        alarm.deactivate("test");

        Assert.assertFalse(alarm.isActivated());
    }

    @Test
    public void deactivateActivatedAlarmWithWrongCodeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");

        alarm.deactivate("not_test");

        Assert.assertTrue(alarm.isOnAlertMode());
    }

    @Test
    public void deactivateOnAlertModeAlarmWithRightCodeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");
        alarm.toAlertMode();

        alarm.deactivate("test");

        Assert.assertFalse(alarm.isActivated());
    }

    @Test
    public void deactivateOnAlertModeAlarmWithWrongCodeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");
        alarm.toAlertMode();

        alarm.deactivate("not_test");

        Assert.assertTrue(alarm.isOnAlertMode());
    }

}