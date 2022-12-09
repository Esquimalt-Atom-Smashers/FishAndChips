package org.firstinspires.ftc.teamcode.internal.util.functionality.driving;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Represents a mecanum drivebase.
 */
public class MecanumDrivebase extends Drivebase {
    /** Whether the joystick inputs are scaled or not */
    private boolean isScaled;

    public MecanumDrivebase(DcMotor frontLeft, DcMotor frontRight, DcMotor rearLeft, DcMotor rearRight, boolean isScaled) {
        super(frontLeft, frontRight, rearLeft, rearRight);
        this.isScaled = isScaled;
    }

    /**
     * Drives the robot
     *
     * @param strafe strafing power (left_joystick_x)
     * @param forward driving power (left_joystick_y)
     * @param turn turning power (right_joystick_x)
     */
    public void drive(double strafe, double forward, double turn) {
        if (isScaled) {
            motors[0].setPower(Math.pow(Range.clip(forward + strafe + turn, -1, 1), 3));
            motors[1].setPower(Math.pow(Range.clip(forward - strafe - turn, -1, 1), 3));
            motors[2].setPower(Math.pow(Range.clip(forward - strafe + turn, -1, 1), 3));
            motors[3].setPower(Math.pow(Range.clip(forward + strafe - turn, -1, 1), 3));
        }
        else {
            motors[0].setPower(Range.clip(forward + strafe + turn, -1, 1));
            motors[1].setPower(Range.clip(forward - strafe - turn, -1, 1));
            motors[2].setPower(Range.clip(forward - strafe + turn, -1, 1));
            motors[3].setPower(Range.clip(forward + strafe - turn, -1, 1));
        }
    }

    public void setScaled(boolean scaled) {
        isScaled = scaled;
    }

    public boolean isScaled() {
        return isScaled;
    }
}
