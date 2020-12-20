package org.firstinspires.ftc.teamcode.Autonomous;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@Autonomous(group = "test", name = "ThreeHighPark")

public class BluePowerShotsPark extends GorillabotsCentral {
    public void runOpMode() {

        initializeComponentsAutonomous();

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

        MoveUntilEncoderGYRO(32,0,0.6,0);//up to line
        PowerShots(true,false);//shoot pow
        MoveUntilEncoderGYRO(8,0,.4,0);//park
        sleep(2000);//wait until end of auto (adjustment needed to get final time to 30 seconds)

        return;
    }

}
