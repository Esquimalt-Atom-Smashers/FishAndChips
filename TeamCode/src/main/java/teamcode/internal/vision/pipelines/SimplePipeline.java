package teamcode.internal.vision.pipelines;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.FastFeatureDetector;
import org.opencv.features2d.Feature2D;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class SimplePipeline extends OpenCvPipeline {

    public int threshold1 = 0;
    public int threshold2 = 0;

    private final Telemetry telemetry;

    public SimplePipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Mat gray = new Mat();
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGBA2GRAY);


        Imgproc.blur(gray, gray, new Size(3, 3));

        Mat circles = new Mat();
        Imgproc.HoughCircles(gray, circles, Imgproc.HOUGH_GRADIENT, 1.2, 100);

        if (circles.empty()) {
            telemetry.addLine("Detected Circle");

            for (int x = 0; x < circles.cols(); x++) {
                double[] data = circles.get(0, x);

                Point circleCenter = new Point(Math.round(data[0]), Math.round(data[1]));

                Imgproc.circle(input, circleCenter, 1, new Scalar(255, 0, 0), 2, 10, 0);
            }
        }

        telemetry.update();

        return input;
    }
}
