package teamcode.internal.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import teamcode.internal.util.EncoderConstants;

/**
 * Represents a virtual 4 bar which handles lifting the claw.
 */
public class LinkageSubsystem extends CustomSubsystemBase {

    /** The speed of which the motors run at */
    private final double LIFT_SPEED = 0.60;
    private final double DROP_SPEED = -0.35;

    /** The current position of the linkage */
    private Position currentPosition;

    /** An array of all possible positions for the linkage to be */
    private Position[] positions = {Position.HOME, Position.SMALL_POLE, Position.MEDIUM_POLE, Position.TALL_POLE};

    /** A cursor which corrosponds with the current position of the linkage*/
    private int cursor;

    /** The motors that control the linkage */
    private final DcMotor linkage;

    /**
     * Enum of all target positions the linkage can go to.
     * Used in autonomous and for convenience during tele-op periods
     */
    public enum Position {
        HOME(0),
        SMALL_POLE(55),
        MEDIUM_POLE(95),
        TALL_POLE(110);

        private int deg;

        Position(int deg) {
            this.deg = deg;
        }
    }

    /**
     * Sole constructor of LinkageSubsystem.
     *
     * Initializes all motors and encoders and
     * sets them to the proper configurations based on {@param hardwareMap}
     * Sets all the proper motor modes i.e brake mode and running with encoders.
     *
     * @param hardwareMap the robots hardware map
     * @param telemetry the op-mode's telemetry
     */
    public LinkageSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        linkage = hardwareMap.get(DcMotor.class, "leftLinkageMotor");

        currentPosition = Position.HOME;
        cursor = 0;

        linkage.setDirection(DcMotorSimple.Direction.FORWARD);
        linkage.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linkage.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linkage.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /** Lifts the linkage at a given speed */
    public void lift() {
        linkage.setPower(LIFT_SPEED);
    }

    /** Drops the linkage at a given speed */
    public void drop() {
        linkage.setPower(DROP_SPEED);
    }

    /** Stops the linkage from lifting or dropping */
    public void stop() {
        linkage.setPower(0);
    }

    /**
     * Lifts the linkage to the next preset position,
     * assuming the linkage is not already max position.
     */
    public void nextPos() {
        if (currentPosition == Position.TALL_POLE) {
            return;
        }
        cursor++;
        linkage.setTargetPosition((int) (positions[cursor].deg * EncoderConstants.Gobilda60RPM.PULSES_PER_DEGREE));

        linkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linkage.setPower(LIFT_SPEED);
        while (linkage.isBusy()) {

        }
        linkage.setPower(0);

        linkage.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        currentPosition = positions[cursor];
    }

    /**
     * Drops the linkage to the previous preset position,
     * assuming the linkage is not already min position.
     */
    public void prevPos() {
        if (currentPosition == Position.HOME) {
            return;
        }
        cursor--;
        linkage.setTargetPosition((int) (positions[cursor].deg * EncoderConstants.Gobilda60RPM.PULSES_PER_DEGREE));

        linkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linkage.setPower(0.25);
        while (linkage.isBusy()) {

        }

        linkage.setPower(0);

        linkage.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        currentPosition = positions[cursor];
    }

    /**
     * @return the current position of the linkage
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

}
