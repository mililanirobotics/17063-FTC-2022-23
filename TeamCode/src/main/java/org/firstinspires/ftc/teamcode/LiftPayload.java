package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftPayload {

    private HardwareDeclarations robot = new HardwareDeclarations();

    public void init(OpMode opMode, Telemetry telemetry) {
        robot.init(opMode.hardwareMap);

        telemetry.addData("Status", "Lift payload initialized");
        telemetry.update();
    }

    public void operate(OpMode opMode, Gamepad gamepad) {
        boolean leftBumper = gamepad.left_bumper;
        boolean rightBumper = gamepad.right_bumper;

        if (leftBumper) {
            robot.leftLiftMotor.setPower(0.5);
            robot.rightLiftMotor.setPower(0.5);
        }

        if (rightBumper) {
            robot.leftLiftMotor.setPower(-0.5);
            robot.rightLiftMotor.setPower(-0.5);
        }
    }

    public void shutdown() {
        robot.leftLiftMotor.setPower(0);
        robot.rightLiftMotor.setPower(0);
    }
}
