package org.firstinspires.ftc.teamcode.internal.util.functionality.intake;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Represents a dual motor intake system.
 */
public class DualMotorIntake {
    /** Intake motors, usually setup as left and right */
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    /** Speeds in which the intake release and intake */
    private double intakeSpeed = 0.5;
    private double releaseSpeed = -0.5;

    public DualMotorIntake(DcMotor leftMotor, DcMotor rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }

    /** Spins the motors too intake the object */
    public void intake() {
        leftMotor.setPower(intakeSpeed);
        rightMotor.setPower(intakeSpeed);
    }

    /** Spins the motors to release the object*/
    public void release() {
        leftMotor.setPower(releaseSpeed);
        rightMotor.setPower(releaseSpeed);
    }
}
