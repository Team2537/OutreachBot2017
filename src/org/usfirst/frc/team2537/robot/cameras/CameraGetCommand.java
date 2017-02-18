package org.usfirst.frc.team2537.robot.cameras;

import java.awt.Color;

import javax.swing.text.StyleConstants.FontConstants;

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

	/**
	 * Gets video from the current camera and displays it on the smartdashboard
	 */
	public CameraGetCommand() {
		requires(Robot.camSys);
		outputStream = CameraServer.getInstance().putVideo("cams", 640, 480);
		source = new Mat();
		output = new Mat();
	}
	
	/**
	 * Creates the outputStream
	 */
	protected void initialize() {

	}
	
	/**
	 * Grabs frames from the camera using cvSink, and does any image processing
	 */
	protected void execute() {
		Robot.camSys.cvSink.grabFrame(source);
		output = source;
		
		// draws a vertical and horizontal line through the center of the image
		Imgproc.line(source, new Point(output.cols() / 2, 0), new Point(output.cols() / 2, output.rows()), new Scalar(255, 35, 0), 1);
		Imgproc.line(source, new Point(0, output.rows() / 2), new Point(output.cols(), output.rows() / 2), new Scalar(255, 35, 0), 1);
		
		if(Robot.camSys.getCamNum() == 0){
			Imgproc.putText(source, "GEAR", new Point(output.cols() - 90, output.rows() - 10), 1, 2, new Scalar(0, 0, 0), 5);
			Imgproc.putText(source, "GEAR", new Point(output.cols() - 90, output.rows() - 10), 1, 2, new Scalar(55, 250, 37), 2);
		}else{
			Imgproc.putText(source, "CLIMBER", new Point(output.cols() - 140, output.rows() - 10), 1, 2, new Scalar(0, 0, 0), 5);
			Imgproc.putText(source, "CLIMBER", new Point(output.cols() - 140, output.rows() - 10), 1, 2, new Scalar(0, 223, 255), 2);			
		}
		
		// Makes the image greener if within the drive range, and red if too close
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
		return false;
	}

	@Override
	protected void end(){
		
	}
	
	@Override
	protected void interrupted(){
		
	}
	
}
