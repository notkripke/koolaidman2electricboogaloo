package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;


@TeleOp(group = "AAAAAAAAAA", name = "TestDrive")
public class TestDrive extends GorillabotsCentral {

    @Override
    public void runOpMode() {

        initializeComponents();

        double x = 0;
        double r = 0;
        double y = 0;

        waitForStart();

        ElapsedTime SlowTimer = new ElapsedTime(); //creates timer to prevent rapid stage increase
        ElapsedTime IntakeTime = new ElapsedTime();

        int slow = 0;
        int IntakeToggle = 0;

        DcMotor ShooterMotor;
        ShooterMotor = hardwareMap.dcMotor.get("ShooterMotor");
        ShooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        DcMotor Transfer;
        Transfer = hardwareMap.dcMotor.get("Transfer");
        Transfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

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
            if (gamepad1.y || gamepad2.y){
                ShooterMotor.setPower(0);
                telemetry.addData("Shooter","off");
            }
            if(gamepad1.dpad_up) {
                ShooterMotor.setPower(0.75);
            }
            if(gamepad1.dpad_down){
                Intake.setPower(0.8);
            }

            if(gamepad1.right_trigger > 0.4 || gamepad1.left_trigger > 0.4){
                if(gamepad1.right_trigger > 0.4){
                Transfer.setPower(0.6);
                }
                if(gamepad1.left_trigger > 0.4){
                    Transfer.setPower(-0.6);
                }
            }
            if(!(gamepad1.right_trigger > 0.4 || gamepad1.left_trigger > 0.4)){
                Transfer.setPower(0);
            }

        if(gamepad1.left_bumper && IntakeTime.time() > 1){
            IntakeToggle +=1;
            IntakeTime.reset();
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

            switch(IntakeToggle){
                case 0:
                    Intake.setPower(0);
                    telemetry.addData("Intake","Off");
                    break;
                case 1:
                    Intake.setPower(-0.8);
                    telemetry.addData("Intake","On");
                    break;
                case 2:
                    IntakeToggle = 0;
                    break;
            }

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
            telemetry.update();
        }
        ShooterMotor.setPower(0);
        stopMotors();
    }
}


