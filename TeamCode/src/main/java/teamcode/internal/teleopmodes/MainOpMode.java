package teamcode.internal.teleopmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import teamcode.internal.Robot;

@SuppressWarnings("unused")
@TeleOp(name="Main Opmode", group="Linear Opmode")
public class MainOpMode extends LinearOpMode {
    private Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this, 0, 0);

        robot.getClawSubsystem().getClaw().turnToAngle(130);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            robot.run();
        }
    }
}
