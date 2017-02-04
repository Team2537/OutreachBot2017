
package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.vision.PWMSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class CameraCourseCorrect extends Command {

	private static final double DEFAULT_SPEED = 0.4;
	private static final double SLOWDOWN_START = 0.7;
	private static final double MINIMUM_SPEED = 0.05;
	private static final double CORRECTION_PROPORTION = 0.5; // it just worked, y'no?
	private static final double TOLERANCE = 1;
	private static final boolean debug = false;
	private double speed;
	private double startDuty;
	private double distance;
	private final double startSpeed;
	private PWMSubsystem pwm = Robot.pwm;
	public static final double DEFAULT_TIMEOUT = 3;

	/**
	 * Drives &lt;distance&gt; while correcting for angle
	 *
	 * @param distance
	 *            distance in inches
	 */
	public CameraCourseCorrect(double distance) {
		this(distance, distance < 0 ? -DEFAULT_SPEED : DEFAULT_SPEED);
	}

	/**
	 * Drives &lt;distance&gt; at &lt;speed&gt;
	 *
	 * @param distance
	 *            Distance in inched
	 * @param speed
	 *            Speed in voltage percent
	 */
	public CameraCourseCorrect(double distance, double speed) {
		this(distance, speed, DEFAULT_TIMEOUT);
	}

	public CameraCourseCorrect(double distance, double speed, double timeout) {
		super(timeout);
		requires(Robot.driveSys);
		this.distance = distance;
		this.speed = speed;
		startSpeed = speed;
	}

	@Override
	protected void initialize() {
		startDuty = pwm.getDutyCycle();
		if (debug) System.out.println("CourseCorrect init: startAngle: " + startDuty);
		Robot.driveSys.resetEncoders();
		Robot.driveSys.setDriveMotors(speed);
	}

	@Override
	protected void execute() {
		double currentDuty = pwm.getDutyCycle();
		if (Math.abs(Math.abs(distance) - Math.abs(Robot.driveSys.getEncoderAverage())) < SLOWDOWN_START) {
			speed = Math.abs(Math.abs(distance) - Math.abs(Robot.driveSys.getEncoderAverage()))/SLOWDOWN_START*startSpeed + MINIMUM_SPEED ;
		}

		double left = speed;
		double right = speed;
		double correction = 0;

		double dutyDiff = (currentDuty - startDuty)%360;
		if (debug) System.out.println("CourseCorrect exec: start: " + startDuty + "\tdiff: " + dutyDiff);

		if (Math.abs(dutyDiff) > TOLERANCE) correction = dutyDiff / CORRECTION_PROPORTION;

		left += correction;
		right -= correction;

		Robot.driveSys.setDriveMotors(left, right);
	}

	@Override
	protected boolean isFinished() {
		//if (isTimedOut()) return true;
		System.out.println("CourseCorrect :" + Robot.driveSys.getEncoderAverage());
		return distance < 0 ? Robot.driveSys.getEncoderAverage() <= distance : Robot.driveSys.getEncoderAverage() >= distance;
	}

	@Override
	protected void end() {
		System.out.println("CourseCorrect : end");

		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);
	}
}
