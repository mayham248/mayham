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


            if (gamepad2.left_bumper) {
                robot.rampSupport.setPosition(0);
            }

            if ((gamepad2.left_trigger > .3) && !pressingLT)
            {
                robot.clawServo.setPosition(0.7);
            pressingLT = true;
            } else if (!(gamepad2.left_trigger > 0.3)){
              pressingLT = false;
            }
            if ((gamepad2.right_trigger > .3) && !pressingRT)
            {
                robot.clawServo.setPosition(0.75);
                pressingRT = true;
            } else if (!(gamepad2.right_trigger > 0.3)){
                pressingRT = false;
            }
            //robot arm moves up
            if (gamepad2.y) {
                moveArm(0);
            }
            //robot arm moves down
            if (gamepad2.a) {
                moveArm(650);

            }
            telemetry.addData("arm position", robot.arm.getCurrentPosition());
            telemetry.update();

//        if (!robot.arm.isBusy()){
//            robot.arm.setPower(0);
//            robot.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        }

        }
    }

    public void moveArm(int position) {
        robot.arm.setTargetPosition(position);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.arm.setPower(1);
    }
}
