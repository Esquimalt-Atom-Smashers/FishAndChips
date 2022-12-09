package org.firstinspires.ftc.teamcode.internal.util.functionality.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Represents a claw controlled by a single servo.
 */
public class SingleServoClaw {
    /** Servo which controls the claw*/
    private Servo servo ;

    /** Claw open and closed positions*/
    private double openPosition = 0.0;
    private double closedPosition = 0.0;

    public SingleServoClaw(HardwareMap hardwareMap, double openPosition, double closedPosition) {
        servo = hardwareMap.get(Servo.class, "clawServo");

        this.openPosition = openPosition;
        this.closedPosition = closedPosition;
    }

    /** Closes the claw */
    public void closeClaw() {
        servo.setPosition(closedPosition);
    }

    /** Opens the claw */
    public void openClaw() {
        servo.setPosition(openPosition);
    }

    public void setOpenPosition(double openPosition) {
        this.openPosition = openPosition;
    }

    public void setClosedPosition(double closedPosition) {
        this.closedPosition = closedPosition;
    }
}
