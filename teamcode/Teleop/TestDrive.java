package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;


@TeleOp(group = "AAAAAAAAAA", name = "TestDrive")
public class TestDrive extends GorillabotsCentral {

    @Override
    public void runOpMode() {
                //THIS CODE IS FOR TESTING NEW TELEOP ACTIONS
                //COMMENT OUT OLD OPERATIONS... DO NOT DELETE UNLESS UNNECESSARY!!!

        initializeComponents();

        double x = 0;
        double r = 0;
        double y = 0;

        double ShootStart = 0;

        waitForStart();

        ElapsedTime SlowTimer = new ElapsedTime(); //creates timer to prevent rapid stage increase
        ElapsedTime SpinUpTimer = new ElapsedTime(); //Makes spin up time effect

        int slow = 0;

        DcMotor ShooterMotor;
        ShooterMotor = hardwareMap.dcMotor.get("ShooterMotor");
        ShooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        final double INCREMENT = -0.01;     // amount to ramp motor each CYCLE_MS cycle
        final int CYCLE_MS = 50;     // period of each cycle
        final double MAX_FWD = -0.80;     // Maximum FWD power applied to motor

        double ShootSpeed = 0;


        while (opModeIsActive()) {

            // SET DRIVING STUFF ↓

            x = gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            r = gamepad1.right_stick_x;

            if (gamepad1.x) { // X STARTS SHOOTER
                ShootStart = 1;
            }

            if (ShootStart == 1){

                ShootSpeed += INCREMENT;
                if (ShootSpeed <= MAX_FWD) {
                    ShootSpeed = MAX_FWD;
                }

                if (ShootSpeed == MAX_FWD) {
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
            }

            if (gamepad1.b && SlowTimer.time() > 1.5) {
                slow += 1;
                SlowTimer.reset();
            }
                /* THIS IS NON-TOGGLE SLOWDRIVE:
            if(gamepad1.left_trigger > 0.3){
                drive.go(x * 0.25, y * 0.25, r * 0.25);
            }
            else {
                drive.go(x * 0.85, y * 0.85, r * 0.85);
            }

                 */

            telemetry.addData("Sensor L:", sensors.getDistanceL());
            telemetry.addData("SensorB:", sensors.getDistanceB());
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

        }
        ShooterMotor.setPower(0);
    }
}


