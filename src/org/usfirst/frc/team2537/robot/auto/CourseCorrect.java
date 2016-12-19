
package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;

public class CourseCorrect extends Command {

	private static final double DEFAULT_SPEED = 0.5;
	private static final double CORRECTION_PROPORTION = 45; // it just worked,
															// y'no?
	private static final double TOLERANCE = 1;
	private static final boolean debug = false;
	private double speed;
	private double startAngle;
	private double distance;
	private AHRS ahrs;
	private boolean slowingDown = false;
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
		try {
			/* Communicate w/navX-MXP via the MXP SPI Bus. */
			/*
			 * Alternatively: I2C.Port.kMXP, SerialPort.Port.kMXP or
			 * SerialPort.Port.kUSB
			 */
			/*
			 * See
			 * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/
			 * for details.
			 */
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}
	}

	@Override
	protected void initialize() {
		startAngle = ahrs.getAngle();
		if (debug)
			System.out.println("CourseCorrect init: startAngle: " + startAngle);
	}

	@Override
	protected void execute() {
		double currentAngle = ahrs.getAngle();
		if (!slowingDown && Math.abs(Math.abs(distance) - Math.abs(getDisplacement())) < 6) {
			speed /= 2;
			slowingDown = true;
		}

		double left = speed;
		double right = speed;
		double correction = 0;

		double angleDiff = startAngle - currentAngle;
		if (debug)
			System.out.println("CourseCorrect exec: start: " + startAngle + "\tdiff: " + angleDiff);

		if (angleDiff < -180)
			angleDiff += 360;
		else if (angleDiff > 180)
			angleDiff -= 360;

		if (Math.abs(angleDiff) > TOLERANCE)
			correction = angleDiff / CORRECTION_PROPORTION;

		left += correction;
		right -= correction;

		Robot.driveSys.setDriveMotors(left, right);
	}

	@Override
	protected boolean isFinished() {
		if (isTimedOut())
			return true;
		return distance < 0 ? getDisplacement() <= distance
				: getDisplacement() >= distance;
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);
	}
	
	protected double getDisplacement(){
		return Math.sqrt(Math.pow(ahrs.getDisplacementX(), 2) + Math.pow(ahrs.getDisplacementY(), 2));
	}
}
