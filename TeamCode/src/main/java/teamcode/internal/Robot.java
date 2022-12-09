package teamcode.internal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import teamcode.internal.subsystems.ClawSubsystem;
import teamcode.internal.subsystems.DrivebaseSubsystem;
import teamcode.internal.subsystems.LightSubsystem;
import teamcode.internal.subsystems.LinkageSubsystem;
import teamcode.internal.subsystems.WebcamSubsystem;

/**
 * The actual robot represented via a class.
 * This class extends ftclib.command.Robot which allows the class to be connected to the CommandScheduler.
 * All gamepad key-binds are set here and subsystems are initialized.
 */
@SuppressWarnings("unused")
public class Robot {
    /** The op-mode the robot is being declared in */
    private final OpMode opMode;

    /** Controllers declared here */
    private Gamepad controller1 = null;

    /** Subsystems declared here */
    private DrivebaseSubsystem drivebaseSubsystem;
    private LinkageSubsystem linkageSubsystem;
    private ClawSubsystem clawSubsystem;
    private WebcamSubsystem webcamSubsystem;
    private LightSubsystem lightSubsystem = null; //null until the lights are put back on the robot

    private volatile boolean clawClosed = false;

    /**
     * Initializes all subsystems and joysticks.
     *
     * @param opMode the op-mode the robot is being declared in
     */
    public Robot(OpMode opMode) {
        this.opMode = opMode;
        initSubsystems();
        initJoysticks();
    }

    /** Initializes the joysticks */
    private void initJoysticks() {

    }

    /** Initializes the subsystems (excluding the webcam subsystem) */
    private void initSubsystems() {
        drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap, opMode.telemetry);
        linkageSubsystem = new LinkageSubsystem(opMode.hardwareMap, opMode.telemetry);
        clawSubsystem = new ClawSubsystem(opMode.hardwareMap, opMode.telemetry);

    }

    public void run() {
        if (controller1.right_bumper) {
            drivebaseSubsystem.drive(-controller1.left_stick_y,
                                      controller1.left_stick_x,
                                      controller1.right_stick_x);
            if (clawClosed) {
                ((Runnable) () -> {
                    clawSubsystem.openClaw();
                    clawClosed = !clawClosed;
                }).run();
            }
            else {
                ((Runnable) () -> {
                    clawSubsystem.closeClaw();
                    clawClosed = !clawClosed;
                }).run();
            }
        }

        if (controller1.a) {
            linkageSubsystem.lift();
            ((Runnable) () -> {
                while (controller1.a) {

                }
                linkageSubsystem.stop();
            }).run();
        }

        if (controller1.b) {
            linkageSubsystem.drop();
            ((Runnable) () -> {
                while (controller1.b) {

                }
                linkageSubsystem.stop();
            }).run();
        }

        drivebaseSubsystem.drive(-controller1.left_stick_y, controller1.left_stick_x, controller1.right_stick_x);
    }

    /** Initializes the webcam subsystem */
    public void initWebcamSubsystem() {
        webcamSubsystem = new WebcamSubsystem(opMode.hardwareMap, opMode.telemetry);
    }

    public WebcamSubsystem getWebcamSubsystem() {
        return webcamSubsystem;
    }

    /**
     * @return the robot's drivebase-subsystem
     */
    public DrivebaseSubsystem getDrivebaseSubsystem() {
        return drivebaseSubsystem;
    }

    /**
     * @return the robot's linkage-subsystem
     */
    public LinkageSubsystem getLinkageSubsystem() {
        return linkageSubsystem;
    }

    /**
     * @return the robot's claw-subsystem
     */
    public ClawSubsystem getClawSubsystem() {
        return clawSubsystem;
    }

    /**
     * @return the robot's light-subsystem
     */
    public LightSubsystem getLightSubsystem() {
        return lightSubsystem;
    }
}
