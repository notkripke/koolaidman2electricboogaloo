package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@Autonomous(group = "test", name = "AlignWallL")

public class AlignWallL extends GorillabotsCentral {
    public void runOpMode() {

        initializeComponentsAutonomous();

        waitForStart();

        MoveUntilRangeLG(15, 0, 50, 90);
        MoveUntilRangeB(10, 0, 50);

        telemetry.addData("DistanceL:", sensors.getDistanceL());
        telemetry.addData("DistanceB:", sensors.getDistanceB());
        telemetry.update();
    }

}
