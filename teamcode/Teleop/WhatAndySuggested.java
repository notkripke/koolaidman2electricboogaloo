package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;


@TeleOp(group = "AAAAAAAAAA", name = "WhatAndySuggested")
public class WhatAndySuggested extends GorillabotsCentral {

    @Override
    public void runOpMode() {

        initializeComponents();

        waitForStart();

        int slow = 0;

        DcMotor ShooterMotor;
        ShooterMotor = hardwareMap.dcMotor.get("ShooterMotor");

        final double INCREMENT   = -0.01;     // Amount to ramp motor(Higher numbers = faster)↓↓
        final int    CYCLE_MS    =   25;     // Change amount of time per wait/cycle
        final double MAX_FWD     =  -0.80;     // Maximum FWD power applied to motor
        double ShootSpeed = -0.15;
        double x;
        double r;
        double y;
        int distance;
        int leftSweetSpotMin = 17;//These
        int leftSweetSpotMax = 24;//are for
        int rightSweetSpotMin = 17;//manual aiming
        int rightSweetSpotMax = 24;//telemetry

        while (opModeIsActive()) {

            // SET DRIVING STUFF ↓

            telemetry.addData("Drive", "better Connor");
            telemetry.update();
            telemetry.addData("Drive", "faster Connor");
            telemetry.update();
            telemetry.addData("Stop", "missing Connor");
            telemetry.update();

            x = gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            r = gamepad1.right_stick_x;

            // ↓ SHOOTER STUFF ↓

            ShootSpeed += INCREMENT;
            if (ShootSpeed <= MAX_FWD) {
                ShootSpeed = MAX_FWD;
            }

            if (ShootSpeed == MAX_FWD){
                telemetry.addData("Shooter", "Ready");
                telemetry.update();
            }

            if (ShootSpeed > MAX_FWD){
                telemetry.addData("Shooter", "Not Ready");
                telemetry.update();
            }

            ShooterMotor.setPower(ShootSpeed);
            if (ShootSpeed <= MAX_FWD) {
                sleep(CYCLE_MS);
            }

            //if statement watches↓↓

            if (gamepad1.b && gamepad1.dpad_left || gamepad1.b && gamepad1.dpad_right){
                distance = 0;
            }

            if(gamepad1.dpad_left){
                AlignLeft();
            }

            if(gamepad1.dpad_right){
                AlignRight();
            }

            if(gamepad1.b){
                slow += 1;
            }

            if(gamepad1.left_trigger > 0.5){
                StartIntake();
                telemetry.addData("Intake", "spinning");
                telemetry.update();
            }
            else{
                StopIntake();
                telemetry.addData("Intake", "not spinning");
                telemetry.update();
            }

            if (gamepad1.left_bumper){
                SpitRing();
            }

            if (sensors.getDistanceL() > leftSweetSpotMin && sensors.getDistanceL() < leftSweetSpotMax){
                telemetry.addData("Lined", "up");
                telemetry.update();
            }

            if(sensors.getDistanceL() < leftSweetSpotMin || sensors.getDistanceL() > leftSweetSpotMax){
                telemetry.addData("Not", "lined up");
                telemetry.update();
            }

            if (sensors.getDistanceR() > rightSweetSpotMin && sensors.getDistanceR() < rightSweetSpotMax){
                telemetry.addData("Lined", "up");
                telemetry.update();
            }

            if (sensors.getDistanceR() < rightSweetSpotMin && sensors.getDistanceR() > rightSweetSpotMax){
                telemetry.addData("Not", "lined up");
                telemetry.update();
            }

            if (sensors.getDistanceL() < leftSweetSpotMin){
                telemetry.addData("Move", "right");
                telemetry.update();
            }

            if (sensors.getDistanceL() > leftSweetSpotMax){
                telemetry.addData("Move", "left");
            }

            if (sensors.getDistanceR() < rightSweetSpotMin){
                telemetry.addData("Move", "left");
                telemetry.update();
            }

            if (sensors.getDistanceR() > rightSweetSpotMax){
                telemetry.addData("Move", "right");
                telemetry.update();
            }

            switch (slow) {

                case 0:
                    drive.go(x, y, r); // drive speed max

                    telemetry.addData("Driving slow?", "No");
                    telemetry.update();
                    break;
                case 1:
                    drive.go(x * 0.25, y * 0.25, r * 0.25);

                    telemetry.addData("Driving slow?", "Yes");
                    telemetry.update();
                    break;
                case 2: //for looping
                    slow = 0;
                    break;
            }
                    if(gamepad1.right_trigger > 0.5){
                        FireRing();
                    }

                    telemetry.addData("Distance to left wall", sensors.getDistanceL());
                    telemetry.update();

                    telemetry.addData("Distance to right wall", sensors.getDistanceR());
                    telemetry.update();


            }
            telemetry.update();
        }
    }
