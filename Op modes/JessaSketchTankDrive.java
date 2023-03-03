package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="JessaSketchTankDrive", group="")
public class JessaSketchTankDrive extends LinearOpMode
{
    DcMotor lMotor, rMotor;
    float   leftY, rightY;

    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {
        lMotor = hardwareMap.dcMotor.get("lMotor");
        rMotor = hardwareMap.dcMotor.get("rMotor");
        
        lMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();
        lMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // wait for start button.

        waitForStart();

        while (opModeIsActive())
        {
            lMotor.setPower(gamepad1.left_stick_y);
            rMotor.setPower(gamepad1.right_stick_y);

            telemetry.addData("Mode", "running");
            telemetry.addData("sticks", "  left=" + leftY + "  right=" + rightY);
            telemetry.update();

            idle();
        }
    }
}