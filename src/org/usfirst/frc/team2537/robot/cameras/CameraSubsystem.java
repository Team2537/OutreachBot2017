package org.usfirst.frc.team2537.robot.cameras;

import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSubsystem extends Subsystem {

	UsbCamera camera0;
	UsbCamera camera1;
	private int cam = 0;

	@Override
	protected void initDefaultCommand() {
		
	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.cameraSwitchButton, new CameraCommand());
	}

	public void cam0() {
		camera0 = CameraServer.getInstance().startAutomaticCapture("cam", 0);
		camera1 = null;
		cam = 0;
	}

	public void cam1() {
		camera1 = CameraServer.getInstance().startAutomaticCapture("cam", 1);
		camera0 = null;
		cam = 1;
	}

	public void switchCams() {
		if (cam == 0) {
			cam1();
		} else if (cam == 1) {
			cam0();
		}
	}

}
