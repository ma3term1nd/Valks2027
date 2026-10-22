package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

@TeleOp(name = "Valks2027_TeleOp")
public class Valks2027_TeleOp extends OpMode {

    MecanumDrive drive;

    public void init() {
        //blank
    }

    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;
        //drive.MecanumDrive(forward, strafe, rotate); //regular drive
        drive.driveFieldRelative(forward, strafe, rotate); //field oriented
    }
}
