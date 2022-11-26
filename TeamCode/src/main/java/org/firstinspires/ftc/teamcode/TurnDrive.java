package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class TurnDrive {

    final HardwareDeclarations robot = new HardwareDeclarations();
    final BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();


    public void init(LinearOpMode linearOpMode, Telemetry telemetry) {
        robot.init(linearOpMode.hardwareMap);
        robot.imu.initialize(parameters);

        telemetry.addData("Status", "Turn Drive Initialized");
        telemetry.update();
    }

    public double getHeading() {
        Orientation angle = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angle.firstAngle;
    }

    public void operate(LinearOpMode linearOpMode, double speed, double rotation) {

        while(rotation != getHeading()) {
            if(rotation > 0) {
                robot.leftFrontDrive.setPower(-speed);
                robot.leftBackDrive.setPower(-speed);
                robot.rightFrontDrive.setPower(-speed);
                robot.rightBackDrive.setPower(-speed);
            }
            if (rotation < 0) {
                robot.leftFrontDrive.setPower(speed);
                robot.leftBackDrive.setPower(speed);
                robot.rightFrontDrive.setPower(speed);
                robot.rightBackDrive.setPower(speed);
            }
        }
    }

    public void shutdown() {
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
    }
}
