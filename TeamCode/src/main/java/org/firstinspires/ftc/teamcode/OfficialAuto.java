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
    private ElapsedTime runtime = new ElapsedTime();
    private EncoderDrive encoderDrive = new EncoderDrive();
    private TurnDrive turnDrive = new TurnDrive();
    private VisionTracking vision = new VisionTracking();

    public void runOpMode() {
        // Initialization
        encoderDrive.init(this, telemetry);
        turnDrive.init(this, telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Code to run after start
        String currentSignal = vision.operate(this, telemetry);

        switch (currentSignal) {
            case "1" :
                encoderDrive.operate(this, 0.25, 24);
                turnDrive.operate(this, 0.25, -90);
                encoderDrive.operate(this, 0.25, 24);
                break;
            case "2" :
                encoderDrive.operate(this, 0.25, 24);
                break;
            case "3" :
                encoderDrive.operate(this, 0.25, 24);
                turnDrive.operate(this, 0.25, 90);
                encoderDrive.operate(this, 0.25, 24);
                break;
        }

        vision.shutdown();
        encoderDrive.shutdown();
        turnDrive.shutdown();
    }
}