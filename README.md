# Esquimalt Atom Smashers

## Who We Are

We are team 20025 the Esquimalt Atom Smashers.

## Table of Contents

1. [Programming Portfollio](#programming-portfollio)
2. [Creating a Telop Mode](#creating-a-teleop-mode)
3. [Creating a Subsystem](#creating-a-subsystem)

## Programming Portfollio

[Programming Log](programming-log.md)

## Creating a subsystem

A subsystem is a component that controls one part of the robot, for example the drivebase or the claw. 

### Step 1: Create class that extends CustomSubsytemBase

You create a class with a name that coresponds with the subsystem you are creating. You then use the ```extends``` keyword to make the class a subclass (child) of CustomSubsystemBase. In this case, the subsytem is called ClawSubsystem.
```java
public class ClawSubsystem extends CustomSubsystemBase {

}
```

### Step 2: Define the hardware the subsystem controls

Next, you define the constants, hardware and any other variables the subsytem would need. In this case, we only need one hardware, a servo. 
```java
public class ClawSubsystem extends CustomSubsystemBase {
    private final Servo claw;
}
```

### Step 3: Create a constructor matching parent class

Next, you call the parent's constructor using the keyword ```super```. You initialize your hardware and other objects that need initialization. In this case, the claw is initialized. 
```java
public class ClawSubsystem extends CustomSubsystemBase {

    private final Servo claw;

    public ClawSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        claw = hardwareMap.get(Servo.class, "servo");
    }

}
```

### Step 4: Create the necessary methods for controling the subsystem

For each individual function the subsystem will do, you should create an individual method for each one. Sometimes during autonomous, you might need more specific methods. In this case, there is two functions the claw subsystem must do, opening the claw and closing the claw. So two corresponding methods must be created. 
```java
public class ClawSubsystem extends CustomSubsystemBase {
    private final ServoEx claw;
    
    public ClawSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        claw = hardwareMap.get(Servo.class, "servo");
    }

    public void openClaw() {
        claw.rotateByAngle(90);
    }

    public void closeClaw() {
        claw.rotateByAngle(-90);
    }
}
```
<hr>

## Creating a Teleop mode

<hr>



