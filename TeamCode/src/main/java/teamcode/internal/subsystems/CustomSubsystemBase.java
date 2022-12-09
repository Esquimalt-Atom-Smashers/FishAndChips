package teamcode.internal.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Parent class for all subsystems.
 */
public abstract class CustomSubsystemBase {
    protected HardwareMap hardwareMap;
    protected Telemetry telemetry;

    public CustomSubsystemBase(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }
}
