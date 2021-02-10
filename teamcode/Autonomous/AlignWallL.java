package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@Autonomous(group = "test", name = "AlignWallL")

public class AlignWallL extends GorillabotsCentral {
    public void runOpMode() {

        initializeComponentsAutonomous();

        waitForStart();

        TurnAbsolute(40,10,30);
        TurnAbsolute(-40,10,30);

        MoveUntilRangeLG(15, 270, 50, 90);
        stopMotors();


    }

}
