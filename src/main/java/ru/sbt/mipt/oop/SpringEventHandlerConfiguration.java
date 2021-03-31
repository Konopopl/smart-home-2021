package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.commandsender.FakeCommandSender;
import ru.sbt.mipt.oop.eventhandler.*;

@Configuration
public class SpringEventHandlerConfiguration {
    @Bean
    CommandSender fakeCommandSender() {
        return new FakeCommandSender();
    }

    @Bean
    EventHandler alarmEventHandler() {
        return new AlarmEventHandler();
    }

    @Bean
    EventHandler doorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    @Autowired
    EventHandler hallDoorEventHandler(CommandSender commandSender) {
        return new HallDoorEventHandler(commandSender);
    }

    @Bean
    EventHandler lightEventHandler() {
        return new LightEventHandler();
    }
}
