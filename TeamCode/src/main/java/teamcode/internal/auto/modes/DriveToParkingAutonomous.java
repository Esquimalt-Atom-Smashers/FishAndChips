package org.firstinspires.ftc.teamcode.internal.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.internal.Robot;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.internal.util.AprilTagConstants;
import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;

@Autonomous
public class DriveToParkingAutonomous extends LinearOpMode {
    private Robot robot;

    private boolean tagFound = false;
    private int parkingZone;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        robot.initWebcamSubsystem();
        robot.getWebcamSubsystem().startStreaming();

        while (!isStarted() && !isStopRequested()) {
            ArrayList<AprilTagDetection> detections = robot.getWebcamSubsystem().getPipeline().getLatestDetections();

            if (detections.size() != 0 ) {
                for (AprilTagDetection tag : detections) {
                    if (AprilTagConstants.parkingZone1Tags.contains(tag.id)) {
                        parkingZone = 1;
                        tagFound = true;
                        break;
                    }
                    if (AprilTagConstants.parkingZone2Tags.contains(tag.id)) {
                        parkingZone = 2;
                        tagFound = true;
                        break;
                    }
                    if (AprilTagConstants.parkingZone3Tags.contains(tag.id)) {
                        parkingZone = 3;
                        tagFound = true;
                        break;
                    }
                }
            }
        }

        if (opModeIsActive()) {
            if (tagFound) {
                deploy();
                wait(1000);
                driveToParking();
            }
            else {
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
            }
        }
    }

    private void deploy() {
        robot.getClawSubsystem().openClaw();
        robot.getClawSubsystem().closeClaw();

        wait(2000);
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
