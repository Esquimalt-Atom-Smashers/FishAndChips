package org.firstinspires.ftc.teamcode.internal.util.external.belmont;//package org.firstinspires.ftc.teamcode.internal.belmont;
//
//import com.arcrobotics.ftclib.command.Robot;
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.arcrobotics.ftclib.gamepad.GamepadKeys;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//
//public class MyRobot extends Robot {
//    private CappingElementSubsystem capping;
//    private ElevatorSubsystem elevator;
//    private IntakeWheelsSubsystem intake;
//    private DriveSubsystem drive;
//
//    private GamepadEx controller1;
//    private GamepadEx controller2;
//
//    private OpMode opmode;
//
//    public MyRobot(OpMode opMode) {
//        this.opmode = opMode;
//        initSubsystems();
//        initJoysticks();
//    }
//
//    public void initJoysticks() {
//        controller1 = new GamepadEx(opmode.gamepad1);
//        controller2 = new GamepadEx(opmode.gamepad2);
//
//        controller1.getGamepadButton(GamepadKeys.Button.A)
//                        .whenPressed(new SpinCommand(intake))
//                                .whenReleased((new IntakeStopCommand(intake)));
//
//        controller1.getGamepadButton(GamepadKeys.Button.B)
//                        .whenPressed(new SpinOutCommand(intake))
//                                .whenReleased(new IntakeStopCommand(intake));
//
//
//        controller2.getGamepadButton(GamepadKeys.Button.A)
//                        .whenPressed(new moveUpCommand(elevator))
//                                .whenReleased(new ElevatorStopCommand(elevator));
//        controller2.getGamepadButton(GamepadKeys.Button.B)
//                        .whenPressed(new moveDownCommand(elevator))
//                                .whenReleased(new ElevatorStopCommand(elevator));
//
//
//        drive.setDefaultCommand(new DriveCommand(drive,
//                () -> controller1.getLeftX(),
//                () -> controller1.getLeftY(),
//                () -> controller1.getRightX()));
//
//    }
//
//    public void initSubsystems() {
//        capping = new CappingElementSubsystem(opmode.hardwareMap);
//        elevator = new ElevatorSubsystem(opmode.hardwareMap);
//        intake  = new IntakeWheelsSubsystem(opmode.hardwareMap);
//        drive = new DriveSubsystem(opmode.hardwareMap);
//    }
//}
