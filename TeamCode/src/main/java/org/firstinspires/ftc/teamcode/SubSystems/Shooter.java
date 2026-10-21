package org.firstinspires.ftc.teamcode.SubSystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Config

public class Shooter {
    /* instance variables */
    DcMotorEx shoot;
    double maximumVelocity;
    ElapsedTime timer = new ElapsedTime();

    //PID Variables
    double Kp = 0;
    double Ki = 0;
    double Kd = 0;
    double Kf = 0;
    double integralSum = 0;
    double lastError = 0;
    private boolean firstTimeInitialization = true;

    /* Constructor */
    public Shooter (HardwareMap hwMap){
    shoot = hwMap.get(DcMotorEx.class, "shoot");
    /*
    shooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    shooter.setDirection(DcMotorSimple.Direction.REVERSE);
    */
    }
        public void findMaximumVelocity(){

            double currentVelocity = shoot.getVelocity();
            shoot.setPower(1);
            if(currentVelocity > maximumVelocity){
                maximumVelocity = currentVelocity;
            }

        }
        public void shooterOn(double targetVel){
        shoot.setPower(pidControl(targetVel));
        }
       /* public double pidControl (double targetVel){

                double error = 0;
                double derivative = 0;
                double output = 0;
                // obtain the encoder position
                reference = shoot.getVelocity();

                // calculate the error
                error = reference - targetVel;

                // rate of change of the error
                derivative = (error - lastError) / timer.seconds();

                // sum of all error over time
                integralSum = integralSum + (error * timer.seconds());

                output = (Kp * error) + (Ki * integralSum) + (Kd * derivative);

                lastError = error;
                timer.reset();
                return output;




        }


        */
       public void shooterOff(){
           lastError = 0;
           firstTimeInitialization = true;

       }
       public double pidControl(double targetVel) {
           double currentVel = shoot.getVelocity();
           //if first time method is called, do not use pid control
           if (firstTimeInitialization) {
               firstTimeInitialization = false;
               return Kf * targetVel;
           }

           double deltaTime = timer.seconds();
           timer.reset();

           double error = targetVel - currentVel; //reference = input, state = output.
           integralSum += error * deltaTime;
           double derivative = (error - lastError) / deltaTime;

           lastError = error;


           double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki) + (targetVel * Kf);




           return output; //returns optimal speed for motor




       }




    //method to change instance variables
    public void changePIDCoefficients(double Kp, double Ki, double Kd, double Kf) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        this.Kf = Kf;
    }
}


