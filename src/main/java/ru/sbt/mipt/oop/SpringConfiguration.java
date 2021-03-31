package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.sbt.mipt.oop.adapter.CCEventHandlerAdapter;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.eventhandler.SecurityEventHandlerDecorator;
import ru.sbt.mipt.oop.loader.SmartHomeJsonFileLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;
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
    public SensorEventsManager sensorEventsManager(List<EventHandler> eventHandlers, SmartHome smartHome, Map<String, SensorEventType> ccEventTypeToEventTypeMap) {
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
}
