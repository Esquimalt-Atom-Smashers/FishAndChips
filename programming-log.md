# Programming Log

#### This is the programming log for team 20025 2022-2023. This log contains information about the programming aspect of the robot. When reading the log, you will see footnotes in areas where a problem is mentioned/fixed. If you click on the footnotes it will take you to a more in-depth description of the error and how it was fixed.

<hr>

### __October 24th 2022 ~~The Startup~~__<br>
Created a repo for all of the FTC programming for FishAndChips. On top of this, started migrating some of the code to reflect the design pattern which FTClib provides. This included using the subsystem design pattern FTClib provided. Subsystems were a way that allowed us to organize our code and split up the functions of the robot into specific classes. These classes included: drivebase, claw(intake), lights, arm, and camera. At the time, FishAndChips was not built so the majority of this code was skeleton code.

### __October 25th 2022 ~~I Got 99 Problems~~__<br>
Subsystems were updated and changed to be uploaded to a test robot we had on hand. Upon uploading there were quite a few errors(damn those semicolons) however, most of them were easily fixable; small tunings like motor speed and null reference errors. The biggest issue we had was with the library itself, we had issues with getting the commands registered and bound to the appropriate subsystem. Nevertheless, we eventually fixed the problem[^1] and had our code working on the test robot.

### __October 29th 2022 ~~Docs docs docs docs docs~~__<br>
Started working on a README.md for the repository, which would cover the following:
* Creating a subsystem
* Creating a command
* Basic debugging tools -> for the builders :-)
* Basic documentation

### __First week of November 2022 ~~Im Sick Of Writing JavaDocs~~__<br>
Started to write javadocs for the code *sigh*, and did minor optimizations as we went through the code. Soon the robot was built and it allowed us to upload the code to FishAndChips everything broke and we had to spend quite a while debugging. It was somewhat ironic, we would fix one problem and 10 more would pop up(more in-depth of what the problems were below). Be that as it may, we FINALLY got everything working the way it was supposed to. Now it was time to work on a) optimizing drive controlled period and b) starting autonomous. 

### __Second week of November 2022 ~~The Grind~~__<br>
We started to experiment with a colour sensor to get values from the signal cones, we soon found that this was a horrible way to achieve this since colour sensors had a) horrible range b) very inconsistent c) colour is dependent on the environment. So, because of this, we switched over to a camera and set up OpenEasyCV to detect the colours[^2]. Once we had the camera detecting the appropriate signal cone colours it was time to implement autonomous methods which would use encoders to autonomously drive the robot a set distance. This took longer than expected but we eventually had methods controlling driving, strafing, lifting the arm, and opening/closing the claw. Now it was time to implement the actual logic of the autonomous. The robot sees colour and drives to the correct parkade. At the end of the second week of November, we had an autonomous mode which worked consistently 20% of the time.

### __November 25th 2022 ~~The Great Disaster~~__ <br>
November 25th, the day before the competition, everything was going grand. We were all packing up and getting our stuff ready for the competition like doing the robot inspection checklist and making sure everything was updated. We noticed our Control hub was not up to date, whatever we thought, let us just update it. **BIG MISTAKE** for whatever reason **EVERYTHING** broke, none of the code was building, a million errors, and whenever we got the code built it would downgrade our control hub to a previous version[^3]. We were stressing out trying everything to get it working, eventually, we just created a new project and started adding the code over 1 by 1. This fixed the issue we were having but unfortunately we had to abandon the previous repository(the one with all the commits). Anyways with that being done for now *foreshadowing* we packed up and were ready for competition. 

### __November 26th 2022 ~~Competition~~__ <br>
November 26th, the day of the competition, everyone was super pumped and excited to see FishAndChips in action. We had tested the autonomous before the competition and we were confident in it. In our first match of the FTC scrimmage, our drivers were ready, the code was uploaded, and FishAndChips was ready. The autonomous mode started once the buzzer went off... nothing happened. We waited 10 seconds, but nothing happened. The programmer's heart sank when we realized, the autonomous was broken. Between matches, we were quickly attempting to fix the code but unfortunately, no matter what we did, we could not get it to work. By the end of the event, we finally figured out what was wrong with it. Essentially the environment was so much different than our robotics room, the colour range was so different that the camera could not detect any of the signal sleeves[^4].

### __November 28th 2022 ~~Working Autonomous?~~__ <br>
We decided to switch from using the camera to find colours to using the camera to detect April tags. There is a very well-made library that works with OpenEasyCV which allows your camera to detect the unique April tags on the signal cones. We got it implemented and wrote the logic for the autonomous mode and it was working 90% of the time. This was a **HUGE** improvement, this allowed for more consistent autonomous which was great. 

### __Month of December ~~Break~~__ <br>
Throughout December we made a few minor changes to autonomous and the route. On top of this, we made a few general code changes(more documentation). Most of December was used for relaxing and taking a break. 

[^1]: FTClib has a few classes for creating your own subsystem and commands but we couldent figure out how to handle specific joystick inputs like setting right bumper to a toggle. Turns out, FTClib has their own gamepad class which has methods which handle all of the event listening. Another issue we had with FTClib was it would completely break whenever gradle would update or the control hub, we eventually stopped using FTClib and switched to our own system(more about this later). 

[^2]: For the colour detection we used the library EasyOpenCV. We wrote a camera pipeline which every frame would create 3 black and white matrices which the white repressented the colour we were looking for. We would then compare the amount of white in each matrix and whichever had more we knew which colour was most present. When converting the initial frame into 3 matrices we would supply a colour range(HSL) which a built in method would go through each pixel and compare the pixel value with the colour range. If it was in the range, set that pixel to white if not, set the pixel to black. 

[^3]: This problem would downgrade your control hub everytime you would upload code to the hub. I assume this was happening because the FTC-Robot-Controller we cloned of github was not the latest one which likely meant some internal files with gradle or what not was conflicting with the updated control hub. The only fix we found was to clone the updated FTC-Robot-Controller repo and import all the code, be careful if using git dont accept any of your old projects code you didnt write like the gradle files and android studio stuff.  

[^4]: This is the biggest downside with detecting colours for automonous, unless you have a very broad range it is almost impossible to predict the exact colour range in the area you will be competing since there are so many variables, lighting, shadows from people, background colours and many more. In general i would recommend using specific black and white images like April tags or using specific images and using tensor flow to detect them. 
