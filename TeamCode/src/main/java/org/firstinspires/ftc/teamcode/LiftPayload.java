package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftPayload {

    final HardwareDeclarations robot = new HardwareDeclarations();

    public void init(OpMode opMode, Telemetry telemetry) {
        robot.init(opMode.hardwareMap);

        telemetry.addData("Status", "Lift payload initialized");
        telemetry.update();
    }

    public void operate(Gamepad gamepad) {
        boolean leftBumper = gamepad.left_bumper;
        boolean rightBumper = gamepad.right_bumper;

        if (leftBumper) {
            robot.leftLiftMotor.setPower(-1);
            robot.rightLiftMotor.setPower(-1);
        }
        else {
            robot.leftLiftMotor.setPower(0);
            robot.rightLiftMotor.setPower(0);
        }

        if (rightBumper) {
            robot.leftLiftMotor.setPower(1);
            robot.rightLiftMotor.setPower(1);
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
