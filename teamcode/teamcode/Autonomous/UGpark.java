package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@Disabled
@Autonomous(group = "test", name = "UGpark")

public class UGpark extends GorillabotsCentral {
    public void runOpMode() {

        initializeComponentsAutonomous();

        waitForStart();

        MoveUntilTime(1250,180,0.7);//180 DEGREES IS FORWARDS, 0 IS BACK
        if(!opModeIsActive())
        {
            return;
        }
        sleep(28000);//time until end of auto period

        return;
    }

}
