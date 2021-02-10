package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@TeleOp(group = "test", name = "DistanceSensorTest")
public class DistanceSensorTest extends GorillabotsCentral {
    public void runOpMode() {
        initializeComponents();

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("l", sensors.getDistanceL());
            telemetry.addData("b", sensors.getDistanceB());
            telemetry.update();
        }
    }
}