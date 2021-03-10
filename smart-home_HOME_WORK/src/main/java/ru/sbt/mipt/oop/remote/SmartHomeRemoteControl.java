package ru.sbt.mipt.oop.remote;

import com.coolcompany.smarthome.rc.RemoteControl;
import ru.sbt.mipt.oop.command.Command;

import java.util.Map;

public class SmartHomeRemoteControl implements RemoteControl {
    private final Map<String, Command> rcButtonToCommandMap;
    private final String rcId;

    public SmartHomeRemoteControl(Map<String, Command> rcButtonToCommandMap, String rcId) {
        this.rcButtonToCommandMap = rcButtonToCommandMap;
        this.rcId = rcId;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (!rcId.equals(this.rcId)) {
            return;
        }

        if (rcButtonToCommandMap.containsKey(buttonCode)) {
            rcButtonToCommandMap.get(buttonCode).execute();
        }
    }

    public String getRcId() {
        return rcId;
    }
}
