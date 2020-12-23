package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;


@TeleOp(group = "AAAAAAAAAA", name = "VanillaDrive")
public class VanillaDrive extends GorillabotsCentral {

    @Override
    public void runOpMode() {

        initializeComponents();

        waitForStart();

        ElapsedTime SlowTimer = new ElapsedTime(); //creates timer to prevent rapid stage increase

        int slow = 0;

        boolean DriveSlow = false;
        boolean DriveSlowWatch = false;

        double x = 0;
        double r = 0;
        double y = 0;

        while (opModeIsActive()) {

            // SET DRIVING STUFF ↓

            x = gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            r = gamepad1.right_stick_x;

            if(gamepad1.b && SlowTimer.time() > 1.5)
                slow += 1;
            SlowTimer.reset();
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
    }
}

