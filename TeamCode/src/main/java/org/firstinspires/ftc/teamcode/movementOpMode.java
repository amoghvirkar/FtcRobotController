package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Omni Linear OpMode", group="Linear Opmode")
public class movementOpMode extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor viperPulley = null;

    @Override
    public void runOpMode() {
        int viperPulleyPos = 0; //0:ground, 1:low, 2:medium, 3:top
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        viperPulley = hardwareMap.get(DcMotor.class, "viper_slide_controller");
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        viperPulley.setDirection(DcMotor.Direction.REVERSE);
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
            double viperPulleyPower =
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);
            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }
            if (gamepad2.a = true) {
                if (viperPulleyPos == 0) {
                    //Slide does not move
                } else if (viperPulleyPos == 1) {
                    //Slide moves downward to Ground Position
                } else if (viperPulleyPos == 2) {
                    //Slide moves downward to Ground
                } else if (viperPulleyPos == 3) {
                    //Slide moves downward to ground
                }
                viperPulleyPos = 0;
            } else if (gamepad2.b = true) {
                if (viperPulleyPos == 1) {
                    //Slide does not move
                } else if (viperPulleyPos == 0) {
                    //Slide moves up
                } else if (viperPulleyPos == 2) {
                    //Slide moves down
                } else if (viperPulleyPos == 3) {
                    //Slide moves down
                }
                viperPulleyPos = 1;
            } else if (gamepad2.x = true) {
                if (viperPulleyPos == 2) {
                    //Slide does not move
                } else if (viperPulleyPos == 0) {
                    //Slide moves up
                } else if (viperPulleyPos == 1) {
                    //Slide moves up
                } else if (viperPulleyPos == 3) {
                    //Slide moves down
                }
                viperPulleyPos = 2;
            } else if (gamepad2.y = true) {
                if (viperPulleyPos == 3) {
                    //Slide does not move
                } else if (viperPulleyPos == 0) {
                    //Slide moves up
                } else if (viperPulleyPos == 1) {
                    //Slide moves up
                } else if (viperPulleyPos == 2) {
                    //Slide moves up
                }
                viperPulleyPos = 3;
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
