package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCommand extends Command {
	private AHRS ahrs;
	private double destinationAngle, startAngle;

	
	private static final double DEFAULT_SPEED = 0.2;
	private static final double MINIMUM_SPEED = 0.06;
	private static final double SLOWDOWN_ANGLE = 60;
	private static final double TOLERANCE = 1; // degrees


	private double speed;

	/**
	 * spins destinationAngle degrees
	 * 
	 * @param destinationAngle
	 *            relative angle in degrees
	 */
	public AutoRotateCommand(double destinationAngle) {
		requires(Robot.driveSys);
		ahrs = Robot.driveSys.getAhrs();
		this.destinationAngle = destinationAngle;
		speed = DEFAULT_SPEED;
	}

	@Override
	protected void initialize() {
		//System.out.println("AutoRotateInitialize");
		startAngle = ahrs.getAngle();
	}

	@Override
	protected void execute() {
		double currentAngle = ahrs.getAngle();
		
		//System.out.println("Current Angle: "+(currentAngle-startAngle));
		if (currentAngle-startAngle <= destinationAngle - TOLERANCE)
			Robot.driveSys.setDriveMotors(-speed, speed);
		if (currentAngle-startAngle >= destinationAngle + TOLERANCE)
			Robot.driveSys.setDriveMotors(speed, -speed);
		//distance between relative destination angle and relative angle from start over the relative destination angle
		//e.g. (dest 90, curr 45) ratio = (90-45)/90 = 0.5
		//      resulatant speed = 0.5*DEFAULT_SPEED + MINIMUM_SPEED
		
		//System.out.println("Ratio: "+Math.abs((destinationAngle-currentAngle-startAngle)/SLOWDOWN_ANGLE));
		speed = Math.abs((destinationAngle-(currentAngle-startAngle))/SLOWDOWN_ANGLE)*DEFAULT_SPEED+MINIMUM_SPEED;
		//System.out.println("AutoRotateCommand :" + speed);
		if(speed>DEFAULT_SPEED){
			speed =DEFAULT_SPEED;
		}
	}

	@Override
	protected boolean isFinished() {
		double currentAngle = ahrs.getAngle();
		return (currentAngle-startAngle <= destinationAngle + TOLERANCE && currentAngle-startAngle >= destinationAngle - TOLERANCE);
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		System.out.println("disabled");
		Robot.driveSys.setDriveMotors(0);
	}

}
