package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Represents a claw that opens and closes via the control of a servo.
 */
public class ClawSubsystem extends CustomSubsystemBase {
    /** The minimum angle of the servo */
    private final int MINIMUM_ANGLE = 0;

    /** The maximum angle of the servo */
    private final int MAXIMUM_ANGLE = 270;

    /** The servo which controls the claw */
    private final ServoEx claw;

    /**
     * Initializes the claw servo and sets it to the proper configuration.
     *
     * @param hardwareMap the robots hardware map
     * @param telemetry the op-mode's telemetry
     */
    public ClawSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        claw = new SimpleServo(hardwareMap, "clawServo", MINIMUM_ANGLE, MAXIMUM_ANGLE, AngleUnit.DEGREES);
    }

    /**
     * Opens the claw by rotating the servo
     * Calls the {@link SimpleServo#rotateByAngle(double) method}
     */
    public void openClaw() {
        claw.turnToAngle(70);
    }

    /**
     * Closes the claw by rotating the servo
     * Calls the {@link SimpleServo#rotateByAngle(double) method}
     */
    public void closeClaw() {
        claw.turnToAngle(125);
    }
}
