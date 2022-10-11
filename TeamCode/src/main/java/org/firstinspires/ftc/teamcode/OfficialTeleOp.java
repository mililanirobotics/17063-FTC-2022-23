package org.firstinspires.ftc.teamcode;

// Imports
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Official TeleOp")

public class OfficialTeleOp extends OpMode
{
    // Declare OpMode members.
    private MecanumDrive mecanumDrive = new MecanumDrive();
    private HardwareDeclarations robot = new HardwareDeclarations();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {
        mecanumDrive.init(this, telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {
        mecanumDrive.operate(this, gamepad1);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop() {
        mecanumDrive.shutdown();
    }

}