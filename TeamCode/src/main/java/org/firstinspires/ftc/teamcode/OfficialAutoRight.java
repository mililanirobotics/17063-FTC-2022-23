package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Official Auto Right")
public class OfficialAutoRight extends LinearOpMode {

    // Declare OpMode members
    final ElapsedTime runtime = new ElapsedTime();
    final HardwareDeclarations robot = new HardwareDeclarations();
    final EncoderDrive encoderDrive = new EncoderDrive();
    final IntakeAndScore intakeAndScore = new IntakeAndScore();
    final LiftPayload liftPayload = new LiftPayload();
    final VisionTracking vision = new VisionTracking();

    public void runOpMode() {
        // Initialization
        robot.init(this.hardwareMap);
        encoderDrive.init(this, telemetry);
        intakeAndScore.init(this, telemetry);
        liftPayload.init(this, telemetry);
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
        String currentSignal = vision.operate(this, telemetry, runtime, 10);

        // Arriving to medium junction
        encoderDrive.operate(this, 0.4, 7, "left", telemetry);
        encoderDrive.operate(this, 0.4, 12.1, "forward", telemetry);
        encoderDrive.operate(this, 0.4, 3.6, "right", telemetry);

        // Scoring cone
        liftPayload.operate(this, 0.8, 0.80, "up", telemetry);
        encoderDrive.operate(this, 0.25, 0.7, "backward", telemetry);
        liftPayload.operate(this, 0.8, 0.2, "down", telemetry);
        intakeAndScore.operate(this, 0.50, 0.1, "score", telemetry);
        encoderDrive.operate(this, 0.25, 1.2, "forward", telemetry);

        if (currentSignal.equals("Trojan")) {
            encoderDrive.operate(this, 0.4, 3, "left", telemetry);
        }
        else if (currentSignal.equals("Gears")) {
            encoderDrive.operate(this, 0.4, 3.2, "right", telemetry);
        }
        else if (currentSignal.equals("Hot Shot")) {
            encoderDrive.operate(this, 0.25, 9.8, "right", telemetry);
        }
        else {
            encoderDrive.operate(this, 0.25, 4, "left", telemetry);
            encoderDrive.operate(this, 0.25, 12.3, "backward", telemetry);
        }

        vision.shutdown();
        intakeAndScore.shutdown();
        liftPayload.shutdown();
        encoderDrive.shutdown();
    }
}