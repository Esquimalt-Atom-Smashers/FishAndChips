package teamcode.internal.events;

import com.qualcomm.robotcore.hardware.Gamepad;

public enum Button {
    A {
        @Override
        public boolean extract(Gamepad gamepad) {
            return gamepad.a;
        }
    },

    B {
        @Override
        public boolean extract(Gamepad gamepad) {
            return gamepad.b;
        }
    },

    X {
        @Override
        public boolean extract(Gamepad gamepad) {
            return gamepad.x;
        }
    },

    Y {
        @Override
        public boolean extract(Gamepad gamepad) {
            return gamepad.y;
        }
    };

    public abstract boolean extract(Gamepad gamepad);

}
