import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="JessaSketchTankDrive", group="")
public class JessaSketchTankDrive extends LinearOpMode
{
    DcMotor lMotor, rMotor;
    float   leftY, rightY;
    Servo   neckS;
    double  servoPosition;
    double  MIN_POSITION = 0, MAX_POSITION = 1;
    double  servoRotationPerLoop = 0.0005;
    

    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {
        lMotor = hardwareMap.dcMotor.get("lMotor");
        rMotor = hardwareMap.dcMotor.get("rMotor");
        servoPosition = 0;
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

            // telemetry.addData("Mode", "running");
            // telemetry.addData("sticks", "  left=" + leftY + "  right=" + rightY);
            // telemetry.update();

            // Simplified from a lesson at https://stemrobotics.cs.pdx.edu/node/4742.html.

            //At the top of the class:
            // Declare variables for the extents of your servo rotation, as well as servo set position.
               
            
            // Inside of runOpMode()
            // You choose the name of your servo local object, but the servo you "get" must match
            //   the name of a servo in the robot configuration.
            neckS = hardwareMap.servo.get("neckS");
            
            // Inside of waitForStart(), but before opModeIsActive():
            // Make a variable to hold the set position of your servo
                 // this is a value between 0 and 1.
            
            // Inside of the "while (opModeIsActive()"" game loop:
            // Adjust the position of the servo as needed
        
            // move arm down on A button if not already at lowest position.
            if (gamepad1.dpad_left && (servoPosition > MIN_POSITION)) {
                servoPosition -= servoRotationPerLoop;
            } 
            
            // move arm up on B button if not already at the highest position.
            if (gamepad1.dpad_right && (servoPosition < MAX_POSITION)) {
                servoPosition += servoRotationPerLoop;
            } 
            
            telemetry.addData("dpad_right", gamepad1.dpad_right);
            telemetry.addData("dpad_left", gamepad1.dpad_left);
            telemetry.addData("servoPosition - Pre-Clipped", servoPosition);
            
            // Set the position of the servo
            neckS.setPosition(Range.clip(servoPosition, MIN_POSITION, MAX_POSITION));
            
            telemetry.addData("servoPosition - Clipped", servoPosition);
            telemetry.update();
            idle();
        }
    }
}


