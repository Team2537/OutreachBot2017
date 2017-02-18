package org.usfirst.frc.team2537.robot.cameras;

import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSubsystem extends Subsystem {

	protected CvSink cvSink;
	private UsbCamera cam0;
	private UsbCamera cam1;
	private int camNum;
	
	public CameraSubsystem() {
		camNum = 0;
		cam0 = new UsbCamera("cam0", 0);
		cam0.setResolution(640, 480);
		CameraServer.getInstance().addCamera(cam0);
		
		cvSink = CameraServer.getInstance().getVideo(cam0);
	}
	
	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new CameraGetCommand());
	}
	
	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.cameraSwitchButton, new CameraSwitchCommand());
	}
	
	public void switchCameras() {
		if (camNum == 0) {
			CameraServer.getInstance().removeCamera("cam0");
			cam0.free();
			cam1 = new UsbCamera("cam1", 1);
			cam1.setResolution(640, 480);
			CameraServer.getInstance().addCamera(cam1);
			cvSink.setSource(cam1);
			camNum = 1;
		} else {
			CameraServer.getInstance().removeCamera("cam1");
			cam1.free();
			cam0 = new UsbCamera("cam0", 0);
			cam0.setResolution(640, 480);
			CameraServer.getInstance().addCamera(cam0);
			cvSink.setSource(cam0);
			camNum = 0;
		}
	}
	

}
