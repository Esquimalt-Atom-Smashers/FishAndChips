package org.firstinspires.ftc.teamcode.internal.util.external.belmont;//package org.firstinspires.ftc.teamcode.internal.belmont;
//
//import com.arcrobotics.ftclib.command.SubsystemBase;
//import com.arcrobotics.ftclib.hardware.motors.Motor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class IntakeWheelsSubsystem extends SubsystemBase {
//
//    private final Motor leftMotor;
//    private final Motor rightMotor;
//
//    public IntakeWheelsSubsystem(HardwareMap hardwareMap) {
//        leftMotor = new Motor(hardwareMap, "leftPickup");
//        rightMotor = new Motor(hardwareMap, "rightPickup");
//        rightMotor.setInverted(true);
//    }
//
//    public void spin() {
//        leftMotor.set(0.7);
//        rightMotor.set(0.7);
//    }
//
//    public void spinout() {
//        leftMotor.set(-0.7);
//        rightMotor.set(-0.7);
//    }
//    public void stop() {
//        leftMotor.set(0);
//        rightMotor.set(0);
//    }
//
//}
