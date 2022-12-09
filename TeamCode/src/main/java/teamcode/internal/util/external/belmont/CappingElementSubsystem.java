package org.firstinspires.ftc.teamcode.internal.util.external.belmont;//package org.firstinspires.ftc.teamcode.internal.belmont;
//
//import com.arcrobotics.ftclib.command.SubsystemBase;
//import com.arcrobotics.ftclib.hardware.ServoEx;
//import com.arcrobotics.ftclib.hardware.SimpleServo;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//
//public class CappingElementSubsystem extends SubsystemBase {
//
//    private final ServoEx dropServo;
//    private final int ANGLE = 90;
//
//    public CappingElementSubsystem(HardwareMap hardwareMap) {
//        dropServo = new SimpleServo(hardwareMap, "dropServo", 0, 270, AngleUnit.DEGREES);
//    }
//
//    public void dropServo() {
//        dropServo.rotateByAngle(-ANGLE);
//    }
//
//    public void liftServo() {
//        dropServo.rotateByAngle(ANGLE);
//    }
//}
