package teamcode.internal.events;

import com.qualcomm.robotcore.hardware.Gamepad;

//TODO: Add all buttons
public enum Button {
    A {
        @Override
        public boolean extract(Gamepad gamepad) {
            return gamepad.a;
        }
    };

    public abstract boolean extract(Gamepad gamepad);

}
