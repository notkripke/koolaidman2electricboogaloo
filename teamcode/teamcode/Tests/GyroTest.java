package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@TeleOp(group = "test", name = "GyroTest")
public class GyroTest extends GorillabotsCentral {
    public void runOpMode() {
        initializeComponents();

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Gyro:", gyro.getAngle());
            telemetry.update();
        }
    }
}