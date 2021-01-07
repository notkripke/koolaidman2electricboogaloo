package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;


@TeleOp(group = "AAAAAAAAAA", name = "WhatAndySuggested")
public class WhatAndySuggested extends GorillabotsCentral {

    @Override
    public void runOpMode() {

        initializeComponents();

        waitForStart();

        ElapsedTime SlowTimer = new ElapsedTime(); //creates timer to prevent rapid stage increase

        ElapsedTime SpinUpTimer = new ElapsedTime(); //Makes spin up time effect

        int slow = 0;

        DcMotor ShooterMotor;
        ShooterMotor = hardwareMap.dcMotor.get("ShooterMotor");

        final double INCREMENT   = -0.01;     // Amount to ramp motor(Higher numbers = faster)↓↓
        final int    CYCLE_MS    =   25;     // Change amount of time per wait/cycle
        final double MAX_FWD     =  -0.80;     // Maximum FWD power applied to motor
        boolean DriveSlow = false;
        boolean DriveSlowWatch = false;
        double ShootSpeed = -0.15;

        double x = 0;
        double r = 0;
        double y = 0;

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

            if (SpinUpTimer.time() <= 2000) {

                if (ShootSpeed > MAX_FWD) {
                    telemetry.addData("Shooter", "Not Ready");
                    telemetry.update();
                }

                ShooterMotor.setPower(ShootSpeed);
                if (ShootSpeed <= MAX_FWD) {
                    sleep(CYCLE_MS);
                }
            }

            if (SpinUpTimer.time() >= 2001){
                ShootSpeed = MAX_FWD;
                ShooterMotor.setPower(ShootSpeed);
            }

            //if statement watches↓↓

            if(gamepad1.dpad_left){
                ShootAlign(true, false);
            }

            if(gamepad1.dpad_right){
                ShootAlign(false, true);
            }

            if(gamepad1.b && SlowTimer.time() > 1.5)
                slow += 1;
                SlowTimer.reset();
            }

            if (gamepad1.left_trigger > 0.5){
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

            if(gamepad1.x){
                PowerShots(true,false);
            }

            if(gamepad1.y){
                PowerShots(false, true);
            }

            telemetry.addData("Slow Driving Timer (< 1.5 sec to change)", SlowTimer);
            telemetry.update();

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
                    if(gamepad2.right_trigger > 0.5){
                        FireRing();
                    }

                    telemetry.addData("Distance to left wall", sensors.getDistanceL());
                    telemetry.update();

                    telemetry.addData("Distance to right wall", sensors.getDistanceR());
                    telemetry.update();


            }
        }

