package org.firstinspires.ftc.teamcode.mayham;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "servo Tester")
public class ServoTester extends LinearOpMode {
    HardwareDemo robot = HardwareDemo.getInstance();
    public void runOpMode() {
        robot.init(hardwareMap);
        boolean pressingDpadUp = false;
        boolean pressingDpadDown = false;



        waitForStart();
        double servoPosition = 1;

        while (opModeIsActive()) {
            if (gamepad2.dpad_up && !pressingDpadUp) {
                pressingDpadUp = true;
                servoPosition += 0.05;
            }
            else if (!gamepad2.dpad_up && pressingDpadUp){
                pressingDpadUp =  false;
            }
            if (gamepad2.dpad_down && !pressingDpadDown) {
                pressingDpadDown = true;
                servoPosition -= 0.05;
            }
            else if (!gamepad2.dpad_down && pressingDpadDown){
                pressingDpadDown = false;
            }


            robot.rampSupport.setPosition(servoPosition);
            telemetry.addData("servo position", robot.rampSupport.getPosition());
            telemetry.update();
        }
    }
}
