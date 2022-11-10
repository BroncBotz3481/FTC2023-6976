package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


    @TeleOp(name = "TeleOp2023New6976", group = "6976")
    public class TeleOp2023New6976 extends LinearOpMode {
        Team6976HWMap2023 robot = new Team6976HWMap2023();


        @Override
        public void runOpMode() {
            robot.Map(hardwareMap);
            telemetry.addData("Say", "TeleOp Starting");
            telemetry.update();
            robot.DriveRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.DriveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.DriveLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.DriveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.DriveRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.DriveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.Intake.setPosition(0);
            robot.Intake.setPosition(1);

            //robot.Lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_PARTY_PALETTE);
            waitForStart();

            while (opModeIsActive()) {
                boolean speedslow = gamepad1.right_bumper;
                double mag = speedslow ? 1.0 : 0.5;

                double y = gamepad1.left_stick_y; // Remember, this is reversed!
                double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                double rx = -gamepad1.right_stick_x;

                // hello
                // Denominator is the largest motor power (absolute value) or 1
                // This ensures all the powers maintain the same ratio, but only when
                // at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                double frontLeftPower = (y + x + rx) / denominator;
                double backLeftPower = (y - x + rx) / denominator;
                double frontRightPower = (y - x - rx) / denominator;
                double backRightPower = (y + x - rx) / denominator;

                telemetry.addData("RightFront", robot.DriveRightFront.getCurrentPosition());
                telemetry.addData("RightBack", robot.DriveRightBack.getCurrentPosition());
                telemetry.addData("LeftFront", robot.DriveLeftFront.getCurrentPosition());
                telemetry.addData("LeftBack", robot.DriveLeftBack.getCurrentPosition());
                telemetry.update();

                robot.DriveLeftFront.setPower(frontLeftPower * mag);
                robot.DriveLeftBack.setPower(backLeftPower * mag);
                robot.DriveRightFront.setPower(frontRightPower * mag);
                robot.DriveRightBack.setPower(backRightPower * mag);

                if (gamepad2.b) {
                    robot.Elevator.setPower(1);
                }
                else if (gamepad2.a){
                    robot.Elevator.setPower(-1);
                }
                else {
                    robot.Elevator.setPower(0);
                }

                if (gamepad2.x){
                    robot.Intake.setPosition(1);
                }
                else{
                    robot.Intake.setPosition(0);
                }

            }
        }
    }
