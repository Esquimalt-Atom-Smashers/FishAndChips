package teamcode.internal.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Represents a claw that opens and closes via the control of a servo.
 */
public class ClawSubsystem extends CustomSubsystemBase {
    /** The servo which controls the claw */
    private final Servo claw;

    /**
     * Initializes the claw servo and sets it to the proper configuration.
     *
     * @param hardwareMap the robots hardware map
     * @param telemetry the op-mode's telemetry
     */
    public ClawSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        claw = hardwareMap.get(Servo.class, "clawServo");
    }

    public void openClaw() {
        claw.setPosition(0);
    }

    public void closeClaw() {
        claw.setPosition(1);
    }
}
