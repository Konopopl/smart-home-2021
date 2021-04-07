package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.commands.*;
import ru.sbt.mipt.oop.decorators.AlarmDecorator;
import ru.sbt.mipt.oop.decorators.SmsDecorator;
import ru.sbt.mipt.oop.handlers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class SpringConfiguration {

    private SmartHome smartHome;
    private HomeInitializer homeInitializer;

    public SpringConfiguration() {
        this.homeInitializer = new HomeJsonInitializer();
        this.smartHome = homeInitializer.initializeHome();
    }

    @Bean
    @Description("SmartHome")
    SmartHome getSmartHome() {
        return this.smartHome;
    }

    @Bean
    EventHandler eventAlarmHandler() {
        return new SmsDecorator(new EventAlarmHandler());
    }

    @Bean
    EventHandler eventLightHandler() {
        return new AlarmDecorator(new EventLightHandler());
    }

    @Bean
    EventHandler eventDoorHandler() {
        return new AlarmDecorator(new EventDoorHandler());
    }


    @Bean
    public SensorEventsManager sensorEventsManager(List<EventHandler> eventHandlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        eventHandlers.forEach(eventHandler -> {
            sensorEventsManager.registerEventHandler(new EventHandlerAdapter(smartHome, eventHandler));
        });

        return sensorEventsManager;
    }

    @Bean
    public Command alarmActivateCommand(SmartHome smartHome) {
        return new AlarmActivateCommand(smartHome, "1234");
    }

    @Bean
    public Command alertActivateCommand(SmartHome smartHome) {
        return new AlertActivateCommand(smartHome);
    }

    @Bean
    public Command hallDoorCloseCommand(SmartHome smartHome) {
        return new HallDoorCloseCommand(smartHome);
    }

    @Bean
    public Command turnHallLightOnCommand(SmartHome smartHome) {
        return new TurnHallLightOnCommand(smartHome);
    }

    @Bean
    public Command turnLightOnCommand(SmartHome smartHome) {
        return new TurnLightOnCommand(smartHome);
    }

    @Bean
    public Command turnLightOffCommand(SmartHome smartHome) {
        return new TurnLightOffCommand(smartHome);
    }


    @Bean
    Map<String, Command> commandMap(Command alarmActivateCommand, Command alertActivateCommand, Command hallDoorCloseCommand,
                                    Command turnHallLightOnCommand, Command turnLightOnCommand, Command turnLightOffCommand) {
        return new HashMap<String, Command>() {{
            put("A", alarmActivateCommand);
            put("B", alertActivateCommand);
            put("C", hallDoorCloseCommand);
            put("D", turnHallLightOnCommand);
            put("E", turnLightOnCommand);
            put("F", turnLightOffCommand);
        }};
    }

    @Bean
    RemoteControlImplementation remoteControlImplementation(HashMap<String, Command> commandMap) {
        return new RemoteControlImplementation(commandMap,"1");
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(List<RemoteControlImplementation> remoteControlImplementationList) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlImplementationList.forEach(remoteControlImplementation -> {
            remoteControlRegistry.registerRemoteControl(remoteControlImplementation,remoteControlImplementation.getId());
        });
        return remoteControlRegistry;
    }
}
