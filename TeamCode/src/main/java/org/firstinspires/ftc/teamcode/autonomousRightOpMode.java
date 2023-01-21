package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.concurrent.TimeUnit;

@Autonomous(name = "Ice Code Autonomous", group = "Linear Opmode")
public class autonomousRightOpMode extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor viperPulley = null;
    private CRServo claw = null;
    private CRServo claw2 = null;
    private void turn(int ticks) throws InterruptedException {
        leftFrontDrive.setTargetPosition(ticks);
        leftBackDrive.setTargetPosition(ticks);
        rightFrontDrive.setTargetPosition(ticks);
        rightBackDrive.setTargetPosition(ticks);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontDrive.setPower(0.25);
        leftBackDrive.setPower(0.25);
        rightFrontDrive.setPower(-0.25);
        rightBackDrive.setPower(-0.25);
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
    private void moveForward(int ticks) throws InterruptedException {
        leftFrontDrive.setTargetPosition(ticks);
        leftBackDrive.setTargetPosition(ticks);
        rightFrontDrive.setTargetPosition(ticks);
        rightBackDrive.setTargetPosition(ticks);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontDrive.setPower(0.25);
        leftBackDrive.setPower(0.25);
        rightFrontDrive.setPower(0.25);
        rightBackDrive.setPower(0.25);
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
    private void pickUpCone() throws InterruptedException {
        claw.setPower(0);
        claw2.setPower(-0.35);
        viperPulley.setTargetPosition(2500);
        viperPulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperPulley.setPower(0.25);
        viperPulley.setPower(0);
    }
    private void dropCone() throws InterruptedException{
        claw.setPower(-0.4);
        claw2.setPower(0.15);
        TimeUnit.SECONDS.sleep(1);
        claw.setPower(0);
        claw2.setPower(-0.35);
        viperPulley.setTargetPosition(-2500);
        viperPulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperPulley.setPower(0.25);
        viperPulley.setPower(0);
    }
    public void runOpMode() throws InterruptedException {
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        viperPulley = hardwareMap.get(DcMotor.class, "viper_slide_controller");
        claw = hardwareMap.get(CRServo.class, "claw");
        claw2 = hardwareMap.get(CRServo.class, "claw2");
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        viperPulley.setDirection(DcMotorSimple.Direction.FORWARD);
        claw.setDirection(DcMotorSimple.Direction.FORWARD);
        claw2.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            pickUpCone();
            moveForward(1800);
            turn(2700);
            dropCone();
            turn(1800);
            moveForward(500);
            break;
        }
    }
}