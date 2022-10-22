package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Official Auto")
public class OfficialAuto extends LinearOpMode {

    // Declare OpMode members
    final ElapsedTime runtime = new ElapsedTime();
    final EncoderDrive encoderDrive = new EncoderDrive();
    final TurnDrive turnDrive = new TurnDrive();
    final VisionTracking vision = new VisionTracking();

    public void runOpMode() {
        // Initialization
        encoderDrive.init(this, telemetry);
        turnDrive.init(this, telemetry);
        vision.init(this, telemetry);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Code to run after start
        String currentSignal = vision.operate(this, telemetry, runtime, 15);

        if (currentSignal.equals("Trojan")) {
            telemetry.addData("Status", "Trojan");
            telemetry.update();

            encoderDrive.operate(this, 0.25, 24);
            turnDrive.operate(this, 0.25, -90);
            encoderDrive.operate(this, 0.25, 24);
        }
        else if (currentSignal.equals("Gears")) {
            telemetry.addData("Status", "Gears");
            telemetry.update();

            encoderDrive.operate(this, 0.25, 24);
        }
        else if (currentSignal.equals("Hot Shot")) {
            telemetry.addData("Status", "Hot Shot");
            telemetry.update();

            encoderDrive.operate(this, 0.25, 24);
            turnDrive.operate(this, 0.25, 90);
            encoderDrive.operate(this, 0.25, 24);
        }

        vision.shutdown();
        encoderDrive.shutdown();
        turnDrive.shutdown();
    }
}