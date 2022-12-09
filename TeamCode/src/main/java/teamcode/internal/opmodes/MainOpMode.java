package org.firstinspires.ftc.teamcode.internal.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.internal.Robot;

@SuppressWarnings("unused")
@TeleOp(name="Main Opmode", group="Linear Opmode")
public class MainOpMode extends CommandOpMode {
    private Robot robot;

    @Override
    public void initialize() {
        Robot m_robot = new Robot(this);
    }
}
