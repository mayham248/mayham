package org.firstinspires.ftc.teamcode.mayham;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "auto blue")
public class AutoBlue extends LinearOpMode {
    private ElapsedTime runtime= new ElapsedTime();
    HardwareDemo robot = HardwareDemo.getInstance();

    private int timer = 5;

    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("status","Hello Drivers!");
        telemetry.update();
        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //this is where the auto blue code will go
        waitForStart();

        robot.clawServo.setPosition(0.85);
        moveArm(700);
        encoderMove(22, 0.4);
        robot.rampSupport.setPosition(0.1);
        sleep(1000);
        encoderMove(24,0.4);
         robot.rampSupport.setPosition(0.9);
        encoderMove(40,0.4);
        turning(-1700,0.4);
        encoderMove(90,0.4);
        robot.clawServo.setPosition(0.95);
        encoderMove(-90,0.4);
        turning(-1700,0.4);
        encoderMove(50,0.4);
        robot.rampSupport.setPosition(0.1);
        sleep(1000);
        encoderMove(24,0.4);
        robot.rampSupport.setPosition(0.9);
        moveArm(0);



    }



    public void encoderMove(double distance,double speed){
        double wheelCircumference = 3.5 * Math.PI;
        double wheelMotor = 537.7;
        double ticks = (distance * (wheelMotor / wheelCircumference));

        robot.setPower(0, 0);

        robot.left.setTargetPosition((int) Math.round(ticks));
        robot.right.setTargetPosition((int) Math.round(ticks));

        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(speed, speed);

        while (opModeIsActive() && (robot.left.isBusy())){

        }

        robot.setPower(0, 0);

        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turning(int ticks, double speed){

        robot.setPower(0, 0);

        robot.left.setTargetPosition(ticks);
        robot.right.setTargetPosition(-ticks);

        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(speed, -speed);

        while (opModeIsActive() && (robot.left.isBusy())){

        }

        robot.setPower(0, 0);

        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void moveArm(int position) {
        robot.arm.setTargetPosition(position);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.arm.setPower(1);
        while (robot.arm.isBusy()) {
        }
        robot.arm.setPower(0);
    }
    public void balancing(){
        double pitch = robot.imu.getRobotYawPitchRollAngles().getPitch();
        if(pitch > 0){
            robot.setPower(0.2,0.2);
        } else if (pitch < 0){
            robot.setPower(-0.2,-0.2);

        }

    }

}
