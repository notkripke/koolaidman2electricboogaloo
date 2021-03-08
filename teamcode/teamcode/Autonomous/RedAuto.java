package org.firstinspires.ftc.teamcode.Autonomous;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Components.VisionPipeline;
import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@Autonomous(group = "test", name = "RedAuto")

public class RedAuto extends GorillabotsCentral {
    public void runOpMode() {

        int startloop = 0;

        initializeComponentsAutonomous();

        startVisionProcessing();
        webcam.openCameraDevice();
        webcam.setPipeline(pipeline);

        int rings = 0;

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



        while(startloop == 0){//startloop == 0

            telemetry.addData("Rings",  pipeline.getPos());
            telemetry.update();

            if(pipeline.getPos() == 0){
                rings = 0;
            }
            else if (pipeline.getPos() == 1){
                rings = 1;
            }
            else if (pipeline.getPos() == 4){
                rings = 4;
            }}

        waitForStart();

        switch (rings) {
            case 0:
                //Path for no rings
                telemetry.addData("Track", "0");
                telemetry.update();
                MoveUntilTime(1530, 192, 0.6);//1430 LINE UP TO FIRST P.S
                sleep(200);
                //TurnAbsolute(2.3,10,30);
                TurnAbsolute(-9.7, .3, .6);//-15.3 TURN INTO POS.
                sleep(200);
                ShooterMotor.setPower(.91);
                sleep(1000);
                Transfer.setPower(0.4);//FIRE FIRST RING
                sleep(100);
                Transfer.setPower(0);
                sleep(100);
                TurnAbsolute(-3,.2,.6);//TURN INTO 2ND P.S
                sleep(300);
                Transfer.setPower(0.4);//SHOOT RING 2
                sleep(200);
                Transfer.setPower(0);
                TurnAbsolute(-2,.2,.6);//TURN INTO 3RD P.S
                sleep(300);
                Transfer.setPower(0.4);//SHOOT LAST RING
                Intake.setPower(-0.8);
                sleep(200);
                Intake.setPower(0);
                sleep(500);
                ShooterMotor.setPower(0);
                Transfer.setPower(0);
                MoveUntilTime(500,189,0.7);//PARK
                stopMotors();
                break;

            case 1:
                //path for one ring
                telemetry.addData("Track", "1");
                telemetry.update();
                sleep(4000);
                break;

            case 4:
                //path for full stack
                telemetry.addData("Track", "4");
                telemetry.update();
                sleep(4000);
                break;



        }

        return;
    }

}
