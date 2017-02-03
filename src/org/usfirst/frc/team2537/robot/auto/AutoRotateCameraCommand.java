package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCameraCommand extends Command {
	private AHRS ahrs;
	
	//values are from 0 to 1 (0 is left of camera, 1 is right)
	private static final double DESTINATION_DUTY = 0.5;
	private static final double DEFAULT_SPEED = 0.15;
	private static final double MINIMUM_SPEED = 0.0;
	private static final double SLOWDOWN_DUTY = 0.5; //distance value from destination
	private static final double TOLERANCE = 0.025;

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
		speed = DEFAULT_SPEED;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double currentDuty;
		if(Robot.pwm.getDutyCycle()<= 1){
			currentDuty = Robot.pwm.getDutyCycle();
		}
		else{
			currentDuty = 1;
		}
		if (currentDuty >= DESTINATION_DUTY - TOLERANCE)
			Robot.driveSys.setDriveMotors(-speed, speed);
		if (currentDuty <= DESTINATION_DUTY + TOLERANCE)
			Robot.driveSys.setDriveMotors(speed, -speed);
		
		speed = Math.abs((DESTINATION_DUTY-currentDuty)/SLOWDOWN_DUTY)*DEFAULT_SPEED+MINIMUM_SPEED;
		//System.out.println("AutoRotateCameraCommand :" + speed);
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
