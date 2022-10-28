package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Official TeleOp")

public class OfficialTeleOp extends OpMode
{
    // Declare OpMode members.
    final MecanumDrive mecanumDrive = new MecanumDrive();
    final LiftPayload liftPayload = new LiftPayload();
    final IntakeAndScore intakeAndScore = new IntakeAndScore();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {
        mecanumDrive.init(this, telemetry);
        liftPayload.init(this, telemetry);
        intakeAndScore.init(this, telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {
        mecanumDrive.fieldCentricOperate(gamepad1);
        liftPayload.operate(gamepad2);
        intakeAndScore.operate(gamepad2);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop() {
        mecanumDrive.shutdown();
        liftPayload.shutdown();
        intakeAndScore.shutdown();
    }

}