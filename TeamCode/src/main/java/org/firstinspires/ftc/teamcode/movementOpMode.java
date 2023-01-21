package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.concurrent.TimeUnit;

@TeleOp(name="Ice Code TeleOp", group="Linear Opmode")
public class movementOpMode extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor viperPulley = null;

    @Override
    public void runOpMode() throws InterruptedException {
        int position = 0;
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        viperPulley = hardwareMap.get(DcMotor.class, "left_front_drive");
        CRServo claw = hardwareMap.get(CRServo.class, "claw");
        CRServo claw2 = hardwareMap.get(CRServo.class, "claw2");
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        viperPulley.setDirection(DcMotor.Direction.REVERSE);
        claw.setDirection(DcMotorSimple.Direction.FORWARD);
        claw2.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            double max;
            double axial   = -gamepad1.left_stick_y;
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);
            if (gamepad2.right_trigger > 0) {
                viperPulley.setPower(0.5);
            } else if (gamepad2.left_trigger > 0) {
                viperPulley.setPower(-0.5);
            } else {
                viperPulley.setPower(0.1);
            }
            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }
            if (gamepad1.left_bumper == true && gamepad1.right_bumper == true) {
                break;
            }
            if (gamepad2.a == true) {
                claw.setPower(0);
                claw2.setPower(-0.35);
            } else if (gamepad2.b == true) {
                claw.setPower(-0.4);
                claw2.setPower(0.15);
            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("axial, lateral, yaw", "%4.2f, %4.2f, %4.2f", axial, lateral, yaw);
            telemetry.addData("Gamepad1 Status", "" +  gamepad1.x + " " + gamepad1.a);
            telemetry.update();
        }
    }
}
