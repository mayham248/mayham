package org.firstinspires.ftc.teamcode.mayham;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "Demo TeleOP")
public class TeleOPDemo extends LinearOpMode {
    HardwareDemo robot = HardwareDemo.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addData("Status", "Hello Drivers!");
        telemetry.update();

        boolean pressingRT = false;
        boolean pressingLT = false;
        boolean pressingA = false;
        boolean pressingY = false;
        boolean pressingDUp = false;
        boolean pressingDDown = false;

        waitForStart();
        while (opModeIsActive()) {
            double movement = -gamepad1.left_stick_y;
            double turning = -gamepad1.right_stick_x;

            double left = movement + turning;
            double right = movement - turning;

            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }
            robot.left.setPower(left);
            robot.right.setPower(right);

            //moves the ramp support up/down
            if (gamepad1.dpad_up && !pressingDUp) {
               robot.rampSupport.setPosition(0.1);
               pressingDUp = true;
            } else if (!gamepad1.dpad_up){
                pressingDUp = false;
            }
            if (gamepad1.dpad_down && !pressingDDown) {
                robot.rampSupport.setPosition(0.9);
                pressingDDown = true;
            } else if (!gamepad1.dpad_down){
                pressingDDown = false;
            }

            if ((gamepad2.left_trigger > .3) && !pressingLT)
            {
                robot.clawServo.setPosition(0.95);
                pressingLT = true;
            } else if (!(gamepad2.left_trigger > 0.3)){
              pressingLT = false;
            }
            if ((gamepad2.right_trigger > .3) && !pressingRT)
            {
                robot.clawServo.setPosition(0.85);
                pressingRT = true;
            } else if (!(gamepad2.right_trigger > 0.3)){
                pressingRT = false;
            }
            //robot arm moves up
            if (gamepad2.y) {
                robot.moveArm(700);
            }
            //robot arm moves down
            if (gamepad2.a) {
              robot.moveArm(0);

            }
            telemetry.addData("arm position", robot.arm.getCurrentPosition());
            telemetry.update();

            if (!robot.arm.isBusy()){
                robot.arm.setPower(0);
                robot.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

        }
    }


}
