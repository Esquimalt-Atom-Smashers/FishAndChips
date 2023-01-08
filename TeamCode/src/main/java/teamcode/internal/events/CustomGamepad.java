package teamcode.internal.events;


import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


public class CustomGamepad {

    private Map<Button, EventContainer> buttonEvents = new EnumMap<>(Button.class);
    private List<Event> events = new ArrayList<>();

    private Gamepad gamepad;

    public CustomGamepad(Gamepad gamepad) {
        this.gamepad = gamepad;

        for (Button button : Button.values()) {
            buttonEvents.put(button, new EventContainer());
        }
    }

    public void setOnPressed(Button button, Event event) {
        buttonEvents.get(button).addHandler(ButtonState.PRESSED, event);
    }

    public void setOnHeld(Button button, Event event) {
        buttonEvents.get(button).addHandler(ButtonState.HELD, event);
    }

    public void setOnReleased(Button button, Event event) {
        buttonEvents.get(button).addHandler(ButtonState.RELEASED, event);
    }

    public void setDefaultEvent(Event event) {
        events.add(event);
    }

    public void handleEvents() throws InterruptedException {

        CountDownLatch waiter = new CountDownLatch(2);

        waiter.await();

        ((Runnable) () -> {
            for (Map.Entry<Button, EventContainer> entry : buttonEvents.entrySet()) {
                buttonEvents.get(entry.getKey()).nextState(entry.getKey().extract(gamepad)).handle();
            }
            waiter.countDown();
        }).run();

        ((Runnable) () -> {
            for (Event event : events) {
                events.add(event);
            }
            waiter.countDown();
        }).run();

    }
}

