package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCameraCommand extends Command {
	private AHRS ahrs;
	private double destinationDuty;

	
	private static final double DEFAULT_SPEED = 0.2;
	private static final double MINIMUM_SPEED = 0.06;
	private static final double SLOWDOWN_DUTY = 0.7;
	private static final double TOLERANCE = 1; // degrees

	private double speed;

	/**
	 * spins destinationAngle degrees
	 * 
	 * @param destinationDuty
	 *            relative angle in degrees
	 */
	public AutoRotateCameraCommand() {
		requires(Robot.driveSys);
		ahrs = Robot.driveSys.getAhrs();
		this.destinationDuty = 0.5;
		speed = DEFAULT_SPEED;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double currentDuty = Robot.pwm.getDutyCycle();
		
		//System.out.println("Current Angle: "+(currentAngle-startAngle));
		if (currentDuty <= destinationDuty - TOLERANCE)
			Robot.driveSys.setDriveMotors(-speed, speed);
		if (currentDuty >= destinationDuty + TOLERANCE)
			Robot.driveSys.setDriveMotors(speed, -speed);
		//distance between relative destination angle and relative angle from start over the relative destination angle
		//e.g. (dest 90, curr 45) ratio = (90-45)/90 = 0.5
		//      resulatant speed = 0.5*DEFAULT_SPEED + MINIMUM_SPEED
		
		//System.out.println("Ratio: "+Math.abs((destinationAngle-currentAngle-startAngle)/SLOWDOWN_ANGLE));
		speed = Math.abs((destinationDuty-currentDuty)/SLOWDOWN_DUTY)*DEFAULT_SPEED+MINIMUM_SPEED;
		//System.out.println("AutoRotateCommand :" + speed);
		if(speed>DEFAULT_SPEED){
			speed = DEFAULT_SPEED;
		}
	}

	@Override
	protected boolean isFinished() {
		double currentDuty = ahrs.getAngle();
		return false;//(currentDuty <= destinationDuty + TOLERANCE && currentDuty >= destinationDuty - TOLERANCE);
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
