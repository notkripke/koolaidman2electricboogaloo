package org.firstinspires.ftc.teamcode.Components;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class VisionPipeline extends OpenCvPipeline
{
    /*
     * An enum to define the skystone position
     */
    public enum RingPosition
    {
        FOUR,
        ONE,
        NONE
    }

    /*
     * Some color constants
     */
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar GREEN = new Scalar(0, 255, 0);

    /*
     * The core values which define the location and size of the sample regions
     */
    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point( 150,160);//250,170 (//80)

    static final int REGION_WIDTH = 40;//50
    static final int REGION_HEIGHT = 50;

    final int FOUR_RING_THRESHOLD = 146;//150
    final int ONE_RING_THRESHOLD = 130;//80

    Point region1_pointA = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x,
            REGION1_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pointB = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    /*
     * Working variables
     */
    Mat region1_Cb;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    int avg1;
   public int pos;

    // Volatile since accessed by OpMode thread w/o synchronization
    public volatile RingPosition numRings = RingPosition.FOUR;

    /*
     * This function takes the RGB frame, converts to YCrCb,
     * and extracts the Cb channel to the 'Cb' variable
     */
    void inputToCb(Mat input)
    {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 1);
    }

    @Override
    public void init(Mat firstFrame)
    {
        inputToCb(firstFrame);

        region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
    }

    @Override
    public Mat processFrame(Mat input)
    {
        inputToCb(input);

        avg1 = (int) Core.mean(region1_Cb).val[0];

        Imgproc.rectangle(
                input, // Buffer to draw on
                region1_pointA, // First point which defines the rectangle
                region1_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

       // numRings = RingPosition.FOUR; // Record our analysis
        if(avg1 > FOUR_RING_THRESHOLD){
            numRings = RingPosition.FOUR;
            pos = 4;
        }else if (avg1 > ONE_RING_THRESHOLD){
            numRings = RingPosition.ONE;
            pos = 1;
        }else{
            numRings = RingPosition.NONE;
            pos = 0;
        }

        return input;
    }
    public int getPos(){
        return pos;
    }

    public int getAnalysis()
    {
        return avg1;
    }
}