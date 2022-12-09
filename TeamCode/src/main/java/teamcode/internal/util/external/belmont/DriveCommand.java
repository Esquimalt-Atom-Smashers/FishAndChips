package org.firstinspires.ftc.teamcode.internal.util.external.belmont;//package org.firstinspires.ftc.teamcode.internal.belmont;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//
//
//import java.util.function.DoubleSupplier;
//
//public class DriveCommand extends CommandBase {
//
//    private final DriveSubsystem drivebaseSubsystem;
//
//    private final DoubleSupplier drive;
//    private final DoubleSupplier turn;
//    private final DoubleSupplier rx;
//
//    public DriveCommand(DriveSubsystem subsystem, DoubleSupplier left_x,
//                               DoubleSupplier left_y, DoubleSupplier right_x) {
//        drivebaseSubsystem = subsystem;
//        drive = left_x;
//        turn = left_y;
//        rx = right_x;
//        addRequirements(drivebaseSubsystem);
//    }
//
//    @Override
//    public void execute() {
//        drivebaseSubsystem.drive(
//                drive.getAsDouble(),
//                turn.getAsDouble(),
//                rx.getAsDouble());
//    }
//
//
//
//}
