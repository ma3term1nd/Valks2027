package org.firstinspires.ftc.teamcode.main;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

@TeleOp(name = "Vikes2027_TeleOp")
public class Vikes2027_TeleOp extends OpMode {

    MecanumDrive drive;
    Pose2d beginPos = new Pose2d(60,12,Math.toRadians(180));

    public void init() {
        drive = new MecanumDrive(hardwareMap, beginPos);
        drive.lazyImu.get().resetYaw();
    }

    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;
        drive.driveFieldRelative(forward, strafe, rotate); //field oriented
    }
}
