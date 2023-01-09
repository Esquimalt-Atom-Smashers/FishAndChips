package teamcode.internal.events;

import android.util.EventLog;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class CustomGamepad {
    private Gamepad gamepad;

    private final EnumMap<Button, EventContainer> buttonEvents = new EnumMap<>(Button.class);

    public CustomGamepad(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public void register(Button button, ButtonState buttonState, ButtonEvent buttonEvent) {
        buttonEvents.get(button).addEventHandler(buttonState, buttonEvent);
    }

    public void handle() {
        for (Map.Entry<Button, EventContainer> entry : buttonEvents.entrySet()) {
            EventContainer eventContainer = buttonEvents.get(entry.getKey());
            eventContainer.nextState(entry.getKey().extract(gamepad)).handle();
        }
    }
}


