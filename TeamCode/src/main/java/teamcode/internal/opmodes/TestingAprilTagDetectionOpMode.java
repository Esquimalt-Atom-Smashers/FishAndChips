package org.firstinspires.ftc.teamcode.internal.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.internal.Robot;
import org.firstinspires.ftc.teamcode.internal.util.AprilTagConstants;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.apriltag.AprilTagDetectorJNI;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;

@TeleOp(name="April Tag Detection OpMode")
public class TestingAprilTagDetectionOpMode extends LinearOpMode {
    private Robot robot;

    private boolean tagFound = false;
    private int parkingZone;

    private AprilTagDetection tag;

    private OpenCvWebcam webcam;
    private AT pipeline;

    private boolean isStreaming = false;

    private final int WIDTH = 640;
    private final int HEIGHT = 360;
    private final OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;

    private final double fx = 578.272;
    private final double fy = 578.272;
    private final double cx = 402.145;
    private final double cy = 221.506;

    //Meters
    private final double TAG_SIZE = 0.037;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        pipeline = new AT(TAG_SIZE, fx, fy, cx, cy);

        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(800, 448, ROTATION);

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

        while (!isStarted() && !isStopRequested()) {


        }

        waitForStart();
        while (opModeIsActive()) {
            ArrayList<AprilTagDetection> detections = pipeline.getLatestDetections();

            if (detections.size() != 0 ) {
                for (AprilTagDetection tag : detections) {
                    if (AprilTagConstants.parkingZone1Tags.contains(tag.id)) {
                        tagFound = true;
                        telemetry.addLine("Tag ID: " + tag.id);
                        telemetry.update();
                    }
                }
            }
            if (!tagFound) {
                telemetry.addLine("No tag detected");
                telemetry.update();
            }

        }
    }

    class AT extends OpenCvPipeline {

        private long aprilTagFamily;

        private ArrayList<AprilTagDetection> detections = new ArrayList<>();
        private ArrayList<AprilTagDetection> updatedDetections = new ArrayList<>();

        private final Object detectionsUpdateSync = new Object();

        private final Mat gray = new Mat();
        private Mat cameraMatrix;

        double fx;
        double fy;
        double cx;
        double cy;

        // UNITS ARE METERS
        double tagsize;
        double tagsizeX;
        double tagsizeY;

        private float decimation;
        private boolean needToSetDecimation;
        private final Object decimationSync = new Object();

        public AT(double tagSize, double fx, double fy, double cx, double cy) {

            this.tagsize = tagsize;
            this.tagsizeX = tagsize;
            this.tagsizeY = tagsize;
            this.fx = fx;
            this.fy = fy;
            this.cx = cx;
            this.cy = cy;

            cameraMatrix = new Mat(3,3, CvType.CV_32FC1);

            //     Construct the camera matrix.
            //
            //      --         --
            //     | fx   0   cx |
            //     | 0    fy  cy |
            //     | 0    0   1  |
            //      --         --
            //
            cameraMatrix.put(0,0, fx);
            cameraMatrix.put(0,1,0);
            cameraMatrix.put(0,2, cx);

            cameraMatrix.put(1,0,0);
            cameraMatrix.put(1,1,fy);
            cameraMatrix.put(1,2,cy);

            cameraMatrix.put(2, 0, 0);
            cameraMatrix.put(2,1,0);
            cameraMatrix.put(2,2,1);

            aprilTagFamily = AprilTagDetectorJNI.createApriltagDetector(AprilTagDetectorJNI.TagFamily.TAG_36h11.string, 3, 3);
        }

        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGBA2GRAY);

            detections = AprilTagDetectorJNI.runAprilTagDetectorSimple(aprilTagFamily, gray, tagsize, fx, fy, cx, cy);

            synchronized (detectionsUpdateSync) {
                updatedDetections = detections;
            }

            return input;
        }

        public void setDecimation(float decimation)
        {
            synchronized (decimationSync)
            {
                this.decimation = decimation;
                needToSetDecimation = true;
            }
        }

        public ArrayList<AprilTagDetection> getLatestDetections() {
            return detections;
        }

        public ArrayList<AprilTagDetection> getUpdatedDetections() {
            return updatedDetections;
        }
    }

}
