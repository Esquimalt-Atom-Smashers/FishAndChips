package teamcode.internal.subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Represents a claw that opens and closes via the control of a servo.
 */
public class ClawSubsystem extends CustomSubsystemBase {
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
        claw = new SimpleServo(hardwareMap, "clawServo", 0, 270, AngleUnit.DEGREES);
    }

    public void openClaw() {
        claw.turnToAngle(120);
    }

    public void closeClaw() {
        claw.turnToAngle(3);
    }

    public ServoEx getClaw() {
        return claw;
    }
}