package org.firstinspires.ftc.teamcode;

// Imports
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

public class EncoderDrive {

    HardwareDeclarations robot = new HardwareDeclarations();

    final double PULSES_PER_ROTATION = 573.7;
    final double COUNTS_PER_ROTATION = PULSES_PER_ROTATION * 4;
    final double WHEEL_DIAMETER = 3.77953;
    final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final double COUNTS_PER_INCH = COUNTS_PER_ROTATION / WHEEL_CIRCUMFERENCE;

    public void init() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void operate(double speed, double distance) {
        double motorPower = speed;
        int motorTarget = (int)(distance * COUNTS_PER_INCH);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftFrontDrive.setTargetPosition(motorTarget);
        robot.rightFrontDrive.setTargetPosition(motorTarget);
        robot.leftBackDrive.setTargetPosition(motorTarget);
        robot.rightBackDrive.setTargetPosition(motorTarget);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.leftFrontDrive.setPower(motorPower);
        robot.rightFrontDrive.setPower(motorPower);
        robot.leftBackDrive.setPower(motorPower);
        robot.rightBackDrive.setPower(motorPower);
    }

    public void shutdown() {

    }
}