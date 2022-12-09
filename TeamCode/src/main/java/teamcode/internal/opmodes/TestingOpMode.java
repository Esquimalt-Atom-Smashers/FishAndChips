package org.firstinspires.ftc.teamcode.internal.opmodes;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.internal.Robot;

@SuppressWarnings("unused")
@TeleOp(name="TestingOpMode", group="Linear Opmode")
public class TestingOpMode extends LinearOpMode {

    BNO055IMU imu;

    Orientation angle;
    Servo servo;

    @Override
    public void runOpMode() throws InterruptedException {
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
//        imu.initialize(parameters);

        servo = hardwareMap.get(Servo.class, "actuator");

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
//            angle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//            telemetry.addData("Heading: ", angle.firstAngle);
//            telemetry.addData("Roll: ", angle.secondAngle);
//            telemetry.addData("Pitch: ", angle.thirdAngle);
//            telemetry.update();
            if (gamepad1.a) {
                servo.setPosition(servo.getPosition() + 10);
            }

            if (gamepad1.b){
                servo.setPosition(servo.getPosition() - 10);
            }

            telemetry.addData("Current Angle: ", servo.getPosition());
            telemetry.update();
        }
    }

}
