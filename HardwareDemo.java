package org.firstinspires.ftc.teamcode.mayham;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class HardwareDemo {



  public DcMotor right;
  public DcMotor left;

  public DcMotor arm;
  public Servo clawServo;
  public Servo rampSupport;

  public static double maxSpeed = 1;
  private static HardwareDemo myInstance = null;
  public static HardwareDemo getInstance()  {
    if (myInstance == null) {
      myInstance = new HardwareDemo();
    }
    return myInstance;
  }
  public void init(HardwareMap hwMap) {
    right = hwMap.get(DcMotor.class,"right");
    right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    right.setDirection(DcMotorSimple.Direction.REVERSE);
    right.setPower(0);

    left = hwMap.get(DcMotor.class,"left");
    left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    left.setDirection(DcMotor.Direction.FORWARD);
    left.setPower(0);

    arm = hwMap.get(DcMotor.class, "arm");
    arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    arm.setDirection(DcMotor.Direction.FORWARD);arm.setPower(0);

    //initialize servo
    clawServo = hwMap.get(Servo.class, "clawServo");

    rampSupport = hwMap.get(Servo.class, "rampSupport");
  }

public void setPower(double motor1, double motor2)  {
    right.setPower(Range.clip(motor1, -maxSpeed, maxSpeed));
    left.setPower(Range.clip(motor2, -maxSpeed, maxSpeed));

}


}
