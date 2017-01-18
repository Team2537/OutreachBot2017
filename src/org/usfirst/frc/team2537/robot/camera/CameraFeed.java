package org.usfirst.frc.team2537.robot.camera;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

public class CameraFeed extends IterativeRobot {
    
    public void robotInit() {
        CameraServer.getInstance().startAutomaticCapture();
    }
    
	protected void initialize() {
		System.out.println("Camera Feed Initiated");
	}
}