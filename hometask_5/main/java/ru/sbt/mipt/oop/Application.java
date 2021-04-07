package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        // начинаем цикл обработки событий
        EventsCycles eventsCycles = new EventsCycle();
        eventsCycles.runCycle(smartHome);
    }

}
