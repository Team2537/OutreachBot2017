package org.usfirst.frc.team2537.robot.cameras;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

public class CameraGetCommand extends Command {
	
	private CvSource outputStream;
	private Mat source;
	private Mat output;

	public CameraGetCommand() {
		requires(Robot.camSys);
	}
	
	protected void initialize() {
		outputStream = CameraServer.getInstance().putVideo("cam0", 640, 480);
		source = new Mat();
		output = new Mat();
	}
	
	protected void execute() {
		Robot.camSys.cvSink.grabFrame(source);
		output = source;
		Imgproc.line(source, new Point(output.cols() / 2, 0), new Point(output.cols() / 2, output.rows()), new Scalar(255, 35, 0), 1);
		Imgproc.line(source, new Point(0, output.rows() / 2), new Point(output.cols(), output.rows() / 2), new Scalar(255, 35, 0), 1);
//		if (Robot.driveSys.getUltrasonic() < driveFarRange) {
//			if (Robot.driveSys.getUltrasonic() > driveCloseRange) {
//				Core.add(source, new Scalar(0, 100, 0), output);
//			} else {
//				Core.add(source, new Scalar(0, 0, 100), output);
//			}
//		}
		outputStream.putFrame(output);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
