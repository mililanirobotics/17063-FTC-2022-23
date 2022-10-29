package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TimedDrive {

    final HardwareDeclarations robot = new HardwareDeclarations();

    public void init(LinearOpMode linearOpMode, Telemetry telemetry) {
        robot.init(linearOpMode.hardwareMap);

        telemetry.addData("Status", "Timed Drive Initialized");
        telemetry.update();
    }

    public void operate(LinearOpMode linearOpMode, ElapsedTime elapsedTime, double speed, String signalImage) {

        if (signalImage.equals("Trojan")){

            elapsedTime.reset();

            while (elapsedTime.seconds() < 1.5) {
            }

            robot.leftFrontDrive.setPower(-speed);
            robot.rightFrontDrive.setPower(speed);
            robot.leftBackDrive.setPower(speed);
            robot.rightBackDrive.setPower(-speed);

            while (elapsedTime.seconds() < 2.25) {
            }

            robot.leftFrontDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.leftBackDrive.setPower(speed);
            robot.rightBackDrive.setPower(speed);

            while (elapsedTime.seconds() < 3.25) {

            }

            robot.leftFrontDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);
            robot.leftBackDrive.setPower(0);
            robot.rightBackDrive.setPower(0);
        }

        else if (signalImage.equals("Gears")) {

            elapsedTime.reset();

            while (elapsedTime.seconds() < 1) {
            }

            robot.leftFrontDrive.setPower(-speed);
            robot.rightFrontDrive.setPower(-speed);
            robot.leftBackDrive.setPower(-speed);
            robot.rightBackDrive.setPower(-speed);

            while (elapsedTime.seconds() < 2.5) {
            }

            robot.leftFrontDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);
            robot.leftBackDrive.setPower(0);
            robot.rightBackDrive.setPower(0);

        }
        else if (signalImage.equals("Hot Shot")){

            elapsedTime.reset();

            while (elapsedTime.seconds() < 1) {
            }

            robot.leftFrontDrive.setPower(speed);
            robot.rightFrontDrive.setPower(0);
            robot.leftBackDrive.setPower(0);
            robot.rightBackDrive.setPower(speed);

            while (elapsedTime.seconds() < 2.5) {
            }

            robot.leftFrontDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);
            robot.leftBackDrive.setPower(0);
            robot.rightBackDrive.setPower(0);
        }
    }
}
