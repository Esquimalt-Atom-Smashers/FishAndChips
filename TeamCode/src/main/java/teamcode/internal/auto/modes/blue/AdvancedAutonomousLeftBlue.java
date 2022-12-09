package teamcode.internal.auto.modes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;

import teamcode.internal.Robot;
import teamcode.internal.subsystems.DrivebaseSubsystem;
import teamcode.internal.util.AprilTagConstants;

@Autonomous(name="Auto: Blue Alliance Left")
public class AdvancedAutonomousLeftBlue extends LinearOpMode {
    private Robot robot;

    private boolean tagFound = false;
    private int parkingZone;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        robot.initWebcamSubsystem();
        robot.getWebcamSubsystem().startStreaming();

        waitForStart();
        if (opModeIsActive()) {
            while (!tagFound && !isStopRequested()) {
                detectTag();
                telemetry.addLine("Tag Not Detected");
                telemetry.update();
            }
            telemetry.addLine("Tag Detected");
            telemetry.update();
            deploy();
            driveToPlaceCone();
            wait(1000);
            driveToParking();
        }
    }

    private void detectTag() {
        ArrayList<AprilTagDetection> detections = robot.getWebcamSubsystem().getPipeline().getLatestDetections();

        if (detections.size() != 0 && !tagFound) {
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
        robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 27);
        wait(500);

        robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
        wait(500);

        robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -14);
        wait(500);

        robot.getClawSubsystem().openClaw();
    }

    private void deploy() {
        robot.getLinkageSubsystem().nextPos();
        robot.getLinkageSubsystem().nextPos();
        wait(500);
    }

    private void driveToParking() {
        switch (parkingZone) {
            case 1:
                robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 16);
                wait(500);
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, -26);
                wait(500);
                robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -28 * 2);
                wait(500);
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 27);
                break;
            case 2:
                break;
            case 3:
                robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                wait(500);
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                break;
        }
    }

    private void wait(int milliseconds) {
        ElapsedTime time = new ElapsedTime();
        while (time.milliseconds() < milliseconds);
    }
}
