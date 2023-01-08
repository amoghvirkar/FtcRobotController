package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.concurrent.TimeUnit;

@Autonomous(name = "Ice Code Autonomous", group = "Linear Opmode")
public class autonomousOpMode extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor viperPulley = null;
    private void quarterTurn(int numTurns) throws InterruptedException {
        int turnCounter = 0;
        while (turnCounter<numTurns) {
            leftFrontDrive.setPower(0.25);
            leftBackDrive.setPower(0.25);
            rightFrontDrive.setPower(-0.25);
            rightBackDrive.setPower(-0.25);
            TimeUnit.SECONDS.sleep(1);
            turnCounter++;
        }
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
    private void moveForward(int time) throws InterruptedException {
        //6 ft/s
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        TimeUnit.MILLISECONDS.sleep(time);
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
    private void pickUpCone(int junction) throws InterruptedException {
        int junctionCounter = 0;
        //close claw
        while (junctionCounter != junction) {
            viperPulley.setPower(1);
            TimeUnit.SECONDS.sleep(2);
            viperPulley.setPower(0);
            junctionCounter++;
        }
    }
    private void dropCone() throws InterruptedException{
        //open the claw
    }
    public void runOpMode() throws InterruptedException {
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        viperPulley = hardwareMap.get(DcMotor.class, "viper_slide_controller");
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        viperPulley.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            moveForward(2600);
            quarterTurn(1);
            moveForward(500);
            break;
        }
    }
}