package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftPayload {

    final HardwareDeclarations robot = new HardwareDeclarations();

    final double COUNTS_PER_ROTATION = 28;
    final double DRIVE_GEAR_REDUCTION = 80;
    final double COUNTS_PER_INCH = COUNTS_PER_ROTATION * DRIVE_GEAR_REDUCTION;

    public void init(OpMode opMode, Telemetry telemetry) {
        robot.init(opMode.hardwareMap);

        telemetry.addData("Status", "Lift payload initialized");
        telemetry.update();
    }

    public void init(LinearOpMode linearOpMode, Telemetry telemetry){
        robot.init(linearOpMode.hardwareMap);

        telemetry.addData("Status", "Lift payload initialized");
        telemetry.update();
    }

    public void operate(Gamepad gamepad) {
        boolean leftBumper = gamepad.left_bumper;
        boolean rightBumper = gamepad.right_bumper;

        if (leftBumper) {
            robot.leftLiftMotor.setPower(1);
            robot.rightLiftMotor.setPower(1);
        }
        else {
            robot.leftLiftMotor.setPower(0);
            robot.rightLiftMotor.setPower(0);
        }

        if (rightBumper) {
            robot.leftLiftMotor.setPower(-0.75);
            robot.rightLiftMotor.setPower(-0.75);
        }
        else {
            robot.leftLiftMotor.setPower(0);
            robot.rightLiftMotor.setPower(0);
        }
    }

    public void operate(LinearOpMode linearOpMode, double speed, double distance, String direction, Telemetry telemetry){
        int motorTarget = (int)(distance * COUNTS_PER_INCH);

        robot.leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction.equals("up")){
            robot.leftLiftMotor.setTargetPosition(motorTarget);
            robot.rightLiftMotor.setTargetPosition(motorTarget);
        }
        else {
            robot.leftLiftMotor.setTargetPosition(-motorTarget);
            robot.rightLiftMotor.setTargetPosition(-motorTarget);
        }

        robot.leftLiftMotor.setPower(speed);
        robot.rightLiftMotor.setPower(speed);

        robot.leftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (linearOpMode.opModeIsActive() && robot.leftLiftMotor.isBusy() && robot.rightLiftMotor.isBusy()){

        }

        robot.leftLiftMotor.setPower(0);
        robot.rightLiftMotor.setPower(0);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void shutdown() {
        robot.leftLiftMotor.setPower(0);
        robot.rightLiftMotor.setPower(0);
    }
}
