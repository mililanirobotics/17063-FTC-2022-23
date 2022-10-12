package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrive {

    // Declare OpMode members.
    private HardwareDeclarations robot = new HardwareDeclarations();

    public void init(OpMode opMode, Telemetry telemetry) {
        robot.init(opMode.hardwareMap);

        telemetry.addData("Status", "Initializes");
        telemetry.update();
    }

    public void operate(OpMode opMode, Gamepad gamepad) {
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = gamepad.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        // Normal Drive
        robot.leftFrontDrive.setPower((y+x+rx) / denominator);
        robot.rightFrontDrive.setPower((y-x-rx) / denominator);
        robot.leftBackDrive.setPower((y-x+rx) / denominator);
        robot.rightBackDrive.setPower((y+x-rx) / denominator);

        // Slow-mode
        if (gamepad.left_bumper == true) {
            robot.leftFrontDrive.setPower((y+x+rx) / denominator * 0.5);
            robot.rightFrontDrive.setPower((y-x-rx) / denominator * 0.5);
            robot.leftBackDrive.setPower((y-x+rx) / denominator * 0.5);
            robot.rightBackDrive.setPower((y+x-rx) / denominator * 0.5);
        }
    }

    public void shutdown() {
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
    }
}
