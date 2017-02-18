package org.usfirst.frc.team2537.robot.cameras; 
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Cameras {

	private int driveCloseRange = 6, driveFarRange = 9;
	private CvSink cvSink;
	private UsbCamera cam0;
	private UsbCamera cam1;
	private int camNum;

	public Cameras() {
		new Thread(() -> {
			camNum = 0;
			cam0 = new UsbCamera("cam0", 0);
			cam1 = new UsbCamera("cam1", 1);
			cam0.setResolution(640, 480);
			cam1.setResolution(640, 480);
//			CameraServer.getInstance().addCamera(cam0);

			cvSink = CameraServer.getInstance().getVideo(cam0);
			CvSource outputStream = CameraServer.getInstance().putVideo("cam0", 640, 480);

			Mat source = new Mat();
			Mat output = new Mat();

			while (!Thread.interrupted()) {
				cvSink.grabFrame(source);
				output = source;
				Imgproc.line(source, new Point(output.cols() / 2, 0), new Point(output.cols() / 2, output.rows()), new Scalar(255, 35, 0), 1);
				Imgproc.line(source, new Point(0, output.rows() / 2), new Point(output.cols(), output.rows() / 2), new Scalar(255, 35, 0), 1);
//				if (Robot.driveSys.getUltrasonic() < driveFarRange) {
//					if (Robot.driveSys.getUltrasonic() > driveCloseRange) {
//						Core.add(source, new Scalar(0, 100, 0), output);
//					} else {
//						Core.add(source, new Scalar(0, 0, 100), output);
//					}
//				}
				outputStream.putFrame(output);
			}
		}).start();
	}
	
	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.cameraSwitchButton, new CameraSwitchCommand());
	}
	
	public void toggleCams() {
		if (camNum == 0) {
			System.out.println("Camera Toggled to Camera 1");
			cvSink.free();
//			cam0.free();
//			CameraServer.getInstance().removeCamera("cam0");
			
//			cam1 = CameraServer.getInstance().startAutomaticCapture(1);
			
//			cam1 = new UsbCamera("cam1", 1);
//			cam1.setResolution(640, 480);
//			CameraServer.getInstance().addCamera(cam1);
//			System.out.println(cam1.getHandle());
			cvSink = CameraServer.getInstance().getVideo(cam1);
			camNum = 1;
		} else {
			System.out.println("Camera Toggled to Camera 0");
			cvSink.free();
//			cam1.free();
//			CameraServer.getInstance().removeCamera("cam1");
			
			
//			cam0 = CameraServer.getInstance().startAutomaticCapture(0);

//			cam0 = new UsbCamera("cam0", 0);
//			cam0.setResolution(640, 480);
//			CameraServer.getInstance().addCamera(cam0);
//			System.out.println(cam0.getHandle());
			cvSink = CameraServer.getInstance().getVideo(cam0);
			camNum = 0;
		}
	}
}
