package org.usfirst.frc.team2537.robot.cameras; 
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Cameras {

	private int driveCloseRange = 6, driveFarRange = 9;

	public Cameras() {
		new Thread(() -> {
	
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
			camera.setResolution(640, 480);
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("cam0", 640, 480);

			Mat source = new Mat();
			Mat output = new Mat();

			while (!Thread.interrupted()) {
				cvSink.grabFrame(source);
				output = source;
				Imgproc.line(source, new Point(output.cols() / 2, 0), new Point(output.cols() / 2, output.rows()), new Scalar(255, 35, 0), 1);
				Imgproc.line(source, new Point(0, output.rows() / 2), new Point(output.cols(), output.rows() / 2), new Scalar(255, 35, 0), 1);
				if (Robot.driveSys.getUltrasonic() < driveFarRange) {
					if (Robot.driveSys.getUltrasonic() > driveCloseRange) {
						Core.add(source, new Scalar(0, 100, 0), output);
					} else {
						Core.add(source, new Scalar(0, 0, 100), output);
					} 
				}
				outputStream.putFrame(output);
			}
		}).start();
	







new Thread(() -> {
	
	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("cam1", 0);
	camera.setResolution(640, 480);
	CvSink cvSink = CameraServer.getInstance().getVideo();
	CvSource outputStream = CameraServer.getInstance().putVideo("cam1", 640, 480);

	Mat source = new Mat();
	Mat output = new Mat();

	while (!Thread.interrupted()) {
		cvSink.grabFrame(source);
		output = source;
		Imgproc.line(source, new Point(output.cols() / 2, 0), new Point(output.cols() / 2, output.rows()), new Scalar(23, 235, 0), 1);
		Imgproc.line(source, new Point(0, output.rows() / 2), new Point(output.cols(), output.rows() / 2), new Scalar(29, 333, 0), 1);
		if (Robot.driveSys.getUltrasonic() < driveFarRange) {
			if (Robot.driveSys.getUltrasonic() > driveCloseRange) {
				Core.add(source, new Scalar(0, 100, 0), output);
			} else {
				Core.add(source, new Scalar(0, 0, 100), output);
			} 
		}
		outputStream.putFrame(output);
	}
}).start();
}
}
