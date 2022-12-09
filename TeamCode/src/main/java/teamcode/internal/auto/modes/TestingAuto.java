package teamcode.internal.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import teamcode.internal.Robot;
import teamcode.internal.subsystems.DrivebaseSubsystem;

@Autonomous(name="Testtesttest")
public class TestingAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(this);

        if (opModeIsActive()) {
            robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 30);
        }
    }
}
