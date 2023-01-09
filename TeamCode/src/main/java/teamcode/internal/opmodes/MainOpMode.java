package teamcode.internal.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import teamcode.internal.Robot;

@SuppressWarnings("unused")
@TeleOp(name="Main Opmode", group="Linear Opmode")
public class MainOpMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            robot.run();
        }
    }
}
