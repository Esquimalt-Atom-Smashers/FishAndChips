package teamcode.internal.automodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;

import teamcode.internal.Robot;
import teamcode.internal.subsystems.DrivebaseSubsystem;
import teamcode.internal.util.AprilTagConstants;

@Autonomous(name="Main Autonomous Mode(right)")
public class AdvancedAutonomousRightBlue extends LinearOpMode {
    private Robot robot;

    private boolean tagFound = false;
    private int parkingZone;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this, 0, 0);

        robot.initWebcamSubsystem();

        waitForStart();
        if (opModeIsActive()) {
            robot.getWebcamSubsystem().startStreaming();
            while (!tagFound && !isStopRequested()) {
                detectTag();
                telemetry.addLine("Tag Not Detected");
                telemetry.update();
            }
            telemetry.addLine("Tag Detected");
            telemetry.update();

            robot.autoDeploy();
            robot.autoDriveToPlaceCone("right");
            robot.autoDriveToParking(parkingZone, "right");
        }
    }

    private void detectTag() {
        ArrayList<AprilTagDetection> detections = robot.getWebcamSubsystem().getPipeline().getLatestDetections();

        if (detections.size() != 0 ) {
            for (AprilTagDetection tag : detections) {
                if (AprilTagConstants.parkingZone1Tags.contains(tag.id)) {
                    parkingZone = 1;
                    tagFound = true;
                }
                if (AprilTagConstants.parkingZone2Tags.contains(tag.id)) {
                    parkingZone = 2;
                    tagFound = true;
                }
                if (AprilTagConstants.parkingZone3Tags.contains(tag.id)) {
                    parkingZone = 3;
                    tagFound = true;
                }
            }
        }
    }

    private void driveToPlaceCone() {
        robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -28);

        robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28 * 3);

        robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 14);

        robot.getClawSubsystem().openClaw();
    }

    private void deploy() {
        robot.getClawSubsystem().openClaw();
        robot.getClawSubsystem().closeClaw();

        wait(2000);
        robot.getLinkageSubsystem().nextPos();
        robot.getLinkageSubsystem().nextPos();
    }

    private void driveToParking() {
        switch (parkingZone) {
            case 1:
                robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -28);
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                break;
            case 2:
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                break;
            case 3:
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                break;
        }
    }

    private void wait(int milliseconds) {
        ElapsedTime time = new ElapsedTime();
        while (time.milliseconds() < milliseconds);
    }
}
