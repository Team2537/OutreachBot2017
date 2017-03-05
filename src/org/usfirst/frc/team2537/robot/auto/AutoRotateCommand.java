package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCommand extends Command {
	private AHRS ahrs;
	private double destinationAngle,currentAngle;

	
	private static final double DEFAULT_SPEED = 0.7;
	private static final double MINIMUM_SPEED = 0.4;
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
		ahrs.reset();
		this.destinationAngle = destinationAngle;
		speed = DEFAULT_SPEED;
	}

	@Override
	protected void initialize() {
		//System.out.println("AutoRotateInitialize");
		ahrs.reset();
	//	startAngle = ahrs.getAngle();
	//	System.out.println("startAngle is"+startAngle);
	}

	@Override
	protected void execute() {
		currentAngle = ahrs.getAngle();
		
		//System.out.println("Current Angle: "+(currentAngle-startAngle));
	
		if (currentAngle <= destinationAngle - TOLERANCE)
			Robot.driveSys.setDriveMotors(speed, -speed);
		if (currentAngle >= destinationAngle + TOLERANCE)
			Robot.driveSys.setDriveMotors(-speed, speed);
		//distance between relative destination angle and relative angle from start over the relative destination angle
		//e.g. (dest 90, curr 45) ratio = (90-45)/90 = 0.5
		//      resulatant speed = 0.5*DEFAULT_SPEED + MINIMUM_SPEED
		
		//System.out.println("Ratio: "+Math.abs((destinationAngle-currentAngle-startAngle)/SLOWDOWN_ANGLE));
		speed = Math.abs((destinationAngle-(currentAngle))/SLOWDOWN_ANGLE)*DEFAULT_SPEED+MINIMUM_SPEED;
		System.out.println("AutoRotateCommand :" + speed);
		System.out.println("angle: "+(currentAngle));
		if(speed>DEFAULT_SPEED){
			speed =DEFAULT_SPEED;
		}
		else if(speed<MINIMUM_SPEED){
			speed = MINIMUM_SPEED;
		}
	}

	@Override
	protected boolean isFinished() {
		//double currentAngle = ahrs.getAngle();
		return (((currentAngle) <= destinationAngle + TOLERANCE) && (currentAngle >= (destinationAngle - TOLERANCE)));
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
