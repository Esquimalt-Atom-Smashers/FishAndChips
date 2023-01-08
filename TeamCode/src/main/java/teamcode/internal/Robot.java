package teamcode.internal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;

import teamcode.internal.events.Button;
import teamcode.internal.events.CustomGamepad;
import teamcode.internal.subsystems.ClawSubsystem;
import teamcode.internal.subsystems.DrivebaseSubsystem;
import teamcode.internal.subsystems.LightSubsystem;
import teamcode.internal.subsystems.LinkageSubsystem;
import teamcode.internal.subsystems.WebcamSubsystem;
import teamcode.internal.util.EncoderConstants;

/**
 * The actual robot represented via a class.
 * All gamepad key-binds are set here and subsystems are initialized.
 */
@SuppressWarnings("unused")
public class Robot {
    /** The op-mode the robot is being declared in */
    private final OpMode opMode;

    /** Controllers declared here */
    private CustomGamepad controller1;

    /** Subsystems declared here */
    private DrivebaseSubsystem drivebaseSubsystem;
    private LinkageSubsystem linkageSubsystem;
    private ClawSubsystem clawSubsystem;
    private WebcamSubsystem webcamSubsystem;
    private LightSubsystem lightSubsystem; //null until the lights are put back on the robot

    private boolean clawClosed = false;

    /**
     * Initializes all subsystems and joysticks.
     *
     * @param opMode the op-mode the robot is being declared in
     */
    public Robot(OpMode opMode) {
        this.opMode = opMode;
        initSubsystems();
        controller1 = new CustomGamepad(opMode.gamepad1);
    }

    /** Initializes the subsystems (excluding the webcam subsystem) */
    private void initSubsystems() {
        drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap, opMode.telemetry);
        drivebaseSubsystem.initImu();
        linkageSubsystem = new LinkageSubsystem(opMode.hardwareMap, opMode.telemetry);
        clawSubsystem = new ClawSubsystem(opMode.hardwareMap, opMode.telemetry);
        lightSubsystem = new LightSubsystem(opMode.hardwareMap, opMode.telemetry);
    }

    public void run() throws InterruptedException {

        controller1.setDefaultEvent(() -> drivebaseSubsystem.drive(-opMode.gamepad1.left_stick_y,
                opMode.gamepad1.left_stick_x, opMode.gamepad1.right_stick_x));

        controller1.setOnHeld(Button.A, () -> linkageSubsystem.lift());
        controller1.setOnReleased(Button.A, () -> linkageSubsystem.stop());

        controller1.handleEvents();

//        drivebaseSubsystem.drive(-controller1.left_stick_y,
//                controller1.left_stick_x, controller1.right_stick_x);
//
//        if (controller1.dpad_left) {
//            ((Runnable) () -> {
//                lightSubsystem.on(LightSubsystem.LightType.UNDER_GLOW);
//                lightSubsystem.on(LightSubsystem.LightType.ARM_GLOW);
//            }).run();
//        }
//        if (controller1.dpad_right) {
//            ((Runnable) () -> {
//                lightSubsystem.off(LightSubsystem.LightType.UNDER_GLOW);
//                lightSubsystem.off(LightSubsystem.LightType.ARM_GLOW);
//            }).run();
//        }
//
//        if (controller1.left_bumper) {
//            ((Runnable) () -> clawSubsystem.closeClaw()).run();
//        }
//
//        if (controller1.right_bumper) {
//            ((Runnable) () -> clawSubsystem.openClaw()).run();
//        }
//
//        if (controller1.a && linkageSubsystem.getLinkage() < 115 * EncoderConstants.Gobilda60RPM.PULSES_PER_DEGREE) {
//            ((Runnable) () -> {
//                linkageSubsystem.lift();
//                while (controller1.a && linkageSubsystem.getLinkage() < 115 * EncoderConstants.Gobilda60RPM.PULSES_PER_DEGREE) {
//
//                }
//                linkageSubsystem.stop();
//            }).run();
//        }
//
//        if (controller1.b) {
//            linkageSubsystem.drop();
//            ((Runnable) () -> {
//                while (controller1.b) {
//
//                }
//                linkageSubsystem.stop();
//            }).run();
//        }
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
