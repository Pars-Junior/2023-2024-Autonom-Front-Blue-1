package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name="Basic: Linear OpMode", group="Linear OpMode")

public class Blue1_Front extends LinearOpMode {

 // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor armmotor;
    private DcMotor acimotor;
    private Servo solservo;
    private Servo sagservo;
    
    private int leftPos;
    private int rightPos;
    private int armPos;
    private int aciPos;
    private int lservoPos;
    private int rservoPos;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        armmotor = hardwareMap.get(DcMotor.class, "arm_motor");
        acimotor = hardwareMap.get(DcMotor.class, "aci_motor");
        solservo = hardwareMap.get(Servo.class, "left_servo");
        sagservo = hardwareMap.get(Servo.class, "right_servo");
        
        
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        acimotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        
        leftPos = 0;
        rightPos = 0;
        armPos = 0;
        aciPos = 0;
        
        waitForStart();
        
        // Çizgiye Bırakma
        
        solservo.setPosition(-0.64);
        sagservo.setPosition(0.32);
        sleep(1500);
        
        drive(-650,-650,0,0,0.6);
        drive(360,-360,0,0,0.6);
        
        sleep(1000);
        drive(250,250,0,0,0.4);
        
        drive(0,0,0,200,1);
        sleep(1500);
        
        
        //Kolu indirme
        drive(0,0,0,-770,1);
        sleep(4000);
        drive(-200,200,0,0,0.6);
        sleep(500);
        sagservo.setPosition(0);
        
        drive(0,0,0,150,1);
        sleep(600);
        
        drive(320,-320,0,0,0.6);
        sleep(2000);
        drive(950,950,0,0,0.4);
        sleep(500);
        drive(0,0,0,1400,1);
        sleep(5000);
        solservo.setPosition(0.25);
        drive(0,0,0,-1400,1);
        sleep(5000);

    }
    
    

    private void drive(int leftTarget, int rightTarget,int armTarget, double aciTarget,  double speed){
        leftPos += leftTarget;
        rightPos += rightTarget;
        armPos += armTarget;
        aciPos += aciTarget;
        
        leftDrive.setTargetPosition(leftPos);
        rightDrive.setTargetPosition(rightPos);
        armmotor.setTargetPosition(armPos);
        acimotor.setTargetPosition(aciPos);
        
        
        
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        acimotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        
        leftDrive.setPower(speed);
        rightDrive.setPower(speed);
        armmotor.setPower(0.5);
        acimotor.setPower(0.8);
        
        
        
        while(opModeIsActive() && leftDrive.isBusy() && rightDrive.isBusy()){
            idle();
        }
    
    }
}



