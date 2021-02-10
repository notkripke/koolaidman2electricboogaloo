package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Sensors
{

    public Rev2mDistanceSensor rangeB;
    public Rev2mDistanceSensor rangeL;

    public Sensors(HardwareMap hardwareMap, Telemetry telemetry)
    {

        rangeB = hardwareMap.get(Rev2mDistanceSensor.class, "rangeB");
        rangeL = hardwareMap.get(Rev2mDistanceSensor.class, "rangeL");
    }

    public double getDistanceB(){
        return rangeB.getDistance(DistanceUnit.INCH);
    }

    public double getDistanceL(){
        return rangeL.getDistance(DistanceUnit.INCH);
    }
}
