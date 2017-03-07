/*package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * turns interior motor on long enough to feed one ball into the exterior flywheel
 * @author email
 *
 *//*
public class FeedOneBallCommand extends Command {
	
	private long startTime;
	
	protected void initialize(){
		startTime = System.currentTimeMillis();
		Robot.shooterSys.setShooterServo(1);
		
	}
	
	protected void execute(){
		
	}
	
	protected boolean isFinished(){
		return System.currentTimeMillis() - 100 > startTime;
		
	}
	
	protected void end(){
		Robot.shooterSys.setShooterServo(0);
	}
	
	protected void interrupted(){
		Robot.shooterSys.setShooterServo(0);
	}
}
*/
package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FeedOneBallCommand extends Command {

	private boolean shooterOff;
	private final static double TARGET_SPEED = 00;
	private final static double INNER_TARGET_1 = 00;
	private final static double INNER_TARGET_2 = 00;
	private final static double MAKESHIFT_P = 2300;
	private final static double TOLERANCE = 30;
	private double currentVoltage = 0;
	private long startTime = System.currentTimeMillis();

	/**
	 * constructor that requires Robot.shooterSys
	 * 
	 * @param shooterOff
	 *            boolean: true = shooter m otors are off, false = shooter motors
	 *            are on
	 */
	public FeedOneBallCommand(boolean shooterOff) {
		requires(Robot.shooterSys);
		this.shooterOff = shooterOff;
	}

	/**
	 * Sets the talon mode of the exterior motor and turns the exterior motor
	 * on, with the PID set to TARGET_SPEED Enables the entire command
	 */
	@Override
	protected void initialize() {
		Robot.shooterSys.setExteriorMotorMode();
		if (shooterOff) {
			Robot.shooterSys.turnExteriorMotorOff();
		} else {
			Robot.shooterSys.setExteriorMotor(0.5);
		}

	}

	/**
	 * Turns interior motor on if exterior motor is going fast enough Prints out
	 * the speed of the motor
	 */
	@Override
	protected void execute() {
		System.out.println(Robot.shooterSys.getExteriorSpeed());
		if (System.currentTimeMillis() - startTime >= 400) {
			if (Robot.shooterSys.getExteriorSpeed() < TARGET_SPEED - TOLERANCE || Robot.shooterSys.getExteriorSpeed() > TARGET_SPEED + TOLERANCE) {
				currentVoltage += (TARGET_SPEED - Robot.shooterSys.getExteriorSpeed()) / (MAKESHIFT_P);
				if (currentVoltage > 1) { // limits voltage for PID loop
					currentVoltage = 1;
				}
				if (currentVoltage < 0) {
					currentVoltage = 0;
				}
				Robot.shooterSys.setExteriorMotor(currentVoltage);
				startTime = System.currentTimeMillis();
			}
		}
		if (System.currentTimeMillis() - startTime >= /*6*/00) {
			if (Robot.shooterSys.getExteriorSpeed() > INNER_TARGET_1 && Robot.shooterSys.getExteriorSpeed() < INNER_TARGET_2) {
				Robot.shooterSys.setShooterServo(0);
			} else {
				Robot.shooterSys.setShooterServo(0/*.5*/);
			}
		} 
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * will never be engaged
	 */
	@Override
	protected void end() {
		Robot.shooterSys.setExteriorMotor(0);
		Robot.shooterSys.setShooterServo(0.5);
	}

	/**
	 * turns flywheels off if interrupted
	 */
	@Override
	protected void interrupted() {
		Robot.shooterSys.setExteriorMotor(0);
		Robot.shooterSys.setShooterServo(0.5);
	}

}
