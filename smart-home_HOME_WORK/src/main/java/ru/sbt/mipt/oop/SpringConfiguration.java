package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import com.coolcompany.smarthome.rc.RemoteControlRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.sbt.mipt.oop.adapter.CCEventHandlerAdapter;
import ru.sbt.mipt.oop.command.*;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.eventhandler.SecurityEventHandlerDecorator;
import ru.sbt.mipt.oop.loader.SmartHomeJsonFileLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;
import ru.sbt.mipt.oop.remote.SmartHomeRemoteControl;
import ru.sbt.mipt.oop.type.SensorEventType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Import(SpringEventHandlerConfiguration.class)

@PropertySource("application.properties")
public class SpringConfiguration {

    @Bean
    public Map<String, SensorEventType> ccEventTypeToEventTypeMap() {
        return new HashMap<String, SensorEventType>() {{
            put("LightIsOn", SensorEventType.LIGHT_ON);
            put("LightIsOff", SensorEventType.LIGHT_OFF);
            put("DoorIsOpen", SensorEventType.DOOR_OPEN);
            put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        }};
    }

    @Bean
    public SmartHomeLoader smartHomeLoader(@Value("${smarthome.json.filename}") String filename) {
        return new SmartHomeJsonFileLoader(filename);
    }

    @Bean
    @Autowired
    public SmartHome smartHome(SmartHomeLoader smartHomeLoader) {
        return smartHomeLoader.loadSmartHome();
    }

    @Bean
    @Autowired
    public SensorEventsManager sensorEventsManager(
            List<EventHandler> eventHandlers,
            SmartHome smartHome,
            Map<String, SensorEventType> ccEventTypeToEventTypeMap) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        sensorEventsManager.registerEventHandler(
                new CCEventHandlerAdapter(
                        new SecurityEventHandlerDecorator(eventHandlers),
                        smartHome,
                        ccEventTypeToEventTypeMap
                )
        );

        return sensorEventsManager;
    }

    @Bean
    @Autowired
    public Command activateAlarmCommand(
            SmartHome smartHome,
            CommandSender commandSender,
            @Value("${smarthome.alarm.code}") String code) {

        return new ActivateAlarmCommand(smartHome, commandSender, code);
    }

    @Bean
    @Autowired
    public Command activateAlertModeCommand(SmartHome smartHome, CommandSender commandSender) {
        return new ActivateAlertModeCommand(smartHome, commandSender);
    }

    @Bean
    @Autowired
    public Command closeHallDoorCommand(SmartHome smartHome, CommandSender commandSender) {
        return new CloseHallDoorCommand(smartHome, commandSender);
    }

    @Bean
    @Autowired
    public Command turnOffAllLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        return new TurnOffAllLightsCommand(smartHome, commandSender);
    }

    @Bean
    @Autowired
    public Command turnOnAllLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        return new TurnOnAllLightsCommand(smartHome, commandSender);
    }

    @Bean
    @Autowired
    public Command turnOnHallLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        return new TurnOnHallLightsCommand(smartHome, commandSender);
    }

    @Bean
    @Autowired
    public Map<String, Command> myRcButtonsMapping(
            Command activateAlarmCommand,
            Command activateAlertModeCommand,
            Command closeHallDoorCommand,
            Command turnOffAllLightsCommand,
            Command turnOnAllLightsCommand,
            Command turnOnHallLightsCommand
    ) {
        return new HashMap<String, Command>() {{
            put("A", activateAlarmCommand);
            put("B", activateAlertModeCommand);
            put("C", closeHallDoorCommand);
            put("D", turnOffAllLightsCommand);
            put("1", turnOnAllLightsCommand);
            put("2", turnOnHallLightsCommand);
        }};

    }

    @Bean
    @Autowired
    public SmartHomeRemoteControl myRemoteControl(Map<String, Command> rcButtonsMapping, @Value("${smarthome.rc.myId}") String id) {
        return new SmartHomeRemoteControl(rcButtonsMapping, id);
    }

    @Bean
    @Autowired
    public RemoteControlRegistry remoteControlRegistry(List<SmartHomeRemoteControl> remoteControls) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControls.forEach(remoteControl -> remoteControlRegistry.registerRemoteControl(remoteControl, remoteControl.getRcId()));
        return new RemoteControlRegistry();
    }
}
