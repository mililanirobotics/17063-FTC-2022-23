package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class IntakeAndScore {
    final HardwareDeclarations robot = new HardwareDeclarations();

    final double COUNTS_PER_ROTATION = 560;
    final double DRIVE_GEAR_REDUCTION = 200/3.0;
    final double WHEEL_DIAMETER = 2;
    final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final double COUNTS_PER_INCH = (COUNTS_PER_ROTATION * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE;

    public void init (OpMode opMode, Telemetry telemetry) {
        robot.init(opMode.hardwareMap);

        telemetry.addData("Status", "Intake and Score Initialized");
        telemetry.update();
    }

    public void init (LinearOpMode linearOpMode, Telemetry telemetry) {
        robot.init(linearOpMode.hardwareMap);

        telemetry.addData("Status", "Intake and Score Initialized");
        telemetry.update();
    }

    public void operate(Gamepad gamepad) {
        double intake = gamepad.left_trigger;
        double score = gamepad.right_trigger;

        if (intake >= 0.5) {
            robot.scoringMotor.setPower(-1);
        }
        else {
            robot.scoringMotor.setPower(0);
        }

        if (score >= 0.5) {
            robot.scoringMotor.setPower(0.75);
        }
        else {
            robot.scoringMotor.setPower(0);
        }
    }
    public void operate(LinearOpMode linearOpMode, double speed, double rotation, String direction, Telemetry telemetry){
        int motorTarget = (int)(rotation * COUNTS_PER_INCH);

        robot.scoringMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction.equals("intake")){
            robot.scoringMotor.setTargetPosition(-motorTarget);
        }
        else {
            robot.scoringMotor.setTargetPosition(motorTarget);
        }

        robot.scoringMotor.setPower(speed);

        robot.scoringMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(linearOpMode.opModeIsActive() && robot.scoringMotor.isBusy()){

        }

        robot.scoringMotor.setPower(0);

        robot.scoringMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public void shutdown() {
        robot.scoringMotor.setPower(0);
    }
}
