package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Official Auto")
public class OfficialAuto extends LinearOpMode {

    // Declare OpMode members
    final ElapsedTime runtime = new ElapsedTime();
    final HardwareDeclarations robot = new HardwareDeclarations();
    final EncoderDrive encoderDrive = new EncoderDrive();
    final VisionTracking vision = new VisionTracking();

    public void runOpMode() {
        // Initialization
        robot.init(this.hardwareMap);
        encoderDrive.init(this, telemetry);
        vision.init(this, telemetry);

        // Port Check
        telemetry.addData("Left front drive port: ", robot.leftFrontDrive.getPortNumber());
        telemetry.addData("Right front drive port: ", robot.rightFrontDrive.getPortNumber());
        telemetry.addData("Left back drive port: ", robot.leftBackDrive.getPortNumber());
        telemetry.addData("Right back drive port: ", robot.rightBackDrive.getPortNumber());

        telemetry.addLine();

        telemetry.addData("Left lift motor port: ", robot.leftLiftMotor.getPortNumber());
        telemetry.addData("Right lift motor port: ", robot.rightLiftMotor.getPortNumber());
        telemetry.addData("Scoring motor port: ", robot.scoringMotor.getPortNumber());

        telemetry.addLine();
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Code to run after start
        String currentSignal = vision.operate(this, telemetry, runtime, 15);

        if (currentSignal.equals("Trojan")) {
            telemetry.addData("Status", "Trojan");
            telemetry.update();

            encoderDrive.operate(this, 0.25, 7, "left", telemetry);
            encoderDrive.operate(this, 0.25, 7, "forward", telemetry);
        }
        else if (currentSignal.equals("Gears")) {
            telemetry.addData("Status", "Gears");
            telemetry.update();

            encoderDrive.operate(this, 0.25, 7, "forward", telemetry);
        }
        else if (currentSignal.equals("Hot Shot")) {
            telemetry.addData("Status", "Hot Shot");
            telemetry.update();

            encoderDrive.operate(this, 0.25, 7, "right", telemetry);
            encoderDrive.operate(this, 0.25, 7, "forward", telemetry);
        }
        else {
            telemetry.addData("Status", "No Image");
            telemetry.update();

            encoderDrive.operate(this, 0.25, 7, "left", telemetry);
        }

        vision.shutdown();
        encoderDrive.shutdown();
    }
}