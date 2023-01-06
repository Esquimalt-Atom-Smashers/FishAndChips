# Esquimalt Atom Smashers

## Who We Are

We are team 20025 the Esquimalt Atom Smashers.

## Table of Contents

1. [Programming Portfollio](#programming-portfollio)
2. [Creating a Telop Mode](#creating-a-teleop-mode)
3. [Creating a Subsystem](#creating-a-subsystem)

## Programming Portfollio

### __October 24th 2022 ~~The Startup~~__<br>
Created repo for all of the FTC programming for FishAndChips. On top of this, started migrating some of the code to reflect the design pattern which FTClib provides. This included using the subsystem design pattern FTClib provided. Subsystems were a way that allowed us to organize our code and split up the functions of the robot in specific classes. These classes included: drivebase, claw(intake), lights, arm, camera. At the time, FishAndChips was not built so majority of this code was skeleton code.

### __October 25th 2022 ~~I Got 99 Problems~~__<br>
Subsystems were updated and changed to be uploaded to a test robot we had on hand. Upon uploading there were quite a few errors(damn those semicolons) however, most of them were easily fixable; small tunings like motor speed and null reference errors. The biggest issue we had was with the library itself, we had issues with getting the commands registered and bound to the appropriate subsystem. Nevertheless, we eventually fixed the problem[^1] and had our code working on the test robot.

### __October 29th 2022 ~~Docs docs docs docs docs~~__<br>
Started working on a README.md for the repository, which would cover the following:
* Creating a subsystem
* Creating a command
* Basic debugging tools -> for the builders :-)
* Basic documentation

### __First week of November 2022 ~~The Im Sick Of Writing JavaDocs~~__<br>
Started to write javadocs for the code *sigh*, and did minor optimizations as we went through the code. Soon the robot was built and it allowed us to upload the code to FishAndChips, obviously everything broke and we had to spend quite a while debugging. It was somewhat ironic, we would fix one problem and 10 more would pop up(more indepth of what the problems were below). Be that as it may, we FINALLY got everything working the way it was supposed to. Now it was time to work on a) optimizing drive controlled period and b) starting autonomous. 

### __Second week of November 2022 ~~The Grind~~__<br>
We started to experiment with a colour sensor to get values from the signal cones, we soon found that this was a horrible way to achieve this since colour sensors had a) horrible range b) very inconsistent c) colour is dependent on enviroment. So, because of this we switched over to a camera and setup OpenEasyCV to detect the colours[^2]. Once we had the camera detecting the appropriate signal cone colours it was time to implement autonomous methods which would use encoders to autonomously drive the robot a set distance. This took longer than expected but we eventually had methods controlling driving, strafing, lifting the arm, and opening/closing the claw. Now it was time to implement the actual logic of the autonomous, basically robot sees colour and drives to the correct parkade. At the end of the second week of November, we had an autonomous mode which worked consistently 20% of the time.

### __November 25th 2022 ~~The Great Disaster~~__ <br>
November 25th, the day before competition, everything was going grand. We were all packing up and getting our stuff ready for competition like doing the robot inspection checklist and making sure everything was updated. We noticed our Control hub was not up to date, whatever we thought, lets just update it. **BIG MISTAKE** for whatever reason **EVERYTHING** broke, none of the code was building, a million errors, and whenever we got the code built it would downgrade our control hub to a previous version[^3]. We were stressing out trying everything to get it working, eventually we just created a new project and started adding the code over 1 by 1. This fixed the issue we were having but unfourtantly we had to abondend the previous repository(the one with all the commits). Anyways with that being done for now *foreshadowing* we packed up and were ready for competition. 

### __November 26th 2022 ~~The Competition~~__ <br>
November 26th, the day of the competition, everyone was super pumped and excited to see FishAndChips in action. We had tested the autonomous prior to the competition and we were confident in it. Our first match of the FTC scrimmage, our drivers were ready, the code was uploaded, and FishAndChips was ready. The autonomous mode started once the buzzer went off... nothing happend. We waited 10 seconds, nothing happend. The programmers heart sunk when we realized, the autonomous was broken. Between matches we were quickly attempting to fix the code between matches but unfourtantly no matter what we did, we could not get it to work. By the end of the event, we finally figured out was wrong with it. Essentially the enviroment was so much different than our robotics room, the colour range was so different that the camera could not detect any of the signal sleeves[^4].

### __November 28th 2022 ~~Working Autonomous?~~__ <br>
We decieded to switch from using the camera to find colours to using the camera to detect april tags. There is a very well made library that works with OpenEasyCV which allows your camera to detect the unique april tags on the signal cones. We got it implemented and wrote the logic for the autonomous mode and it was working 90% of the time. This was a **HUGE** improvment, this allowed for a more consistent autonomous which was great. 

### _Month of December ~~Break~~__ <br>
Throughout December we made few minor changes to autonomous and the route. On top of this, we made a few general code changes(more documentation). Most of December was used for relaxing and taking a break. 

<hr>

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



