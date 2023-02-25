package teamcode.internal;

import android.media.Ringtone;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import teamcode.internal.subsystems.ClawSubsystem;
import teamcode.internal.subsystems.DrivebaseSubsystem;
import teamcode.internal.subsystems.LightSubsystem;
import teamcode.internal.subsystems.LinkageSubsystem;
import teamcode.internal.subsystems.WebcamSubsystem;
import teamcode.internal.util.EncoderConstants;
import teamcode.internal.util.Time;

/**
 * The actual robot represented via a class.
 * All gamepad key-binds are set here and subsystems are initialized.
 */
@SuppressWarnings("unused")
public class Robot {
    /** The op-mode the robot is being declared in */
    private final OpMode opMode;

    /** Controllers declared here */
//    private CustomGamepad controller1;
    private Gamepad controller1;

    /** Subsystems declared here */
    private DrivebaseSubsystem drivebaseSubsystem;
    private LinkageSubsystem linkageSubsystem;
    private ClawSubsystem clawSubsystem;
    private WebcamSubsystem webcamSubsystem;
    private LightSubsystem lightSubsystem; //null until the lights are put back on the robot // The lights are back on the robot?

    private boolean clawClosed = false;

    private final Time time = new Time();

    private int robotX;
    private int robotY;

    private boolean right_bumper_pressed = false;

    /**
     * Initializes all subsystems and joysticks.
     *
     * @param opMode the op-mode the robot is being declared in
     */
    public Robot(OpMode opMode, int x, int y) {
        robotX = x;
        robotY = y;
        this.opMode = opMode;
        initSubsystems();
//        controller1 = new CustomGamepad(opMode.gamepad1);
        controller1 = opMode.gamepad1;
    }

    /** Initializes the subsystems (excluding the webcam subsystem) */
    private void initSubsystems() {
        drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap, opMode.telemetry);
        drivebaseSubsystem.initImu();
        drivebaseSubsystem.setScaled(false);

        linkageSubsystem = new LinkageSubsystem(opMode.hardwareMap, opMode.telemetry);
        clawSubsystem = new ClawSubsystem(opMode.hardwareMap, opMode.telemetry);
        lightSubsystem = new LightSubsystem(opMode.hardwareMap, opMode.telemetry);

    }

    public void run() throws InterruptedException {
//        controller1.setDefaultEvent(() -> drivebaseSubsystem.drive(-opMode.gamepad1.left_stick_y,
//                opMode.gamepad1.left_stick_x, opMode.gamepad1.right_stick_x));
//
//        controller1.setOnHeld(Button.A, () -> linkageSubsystem.lift());
//        controller1.setOnReleased(Button.A, () -> linkageSubsystem.stop());
//
//        controller1.handleEvents();


        // Drive
        ((Runnable) () -> {
            drivebaseSubsystem.drive(-controller1.left_stick_y,
                    controller1.left_stick_x, controller1.right_stick_x, true);
        }).run();



        // Turn on lights
        ((Runnable) () -> {
            if (controller1.dpad_left) {
                lightSubsystem.on(LightSubsystem.LightType.UNDER_GLOW);
                lightSubsystem.on(LightSubsystem.LightType.ARM_GLOW);
            }
        }).run();

        // Turn off lights
        ((Runnable) () -> {
            if (controller1.dpad_right) {
                lightSubsystem.off(LightSubsystem.LightType.UNDER_GLOW);
                lightSubsystem.off(LightSubsystem.LightType.ARM_GLOW);
            }
        }).run();

        // Open claw
        ((Runnable) () -> {
            if (controller1.left_bumper) {
                clawSubsystem.openClaw();
            }
        }).run();

        // Close claw
        ((Runnable) () -> {
            if (controller1.right_bumper) {
                clawSubsystem.closeClaw();
            }
        }).run();

        // Set limit position
        ((Runnable) () -> {
            if (controller1.dpad_up) {
                linkageSubsystem.setLimitPosition(300);
            }
        }).run();

        // Lift arm
        ((Runnable) () -> {
            if (controller1.a) {
                linkageSubsystem.lift();
            } else if (!controller1.a && !controller1.b) {
                linkageSubsystem.stop();
            }
//            if (controller1.a) {
//                linkageSubsystem.lift();
//                // THIS IS THE PROBLEM
//                while (controller1.a) {
//
//                }
//                linkageSubsystem.stop();
//            }
        }).run();



        // Lower arm
        ((Runnable) () -> {
            if (controller1.b) {
                linkageSubsystem.drop();
            } else if (!controller1.b && !controller1.a){
                linkageSubsystem.stop();
            }
//            if (controller1.b) {
//                linkageSubsystem.drop();
//                while (controller1.b) {
//
//                }
//                linkageSubsystem.stop();

        }).run();


    }

    public void autoDeploy() {
        getClawSubsystem().openClaw();
        getClawSubsystem().closeClaw();
        time.waitFor(1000);
        getLinkageSubsystem().nextPos();
        time.waitFor(500);
        getLinkageSubsystem().nextPos();
        time.waitFor(500);
    }

    public void autoDriveToParking(int parkingZone, String dir) {
        if (dir.equalsIgnoreCase("left")) {
            switch (parkingZone) {
                case 1:
                    getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -42);
                    time.waitFor(2000);
                    break;
                case 2:
                    getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -14);
                    time.waitFor(2000);
                    break;
                case 3:
                    getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 14);
                    time.waitFor(2000);
                    break;
            }
        }

        if (dir.equalsIgnoreCase("right")) {
            switch (parkingZone) {
                case 1:
                    getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -14);
                    time.waitFor(2000);
                    break;
                case 2:
                    getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 14);
                    time.waitFor(2000);
                    break;
                case 3:
                    getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 42);
                    time.waitFor(2000);
                    break;
            }
        }

    }

    public void autoDriveToPlaceCone(String dir) {
        if (dir.equalsIgnoreCase("left")) {
            getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.CENTIMETRES, 99);
            time.waitFor(500);

            getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.CENTIMETRES, -35);
            time.waitFor(500);

            getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.CENTIMETRES, 38);
            time.waitFor(500);

            getClawSubsystem().openClaw();
            time.waitFor(500);
        }
        if (dir.equalsIgnoreCase("right")) {
            getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.CENTIMETRES, 99);
            time.waitFor(500);

            getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.CENTIMETRES, -33);
            time.waitFor(500);

            getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.CENTIMETRES, -38);
            time.waitFor(500);

            getClawSubsystem().openClaw();
            time.waitFor(500);
        }
        else {
            return;
        }
    }

    public void autoDriveToTile(int tileX, int tileY) {
        int tileDistanceX = Math.abs(robotX - tileX);
        int tileDistanceY = Math.abs(robotY - tileY);

        drivebaseSubsystem.drive(DrivebaseSubsystem.DistanceUnits.TILES, tileDistanceY);
        drivebaseSubsystem.strafe(DrivebaseSubsystem.DistanceUnits.TILES, tileDistanceX);

        robotX = tileX;
        robotY = tileY;
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
