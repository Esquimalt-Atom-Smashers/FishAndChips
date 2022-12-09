package org.firstinspires.ftc.teamcode.internal.util.external.belmont;//package org.firstinspires.ftc.teamcode.internal.belmont;
//
//import com.arcrobotics.ftclib.command.SubsystemBase;
//import com.arcrobotics.ftclib.drivebase.MecanumDrive;
//import com.arcrobotics.ftclib.hardware.motors.Motor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import org.firstinspires.ftc.teamcode.internal.util.MotorSettings;
//
///**
// * Represents a drivebase that handles all movement functions of a robot.
// * Is a subclass of {@link } to achieve a command-based design pattern.
// *
// * @author Esquimalt Atom Smashers
// */
//public class DriveSubsystem extends SubsystemBase {
//
//
//    /** A MecanumDrive instance which handles the driving of the robot */
//    private final MecanumDrive mecanum;
//
//    /** Motors which control the drivebase */
//    private final Motor frontLeft;
//    private final Motor frontRight;
//    private final Motor rearLeft;
//    private final Motor rearRight;
//
//
//    public DriveSubsystem(HardwareMap hardwareMap) {
//
//        frontLeft = new Motor(hardwareMap, "frontLeft");
//        frontRight = new Motor(hardwareMap, "frontRight");
//        rearLeft = new Motor(hardwareMap, "rearLeft");
//        rearRight = new Motor(hardwareMap, "rearRight");
//
//        MotorSettings.resetEncoders(frontLeft, frontRight, rearLeft, rearRight);
//        MotorSettings.setZeroPowerBehaviors(MotorSettings.defaultZeroPowerBehavior, frontLeft, frontRight, rearLeft, rearRight);
//        MotorSettings.setMotorRunModes(MotorSettings.defaultRunMode, frontLeft, frontRight, rearLeft, rearRight);
//        mecanum = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);
//    }
//
//    /**
//     * Drives the robot in the given direction based on the three given parameters
//     * Calls the {@link MecanumDrive#driveRobotCentric(double, double, double)}
//     *
//     * @param left_x the value of the left joystick x axis
//     * @param left_y the value of the left joystick y axis
//     * @param right_x the value of the right joystick x axis
//     */
//    public void drive(double left_x, double left_y, double right_x) {
//        mecanum.driveRobotCentric(left_x, left_y, right_x);
//    }
//}
//
