package org.firstinspires.ftc.teamcode.internal.util.functionality.driving;

import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Drivebase {

    DcMotor[] motors;

    public Drivebase(DcMotor frontLeft, DcMotor frontRight, DcMotor rearLeft, DcMotor rearRight) {
        motors = new DcMotor[]{frontLeft, frontRight, rearLeft, rearRight};
    }

    public DcMotor[] getMotors() {
        return motors;
    }


}
