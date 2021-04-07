package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.commands.Command;

import java.util.HashMap;

public class RemoteControlImplementation implements RemoteControl {

    public HashMap<String, Command> commandHashMap;
    public String id;

    public RemoteControlImplementation(HashMap<String, Command> map, String id) {
        this.commandHashMap = map;
        this.id = id;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (id.equals(rcId) && commandHashMap.containsKey(buttonCode)) {
            commandHashMap.get(buttonCode).execute();
        }
    }

    public String getId() {
        return id;
    }
}
