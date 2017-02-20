package org.usfirst.frc.team2537.robot.cameras;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Cameras extends Thread {

	protected CvSink cvSink;
	private UsbCamera cam0;
	private UsbCamera cam1;
	private int camNum;
	private CvSource outputStream;
	private Mat source;
	private Mat output;
	private int driveCloseRange = 6;
	private int driveFarRange = 9;
	
	/**
	 * Creates the default camera (cam0) and the cvSink
	 */
	public Cameras() {
		camNum = 0;
		cam0 = new UsbCamera("cam0", 0);
		cam0.setResolution(320, 240);
		CameraServer.getInstance().addCamera(cam0);
		cvSink = CameraServer.getInstance().getVideo(cam0);
		source = new Mat();
		output = new Mat();
		outputStream = CameraServer.getInstance().putVideo("cams", 320, 240);
	}
	
	/**
	 * Toggles the cameras between cam0 and cam1
	 * Frees and removes the current camera, then creates and adds the new camera
	 */
	public void switchCameras() {
		if (camNum == 0) {
			CameraServer.getInstance().removeCamera("cam0");
			cam0.free();
			cam1 = new UsbCamera("cam1", 1);
			cam1.setResolution(320, 240);
			CameraServer.getInstance().addCamera(cam1);
			cvSink.setSource(cam1);
			camNum = 1;
		} else {
			CameraServer.getInstance().removeCamera("cam1");
			cam1.free();
			cam0 = new UsbCamera("cam0", 0);
			cam0.setResolution(320, 240);
			CameraServer.getInstance().addCamera(cam0);
			cvSink.setSource(cam0);
			camNum = 0;
		}
	}
	
	@Override
	public void run() {
		while(true) {
			cvSink.grabFrame(source);
			output = source;

			if (camNum == 0) {
				// Makes the image greener if within the drive range, and red if too
				// close
				if (Robot.driveSys.getUltrasonic() < driveFarRange) {
					if (Robot.driveSys.getUltrasonic() > driveCloseRange) {
						Core.add(source, new Scalar(0, 100, 0), output);
					} else {
						Core.add(source, new Scalar(0, 0, 100), output);
					}
				}
				
				// draws "GEAR" in the top right corner
				Imgproc.putText(source, "GEAR", new Point(output.cols() - 75, 25), 4, 0.8,
						new Scalar(0, 0, 0), 3);
				Imgproc.putText(source, "GEAR", new Point(output.cols() - 75, 25), 4, 0.8,
						new Scalar(55, 250, 37), 1);

				// draws a vertical and horizontal line through the center of the image
				Imgproc.line(source, new Point(output.cols() / 2, 0), new Point(output.cols() / 2, output.rows()),
						new Scalar(55, 250, 37), 1);
				Imgproc.line(source, new Point(0, output.rows() / 2), new Point(output.cols(), output.rows() / 2),
						new Scalar(55, 250, 37), 1);

			} else {
				//draws "CLIMB" in the top right corner
				Imgproc.putText(source, "CLIMB", new Point(output.cols() - 85, 25), 4, 0.8,
						new Scalar(0, 0, 0), 3);
				Imgproc.putText(source, "CLIMB", new Point(output.cols() - 85, 25), 4, 0.8,
						new Scalar(0, 223, 255), 1);
				
				// draws a vertical and horizontal line through the center of the image
				Imgproc.line(source, new Point(output.cols() / 2, 0), new Point(output.cols() / 2, output.rows()),
						new Scalar(0, 223, 255), 1);
				Imgproc.line(source, new Point(0, output.rows() / 2), new Point(output.cols(), output.rows() / 2),
						new Scalar(0, 223, 255), 1);

			}

			outputStream.putFrame(output);
			
			if (HumanInput.cameraSwitchButton.get()) {
				switchCameras();
			}
		}
	}

}
