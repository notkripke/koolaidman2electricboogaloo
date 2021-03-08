package org.firstinspires.ftc.teamcode.Autonomous;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@Autonomous(group = "test", name = "TestAuto")

public class TestAuto extends GorillabotsCentral {
    public void runOpMode() {

        DcMotor Intake;
        Intake = hardwareMap.dcMotor.get("Intake");
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DcMotor ShooterMotor;
        ShooterMotor = hardwareMap.dcMotor.get("ShooterMotor");
        ShooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        DcMotor Transfer;
        Transfer = hardwareMap.dcMotor.get("Transfer");
        Transfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        initializeComponentsAutonomous();
        waitForStart();

//THIS AUTO IS FOR TESTING OF MOVEMENT CLASSES, DEGREES OF MOTION, AND ANY-------
//EXPERIMENTAL CHANGES. DESIGNED FOR QUICK TESTING AND TURNAROUND TIMES TO-------
//INCREASE EFFICIENCY OF WORK AND TESTING. DON'T DELETE CHANGES, JUST COMMENT----
//THEM OUT (//)------------------------------------------------------------------

        //GYRO FOR POWER SHOTS: 178 FOR FIRST, -176 FOR SECOND, -172 FOR LAST

//_______________________________________
        //SINGLE RING INTAKE FROM START
//MoveUntilTime(730,34,0.7);//33, 750
//stopMotors();
//sleep(200);
//Intake.setPower(-0.8);
//MoveUntilTime(300,190,.25);
//MoveUntilTime(800,0,0.3);
//sleep(1200);
//Intake.setPower(0);
//________________________________________
        //3 RINGS HIGH
        /*MoveUntilTime(1530, 189, 0.6);//1430
        stopMotors();
        sleep(200);
        //TurnAbsolute(2.3,10,30);
        TurnAbsolute(-9.7, .3, .6);//-15.3
        sleep(200);
        stopMotors();
        ShooterMotor.setPower(.91);
        sleep(1000);
        Transfer.setPower(0.4);
        sleep(500);
        Transfer.setPower(0.5);
        ShooterMotor.setPower(.88);
        Intake.setPower(-0.8);
        sleep(2500);
        MoveUntilTime(500, 180, .4);
        stopMotors();*/
        //______________________________________
        if(opModeIsActive()) {
          MoveUntilTime(1850,140,0.6);
          //TurnAbsolute(4,0.3,.6);
          sleep(400);
          ShooterMotor.setPower(0.78);
          sleep(800);
          Transfer.setPower(0.4);
          sleep(100);
          Transfer.setPower(0);
          sleep(200);
          TurnAbsolute(-6,.3,.6);
          sleep(300);
          Transfer.setPower(.4);
          sleep(100);
          Transfer.setPower(0);
          TurnAbsolute(-3,.2,.4);
          Transfer.setPower(.4);
          sleep(300);
          ShooterMotor.setPower(0);
          sleep(200);
          MoveUntilTime(200,180,0.6);

        }

        if(!opModeIsActive())
        {
            return;
        }

        return;
    }

}
