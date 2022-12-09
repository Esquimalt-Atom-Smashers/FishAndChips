package teamcode.internal.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * A light subsystem which controls the LEDs on the robot
 */
public class LightSubsystem extends CustomSubsystemBase {
    /** The LEDs under the robot */
    private final DcMotor underGlow;

    /** The LEDs on the arm of the robot */
    private final DcMotor armGlow;

    /** Constants which control how bright the LEDs are */
    private final double UNDER_GLOW_BRIGHTNESS = 1;
    private final double ARM_GLOW_BRIGHTNESS = 1;

    /** Enum for distinguishing between the two different LEDs on the robot */
    public enum LightType {
        UNDER_GLOW,
        ARM_GLOW
    }

    /**
     * Initializes the LEDs and sets them to the proper configurations.
     *
     * @param hardwareMap the robots hardware map
     * @param telemetry the op-mode's telemetry
     */
    public LightSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        underGlow = hardwareMap.get(DcMotor.class, "underGlow");
        armGlow = hardwareMap.get(DcMotor.class, "armGlow");
    }

    /**
     * Turns the given LED on.
     *
     * @param lightType the LED to be turned on
     */
    public void on(LightType lightType) {
        if (lightType.equals(LightType.UNDER_GLOW)) {
            underGlow.setPower(UNDER_GLOW_BRIGHTNESS);
        }
        if (lightType.equals(LightType.ARM_GLOW)) {
            armGlow.setPower(ARM_GLOW_BRIGHTNESS);
        }
    }

    /**
     * Turns the given LED off.
     *
     * @param lightType the LED to be turned off
     */
    public void off(LightType lightType) {
        if (lightType.equals(LightType.UNDER_GLOW)) {
            underGlow.setPower(0);
        }
        else {
            armGlow.setPower(0);
        }
    }

}
