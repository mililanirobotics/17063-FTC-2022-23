package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftPayload {

    final HardwareDeclarations robot = new HardwareDeclarations();

    public void init(OpMode opMode, Telemetry telemetry) {
        robot.init(opMode.hardwareMap);

        telemetry.addData("Status", "Lift payload initialized");
        telemetry.update();
    }

    public void operate(OpMode opMode, Gamepad gamepad, double maxCounts, double minCounts) {
        boolean leftBumper = gamepad.left_bumper;
        boolean rightBumper = gamepad.right_bumper;

        // Check this if lift dies
        if (leftBumper && robot.leftLiftMotor.getCurrentPosition() < maxCounts && robot.rightLiftMotor.getCurrentPosition() < maxCounts) {
            robot.leftLiftMotor.setPower(0.5);
            robot.rightLiftMotor.setPower(0.5);
        }
        else {
            robot.leftLiftMotor.setPower(0);
            robot.rightLiftMotor.setPower(0);
        }

        if (rightBumper && robot.leftLiftMotor.getCurrentPosition() > minCounts && robot.rightLiftMotor.getCurrentPosition() > minCounts) {
            robot.leftLiftMotor.setPower(-0.5);
            robot.rightLiftMotor.setPower(-0.5);
        }
        else {
            robot.leftLiftMotor.setPower(0);
            robot.rightLiftMotor.setPower(0);
        }
    }

    public void shutdown() {
        robot.leftLiftMotor.setPower(0);
        robot.rightLiftMotor.setPower(0);
    }
}
