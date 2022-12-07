package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

public class VisionTracking {

    // Declare OpMode members
    final HardwareDeclarations robot = new HardwareDeclarations();
    private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/Custom Model v3.tflite";

    private static final String[] LABELS = {
            "Gears",
            "Hot Shot",
            "Trojan"
    };

    private static final String VUFORIA_KEY = "AfCDK9H/////AAABmV1RcmG0/kG1og7GzNyI0ZAVv5gzjnQ4nLvp4MOsiXlbaf/bVI2JBbkj+Bvb8ur3KEl6D4mlcpwEpDMZ51nkqMqqG1VE53EjiDsUUBVZ36OiyfOSjoeoB1py8iT1CYO1EydEKeoOy/SSoF743egWLXTpyFi4Mfyg6DDW06lgfWNsoWJ904IL+6CJ3TMObEuE5Gtwqu1CccRpRCdaE0kCuGDsdd4o2NPZLEqdpAJcitJbyLeC//Qqh8tfK35W6kK5wG7JU46kjfrtPSsICZfSwvA+bmCIcmir4XKKLM/8dkx39bJNF+6FdBeLkIfaeDfrVV8+2RxzeBB+f5DpycmxB0LqzVTvOu+H9VDOWSBVr+l4";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfObjectDetector;

    public void init(LinearOpMode linearOpMode, Telemetry telemetry) {
        initVuforia(linearOpMode);
        initTfObjectDetector(linearOpMode);

        if (tfObjectDetector != null) {
            tfObjectDetector.activate();
        }

        telemetry.addData("Status", "Vision tracking initialized");
        telemetry.update();
    }

    public String operate(LinearOpMode linearOpMode, Telemetry telemetry, ElapsedTime elapsedTime, int timeoutSec) {
        String signalImage = "No image";

        while (linearOpMode.opModeIsActive() && signalImage.equals("No image") && elapsedTime.seconds() < timeoutSec) {
            if (tfObjectDetector != null) {
                List<Recognition> updatedRecognitions = tfObjectDetector.getUpdatedRecognitions();

                if (updatedRecognitions != null) {
                    for (Recognition recognition : updatedRecognitions) {
                        signalImage = recognition.getLabel();

                        telemetry.addData("Image Recognized: ", signalImage);
                        telemetry.update();
                    }
                }
                else {
                    signalImage = "No image";
                    telemetry.addData("Status", "No image recognized");
                    telemetry.update();
                }
            }

        }

        return signalImage;
    }

    public void shutdown() {
    }

    private void initVuforia(LinearOpMode linearOpMode) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.camera = robot.camera;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfObjectDetector(LinearOpMode linearOpMode) {
        int tfodMonitorViewId = linearOpMode.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", linearOpMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters();
        tfodParameters.minResultConfidence = 0.50f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfObjectDetector = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        tfObjectDetector.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }
}
