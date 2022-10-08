package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class MecanumDrive {

    // Declare OpMode members.
    HardwareDeclarations robot = new HardwareDeclarations();

    public void init(OpMode opMode) {
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initializes");
        telemetry.update();
    }

    public void operate(OpMode opMode, Gamepad gamepad) {
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = gamepad.right_stick_x;

        robot.leftFrontDrive.setPower(y+x+rx);
        robot.rightFrontDrive.setPower(y-x-rx);
        robot.leftBackDrive.setPower(y-x+rx);
        robot.rightBackDrive.setPower(y+x-rx);

        if (gamepad.left_bumper == true){
            robot.leftFrontDrive.setPower((y+x+rx)/2);
            robot.rightFrontDrive.setPower((y-x-rx)/2);
            robot.leftBackDrive.setPower((y-x+rx)/2);
            robot.rightBackDrive.setPower((y+x-rx)/2);
        }
    }

    public void shutdown(){
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
    }
}
