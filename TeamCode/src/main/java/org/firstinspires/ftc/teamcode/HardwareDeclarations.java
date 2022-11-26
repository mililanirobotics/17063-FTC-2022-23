package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;

public class HardwareDeclarations {

    // Prefix any hardware functions with "robot." to access this class.
    public DcMotorEx leftFrontDrive = null;
    public DcMotorEx rightFrontDrive = null;
    public DcMotorEx leftBackDrive = null;
    public DcMotorEx rightBackDrive = null;
    public DcMotor leftLiftMotor = null;
    public DcMotor rightLiftMotor = null;
    public DcMotor scoringMotor = null;

    public BNO055IMU imu = null;
    public Camera camera;

    // Initialize hardware
    HardwareMap hwMap = null;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        leftFrontDrive = hwMap.get(DcMotorEx.class, "leftFrontDrive");
        rightFrontDrive = hwMap.get(DcMotorEx.class, "rightFrontDrive");
        leftBackDrive = hwMap.get(DcMotorEx.class, "leftBackDrive");
        rightBackDrive = hwMap.get(DcMotorEx.class, "rightBackDrive");
        leftLiftMotor = hwMap.get(DcMotor.class, "leftLiftMotor");
        rightLiftMotor = hwMap.get(DcMotor.class, "rightLiftMotor");
        scoringMotor = hwMap.get(DcMotor.class, "scoringMotor");

        imu = hwMap.get(BNO055IMU.class, "imu");

        leftFrontDrive.setDirection(DcMotorEx.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotorEx.Direction.FORWARD);
        leftLiftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightLiftMotor.setDirection(DcMotor.Direction.REVERSE);
        scoringMotor.setDirection(DcMotor.Direction.FORWARD);

        leftFrontDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        scoringMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
        leftLiftMotor.setPower(0);
        rightLiftMotor.setPower(0);
        scoringMotor.setPower(0);

        leftLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        scoringMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
