package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MecanumDrive {

    // Declare OpMode members.
    final HardwareDeclarations robot = new HardwareDeclarations();
    final BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    static boolean slowModeOn = false;

    public void init(OpMode opMode, Telemetry telemetry) {
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

        robot.init(opMode.hardwareMap);
        robot.imu.initialize(parameters);

        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Mecanum Drive Initializes");
        telemetry.update();
    }

    public void operate(Gamepad gamepad) {
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = gamepad.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        if (gamepad.left_bumper && !slowModeOn) {
            slowModeOn = true;
        }
        else if (gamepad.left_bumper && slowModeOn){
            slowModeOn = false;
        }

        if (slowModeOn) {
            robot.leftFrontDrive.setPower((y + x + rx) / denominator * 0.5);
            robot.rightFrontDrive.setPower((y - x - rx) / denominator * 0.5);
            robot.leftBackDrive.setPower((y - x + rx) / denominator * 0.5);
            robot.rightBackDrive.setPower((y + x - rx) / denominator * 0.5);
        }
        else {
            robot.leftFrontDrive.setPower((y + x + rx) / denominator);
            robot.rightFrontDrive.setPower((y - x - rx) / denominator);
            robot.leftBackDrive.setPower((y - x + rx) / denominator);
            robot.rightBackDrive.setPower((y + x - rx) / denominator);
        }
    }

    public void shutdown() {
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
    }
}
