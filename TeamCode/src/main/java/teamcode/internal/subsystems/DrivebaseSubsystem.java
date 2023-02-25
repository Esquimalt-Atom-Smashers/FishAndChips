package teamcode.internal.subsystems;

import android.os.Build;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Predicate;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;

import teamcode.internal.util.EncoderConstants;

/**
 * Represents a drivebase that handles all movement functions of a robot.
 *
 * @author Esquimalt Atom Smashers
 */

public class DrivebaseSubsystem extends CustomSubsystemBase {
    /** Motors which control the drivebase */
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor rearLeft;
    private final DcMotor rearRight;

    /** An array of the drivebase motors on the robot */
    private final DcMotor[] motors;

    private BNO055IMU imu;

    /** Speeds in which the robot strafes and drives */
    private final double AUTO_STRAFE_SPEED = 0.3;
    private final double AUTO_DRIVE_SPEED = 0.3;
    private final double TURN_SPEED = 0.4;

    private boolean scaled = false;



    public BNO055IMU getGyro() {
        return imu;
    }
    /**
     * The sole constructor of DrivebaseSubsystem. Initializes the 4 drivebase motors
     * and the built-in gyro. The drivebase motors are set to their corresponding directions and
     * run-modes.
     *
     * @param hardwareMap the robots hardware map
     * @param telemetry the op-mode's telemetry
     */
    public DrivebaseSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");

        motors = new DcMotor[]{frontLeft, frontRight, rearLeft, rearRight};

        Arrays.stream(motors)
                .forEach(motor -> motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE));

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRight.setDirection(DcMotorSimple.Direction.FORWARD);

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER));

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER));

    }

    /**
     * Drives the robot given the 3 joystick positions. The drivebase uses mecanum wheels allowing for 8 directional travel.
     *
     * @param strafe how far the robot will strafe left or right2
     * @param forward how far the robot will drive forward or backwards
     * @param turn how much the robot will turn clockwise or counterclockwise
     */
    public void drive(double forward, double strafe, double turn, boolean fieldCentric) {
        double gyroRadians = Math.toRadians(-imu.getAngularOrientation().firstAngle);

        double rotateX = fieldCentric ? strafe * Math.cos(gyroRadians) - forward * Math.sin(gyroRadians) : strafe;
        double rotateY = fieldCentric ? strafe * Math.sin(gyroRadians) + forward * Math.cos(gyroRadians) : forward;

        frontLeft.setPower(Range.clip(rotateY + rotateX + turn, -1, 1) * .80);
        frontRight.setPower(Range.clip(rotateY - rotateX - turn, -1, 1) * .80);
        rearLeft.setPower(Range.clip(rotateY - rotateX + turn, -1, 1) * .80);
        rearRight.setPower(Range.clip(rotateY + rotateX - turn, -1, 1) * .80);
    }

    /**
     * Drives the robot forwards or backwards given the entered distance.
     *
     * @param unit the unit of measurement
     * @param distance the distance the robot will drive
     */
    public void drive(DistanceUnits unit, double distance) {
        Arrays.stream(motors)
                .forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition()
                        + (int)(unit.toPulses(distance))));

        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        driveToPosition(AUTO_DRIVE_SPEED, 0, 0);
//        frontLeft.setPower(.5);
//        frontRight.setPower(.5);
//        rearLeft.setPower(.5);
//        rearRight.setPower(.5);
//        while (frontLeft.isBusy() || frontLeft.isBusy() || rearLeft.isBusy() || rearRight.isBusy()) {
//
//        }
        stopMotors();

        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    /**
     * Strafes the robot left or right given the entered distance.
     *
     * @param unit the unit of measurement
     * @param distance the distance the robot will strafe
     */
    public void strafe(DistanceUnits unit, int distance) {
        int target = (int) unit.toPulses(distance);

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + target);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() - target);
        rearLeft.setTargetPosition(rearLeft.getCurrentPosition() - target);
        rearRight.setTargetPosition(rearRight.getCurrentPosition() + target);

        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        driveToPosition(0, AUTO_STRAFE_SPEED, 0);
        stopMotors();

        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveToPosition(double forward, double strafe, double turn) {
        drive(forward, strafe, turn, false);
        while (frontLeft.isBusy() && frontRight.isBusy() && rearLeft.isBusy() && rearRight.isBusy()) {

        }
    }

    public void stopMotors() {
        Arrays.stream(motors)
                .forEach(motor -> motor.setPower(0));
    }

    public void setMotorMode(DcMotor.RunMode runMode) {
        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(runMode));
    }

    public void setScaled(boolean scaled) {
        this.scaled = scaled;
    }

    public void initImu() {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imu.initialize(parameters);
    }

    /** Enum used for driving in different units of length */
    public enum DistanceUnits {
        CENTIMETRES(EncoderConstants.Gobilda312RPM.PULSES_PER_CENTIMETRE),
        INCHES(EncoderConstants.Gobilda312RPM.PULSES_PER_INCH),
        TILES(EncoderConstants.Gobilda312RPM.PULSES_PER_TILE);

        private double conversionFactor;

        DistanceUnits(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toPulses(double value) {
            return value * conversionFactor;
        }
    }
}

