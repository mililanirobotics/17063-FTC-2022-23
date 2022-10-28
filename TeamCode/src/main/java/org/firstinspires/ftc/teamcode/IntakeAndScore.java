package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class IntakeAndScore {
    final HardwareDeclarations robot = new HardwareDeclarations();

    public void init (OpMode opMode, Telemetry telemetry) {
        robot.init(opMode.hardwareMap);

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
            robot.scoringMotor.setPower(1);
        }
        else {
            robot.scoringMotor.setPower(0);
        }
    }
    public void shutdown() {
        robot.scoringMotor.setPower(0);
    }
}
