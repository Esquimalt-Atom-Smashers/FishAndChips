package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.internal.vision.pipelines.AprilTagDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

/**
 * Represents a webcam which can detect april-tags
 */
public class WebcamSubsystem extends SubsystemBase {
    /** The physical webcam on the robot */
    private OpenCvWebcam webcam;

    /** The detection pipeline for the webcam */
    private final AprilTagDetectionPipeline pipeline;

    /** Telemetry variable used for outputting to the terminal during op mode */
    private final Telemetry telemetry;

    /** Weather or not the webcam is streaming */
    private boolean isStreaming = false;

    /*
       _____                                  _____      _   _   _
      / ____|                                / ____|    | | | | (_)
     | |     __ _ _ __ ___   ___ _ __ __ _  | (___   ___| |_| |_ _ _ __   __ _ ___
     | |    / _` | '_ ` _ \ / _ \ '__/ _` |  \___ \ / _ \ __| __| | '_ \ / _` / __|
     | |___| (_| | | | | | |  __/ | | (_| |  ____) |  __/ |_| |_| | | | | (_| \__ \
      \_____\__,_|_| |_| |_|\___|_|  \__,_| |_____/ \___|\__|\__|_|_| |_|\__, |___/
                                                                          __/ |
                                                                         |___/
    */

    /** The resolution of the camera */
    private final int WIDTH = 640;
    private final int HEIGHT = 360;

    /** The rotation of the camera view */
    private final OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;

    /** Calibration for the camera */
    private final double fx = 578.272;
    private final double fy = 578.272;
    private final double cx = 402.145;
    private final double cy = 221.506;

    /** The size of the april tag (in meters) */
    private final double TAG_SIZE = 0.037;

    /**
     * Initializes the camera and configures it, and sets the proper pipeline.
     *
     * @param hardwareMap the robots hardware map
     * @param telemetry the op-mode's telemetry
     */
    public WebcamSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        pipeline = new AprilTagDetectionPipeline(TAG_SIZE, fx, fy, cx, cy);

        webcam.setPipeline(pipeline);

        this.telemetry = telemetry;
    }

    public void startStreaming() {
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(WIDTH, HEIGHT, ROTATION);
                isStreaming = true;

                telemetry.addLine("Webcam is streaming");
                telemetry.update();
            }

            @Override
            public void onError(int errorCode) {
                isStreaming = false;

                telemetry.addLine("Error occurred during the opening of the webcam, error code: " + errorCode);
                telemetry.update();
            }
        });
    }


    public AprilTagDetectionPipeline getPipeline() {
        return pipeline;
    }

    public boolean isStreaming() {
        return isStreaming;
    }
}
