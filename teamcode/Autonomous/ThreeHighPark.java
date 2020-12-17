package org.firstinspires.ftc.teamcode.Autonomous;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@Autonomous(group = "test", name = "ThreeHighPark")

public class ThreeHighPark extends GorillabotsCentral {
    public void runOpMode() {

        waitForStart();

        final double INCREMENT = -0.01;     // amount to ramp motor each CYCLE_MS cycle
        final int CYCLE_MS = 50;     // period of each cycle
        final double MAX_FWD = -0.80;     // Maximum FWD power applied to motor

        double ShootSpeed = 0;

        while (ShootSpeed > MAX_FWD){
            ShootSpeed += INCREMENT;
            if (ShootSpeed <= MAX_FWD) {
                ShootSpeed = MAX_FWD;

            }
        }

        MoveUntilEncoderGYRO(32, 0, .6, 0);
        TurnAbsolute(20,0.3,0.8);
        sleep(400);
        FireRing();
        FireRing();
        FireRing();
        MoveUntilEncoderGYRO(8,0,.4,0);
        sleep(2000);//wait until end of auto (adjustment needed to get final time to 30 seconds)

        return;
    }

}
