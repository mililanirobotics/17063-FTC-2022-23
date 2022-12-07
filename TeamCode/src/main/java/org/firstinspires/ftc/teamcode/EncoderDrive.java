package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class EncoderDrive {

    final HardwareDeclarations robot = new HardwareDeclarations();

    final double PULSES_PER_ROTATION = 28;
    final double COUNTS_PER_ROTATION = PULSES_PER_ROTATION * 4;
    final double DRIVE_GEAR_REDUCTION = 19.2;
    final double WHEEL_DIAMETER = 3.77953;
    final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final double COUNTS_PER_INCH = (COUNTS_PER_ROTATION * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE;

    public void init(LinearOpMode linearOpMode, Telemetry telemetry) {
        robot.init(linearOpMode.hardwareMap);

        telemetry.addData("Status", "Encoder Drive Initialized");
        telemetry.update();
    }


    public void operate(LinearOpMode linearOpMode, double speed, double distance, String direction, Telemetry telemetry) {
        int motorTarget = (int)(distance * COUNTS_PER_INCH);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction.equals("forward")) {

            robot.leftFrontDrive.setTargetPosition(-motorTarget);
            robot.rightFrontDrive.setTargetPosition(-motorTarget);
            robot.leftBackDrive.setTargetPosition(-motorTarget);
            robot.rightBackDrive.setTargetPosition(-motorTarget);

            robot.leftFrontDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.leftBackDrive.setPower(-speed);
            robot.rightBackDrive.setPower(speed);

        }
        else if (direction.equals("backward")) {

            robot.leftFrontDrive.setTargetPosition(motorTarget);
            robot.rightFrontDrive.setTargetPosition(motorTarget);
            robot.leftBackDrive.setTargetPosition(motorTarget);
            robot.rightBackDrive.setTargetPosition(motorTarget);

            robot.leftFrontDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.leftBackDrive.setPower(speed);
            robot.rightBackDrive.setPower(speed);
        }
        else if (direction.equals("left"))
        {
            robot.leftFrontDrive.setTargetPosition(motorTarget);
            robot.rightFrontDrive.setTargetPosition(-motorTarget);
            robot.leftBackDrive.setTargetPosition(motorTarget);
            robot.rightBackDrive.setTargetPosition(motorTarget);

            robot.leftFrontDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.leftBackDrive.setPower(-speed);
            robot.rightBackDrive.setPower(speed);
        }
        else if (direction.equals("right"))
        {
            robot.leftFrontDrive.setTargetPosition(-motorTarget);
            robot.rightFrontDrive.setTargetPosition(motorTarget);
            robot.leftBackDrive.setTargetPosition(-motorTarget);
            robot.rightBackDrive.setTargetPosition(-motorTarget);

            robot.leftFrontDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.leftBackDrive.setPower(speed);
            robot.rightBackDrive.setPower(speed);
        }
        else if (direction.equals("turn left"))
        {
            robot.leftFrontDrive.setTargetPosition(-motorTarget);
            robot.rightFrontDrive.setTargetPosition(motorTarget);
            robot.leftBackDrive.setTargetPosition(-motorTarget);
            robot.rightBackDrive.setTargetPosition(motorTarget);

            robot.leftFrontDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.leftBackDrive.setPower(-speed);
            robot.rightBackDrive.setPower(speed);
        }
        else
        {
            robot.leftFrontDrive.setTargetPosition(motorTarget);
            robot.rightFrontDrive.setTargetPosition(-motorTarget);
            robot.leftBackDrive.setTargetPosition(motorTarget);
            robot.rightBackDrive.setTargetPosition(-motorTarget);

            robot.leftFrontDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.leftBackDrive.setPower(speed);
            robot.rightBackDrive.setPower(speed);
        }

        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(linearOpMode.opModeIsActive() && robot.leftFrontDrive.isBusy() || robot.rightFrontDrive.isBusy() && robot.leftBackDrive.isBusy() && robot.rightBackDrive.isBusy()) {

            telemetry.addData("Left front target: ", robot.leftFrontDrive.getTargetPosition());
            telemetry.addData("Right front target: ", robot.rightFrontDrive.getTargetPosition());
            telemetry.addData("Left back target: ", robot.leftBackDrive.getTargetPosition());
            telemetry.addData("Right back target: ", robot.rightBackDrive.getTargetPosition());

            telemetry.addLine();

            telemetry.addData("Left front position: ", robot.leftFrontDrive.getCurrentPosition());
            telemetry.addData("Right front position: ", robot.rightFrontDrive.getCurrentPosition());
            telemetry.addData("Left back position: ", robot.leftBackDrive.getCurrentPosition());
            telemetry.addData("Right back position: ", robot.rightBackDrive.getCurrentPosition());

            telemetry.addLine();

            telemetry.addData("Left front velocity: ", robot.leftFrontDrive.getPower());
            telemetry.addData("Right front velocity: ", robot.rightFrontDrive.getPower());
            telemetry.addData("Left back velocity: ", robot.leftBackDrive.getPower());
            telemetry.addData("Right back velocity: ", robot.rightBackDrive.getPower());

            telemetry.update();
        }

        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void shutdown() {
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
    }
}