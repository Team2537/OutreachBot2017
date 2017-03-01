package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class CourseCorrect extends Command {

	protected static final double DEFAULT_SPEED = 0.4;
	private static final double SLOWDOWN_START = 0.7; // % distance from target
														// to start slowing down
														// at (0 to 1)
	protected static final double MINIMUM_SPEED = 0.1;
	protected static final double CORRECTION_PROPORTION = 90; // it just worked,
																// y'no?
	private static final double TOLERANCE = 1;
	private static final boolean debug = true;
	private double speed;
	private double startAngle;
	private double distance;
	private final double startSpeed;
	protected AHRS ahrs = Robot.driveSys.getAhrs();
	private boolean direction= true;
	private boolean useEncDistance=false;
	public static final double DEFAULT_TIMEOUT = 3;

	/**
	 * Drives &lt;distance&gt; while correcting for angle
	 *
	 * @param distance
	 *            distance in inches
	 */
	public CourseCorrect(double distance) {
		this(distance, distance < 0 ? -DEFAULT_SPEED : DEFAULT_SPEED);
	}

	/**
	 * Sets Course Correct to use the previously measured encoder values as a
	 * distance
	 * 
	 *
	 * @param direction
	 *            forward(true) or backward(false)
	 */
	public CourseCorrect(boolean direction){
		this(0);
		useEncDistance = true;
		this.direction = direction;			
	}

	/**
	 * Drives &lt;distance&gt; at &lt;speed&gt;
	 *
	 * @param distance
	 *            Distance in inched
	 * @param speed
	 *            Speed in voltage percent
	 */
	public CourseCorrect(double distance, double speed) {
		this(distance, speed, DEFAULT_TIMEOUT);
	}

	public CourseCorrect(double distance, double speed, double timeout) {
		super(timeout);
		requires(Robot.driveSys);
		this.distance = distance;
		this.speed = speed;
		startSpeed = speed;
	}

	@Override
	protected void initialize() {
		if (useEncDistance) {
			distance = direction ? Robot.driveSys.getEncoderAverage()
					: -Robot.driveSys.getEncoderAverage();
		}
		setStartAngle(ahrs.getAngle());
		if (debug)
			System.out.println("CourseCorrect init: startAngle: "
					+ getStartAngle());
		Robot.driveSys.resetEncoders();
		Robot.driveSys.setDriveMotors(speed);
	}

	@Override
	protected void execute() {
		double currentAngle = ahrs.getAngle();
		speed = Math.abs(Math.abs(distance)
				- Math.abs(Robot.driveSys.getEncoderAverage()))
				/ (getSlowdownStart() * Math.abs(distance)) * DEFAULT_SPEED;
		System.out.println(speed);
		if (speed < MINIMUM_SPEED)
			speed = MINIMUM_SPEED;
		if (speed > DEFAULT_SPEED)
			speed = DEFAULT_SPEED;

		if (distance < Robot.driveSys.getEncoderAverage()) {
			speed *= -1;
		}
		double left = speed;
		double right = speed;
		double correction = 0;

		double angleDiff = (currentAngle - getStartAngle()) % 360;
		if (debug)
			System.out.println("CourseCorrect exec: start: " + getStartAngle()
					+ "\tdiff: " + angleDiff);

		if (Math.abs(angleDiff) > TOLERANCE)
			correction = angleDiff / CORRECTION_PROPORTION;

		left += correction;
		right -= correction;

		Robot.driveSys.setDriveMotors(left, right);
	}

	@Override
	protected boolean isFinished() {
		// if (isTimedOut()) return true;
		// System.out.println("CourseCorrect :" +
		// Robot.driveSys.getEncoderAverage());
		return distance < 0 ? Robot.driveSys.getEncoderAverage() <= distance
				: Robot.driveSys.getEncoderAverage() >= distance;
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

	public static double getSlowdownStart() {
		return SLOWDOWN_START;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
