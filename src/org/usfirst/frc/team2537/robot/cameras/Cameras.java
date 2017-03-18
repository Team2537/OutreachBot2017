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

public class Cameras extends Thread {

	private CvSink cvSink;
	private UsbCamera cam0;
	private UsbCamera cam1;
	private int camNum;
	private CvSource outputStream;
	private Mat source;
	private Mat output;
	private boolean switched;
	private long lastSwitched;
	
	/**
	 * Creates the default camera (cam0) and the cvSink
	 */
	public Cameras() {
		// Define Variables
		camNum = 0;
		lastSwitched = 0;
		source = new Mat();
		output = new Mat();

		// Setup default camera
		cam0 = new UsbCamera("cam0", 0);
		cam0.setResolution(320, 240);
		CameraServer.getInstance().addCamera(cam0);
		cvSink = CameraServer.getInstance().getVideo(cam0);
		outputStream = CameraServer.getInstance().putVideo("cams", 320, 240);
	}
	
	/**
	 * Toggles the cameras between cam0 and cam1
	 * Frees and removes the current camera, then creates and adds the new camera
	 */
	public void switchCameras() {
		if (camNum == 0) {
			// Destroy old camera
			CameraServer.getInstance().removeCamera("cam0");
			cam0.free(); 
			
			// Make new camera
			cam1 = new UsbCamera("cam1", 1);
			cam1.setResolution(320, 240);
			
			// Set new camera to CameraServer and cvSink
			CameraServer.getInstance().addCamera(cam1);
			cvSink.setSource(cam1);
			camNum = 1;
		} else {
			// Destroy old camera
			CameraServer.getInstance().removeCamera("cam1");
			cam1.free();
			
			// Make new camera
			cam0 = new UsbCamera("cam0", 0);
			cam0.setResolution(320, 240);
			
			// Set new camera to CameraServer and cvSink
			CameraServer.getInstance().addCamera(cam0);
			cvSink.setSource(cam0);
			camNum = 0;
		}
	}
	
	@Override
	public void run() {
		while(true) {
			// Check if buttons are pressed and if so either switch or change which overlay
			if (HumanInput.cameraSwitchButton.get()) {
				switchCameras();
			} else if (HumanInput.cameraSwitcherooButton.get() && System.currentTimeMillis() > lastSwitched + 500) {
				switched = !switched;
				lastSwitched = System.currentTimeMillis();
			}
			
			// Get frame from cvSink
			cvSink.grabFrame(source);
			
			if (source.rows() != 240 || source.cols() != 320) {
				continue;
			}
			output = source;

			// If on gear cam...
			if ((camNum == 0 && !switched) || (camNum == 1 && switched)) {
				// Makes the image greener if within the drive range, and red if too
				// close
				
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
			
			// If on climber cam...
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
			
			// Send frame to output stream
			outputStream.putFrame(output);
		}
	}

}
