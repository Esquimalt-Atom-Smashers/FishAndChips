package org.firstinspires.ftc.teamcode.internal.util.external.belmont;//package org.firstinspires.ftc.teamcode.internal.belmont;
//
//import com.arcrobotics.ftclib.command.SubsystemBase;
//import com.arcrobotics.ftclib.hardware.motors.Motor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class ElevatorSubsystem extends SubsystemBase {
//    public final Motor elevator;
//
//    public ElevatorSubsystem(HardwareMap hardwareMap) {
//        elevator = new Motor(hardwareMap, "elevator");
//        elevator.setInverted(true);
//        elevator.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
//    }
//
//    public void moveUp() {
//        elevator.set(0.5);
//    }
//
//    public void moveDown() {
//        elevator.set(-0.2);
//    }
//
//    public void stop() {
//        elevator.set(0);
//    }
//}
