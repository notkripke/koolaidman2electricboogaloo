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

        waitForStart();

        ElapsedTime SlowTimer = new ElapsedTime(); //creates timer to prevent rapid stage increase
        ElapsedTime IntakeTimer = new ElapsedTime();

        int slow = 0;
        boolean IntakeToggle = false;

        DcMotor ShooterMotor;
        ShooterMotor = hardwareMap.dcMotor.get("ShooterMotor");
        ShooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        DcMotor Intake;
        Intake = hardwareMap.dcMotor.get("Intake");
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        while (opModeIsActive()) {

            // SET DRIVING STUFF ↓

            x = gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            r = gamepad1.right_stick_x;



            if (gamepad1.x) { // X STARTS SHOOTER
                ShooterMotor.setPower(1);
                telemetry.addData("Shooter", "on");
            }
            if (gamepad1.y){
                ShooterMotor.setPower(0);
                telemetry.addData("Shooter","off");
            }

            if (gamepad1.left_bumper && IntakeTimer.time() > 1.5){
                IntakeToggle = !IntakeToggle;
            }
            if (IntakeToggle = true){
                Intake.setPower(0.9);
                telemetry.addData("Intake","on");
            }
            if(IntakeToggle = false){
                Intake.setPower(0);
                telemetry.addData("Intake","off");
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

            switch (slow) {

                case 0:
                    drive.go(-x, -y, -r); // drive speed max

                    telemetry.addData("Driving slow?", "No");
                    break;
                case 1:
                    drive.go(-x * 0.25, -y * 0.25, -r * 0.25);

                    telemetry.addData("Driving slow?", "Yes");
                    break;
                case 2: //for looping
                    slow = 0;
                    break;
            }
            if (gamepad1.left_trigger > 0.6 && sensors.getDistanceL() < 21 && sensors.getDistanceB() > 76){
                stopMotors();
                sleep(600);
                telemetry.addData("In", "zone");
            }
            telemetry.update();
        }
        ShooterMotor.setPower(0);
        stopMotors();
    }
}


