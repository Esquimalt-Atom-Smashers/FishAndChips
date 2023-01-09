package teamcode.internal.events;

import java.util.HashMap;
import java.util.Map;

public class EventContainer {
    private ButtonState state;
    private Map<ButtonState, Event> eventHandlers = new HashMap<>();

    private static ButtonState generateNextState(boolean buttonInput, ButtonState oldState) {
        switch (oldState) {
            case OFF:
                if (buttonInput) {
                    return ButtonState.PRESSED;
                } else {
                    return ButtonState.OFF;
                }
            case PRESSED:
                if (buttonInput) {
                    return ButtonState.HELD;
                } else {
                    return ButtonState.RELEASED;
                }
            case HELD:
                if (buttonInput) {
                    return ButtonState.HELD;
                } else {
                    return ButtonState.RELEASED;
                }
            case RELEASED:
                if (buttonInput) {
                    return ButtonState.PRESSED;
                } else {
                    return ButtonState.OFF;
                }
            default:
                return ButtonState.OFF;
        }
    }

    public EventContainer addHandler(ButtonState event, Event listener) {
        eventHandlers.put(event, listener);
        return this;
    }

    public EventContainer handle() {
        if (eventHandlers.containsKey(state)) {
            eventHandlers.get(state).onEvent();
        }
        return this;
    }

    public EventContainer nextState(boolean buttonInput) {
        state = generateNextState(buttonInput, state);
        return this;
    }
}
