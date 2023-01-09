package teamcode.internal.events;

import java.util.HashMap;

public class EventContainer {
    private ButtonState state;

    private final HashMap<ButtonState, ButtonEvent> eventHandlers = new HashMap<>();

    private static ButtonState generateState(boolean input, ButtonState oldState) {
        switch (oldState) {
            case OFF:
                return input ? ButtonState.PRESSED : ButtonState.OFF;
            case PRESSED:
                return input ? ButtonState.HELD : ButtonState.OFF;
            case HELD:
                return input ? ButtonState.HELD : ButtonState.RELEASED;
            case RELEASED:
                return input ? ButtonState.PRESSED : ButtonState.OFF;
            default:
                return ButtonState.OFF;
        }
    }

    public EventContainer addEventHandler(ButtonState state, ButtonEvent event) {
        eventHandlers.put(state, event);
        return this;
    }

    public void handle() {

    }

    public EventContainer nextState(boolean input) {
        state = generateState(input, state);
        return this;
    }

}
