package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	private boolean shooterOff;
	private final static int TARGET_SPEED = 1000;
	private final static int INNER_TARGET = 980;

	/**
	 * constructor that requires Robot.shooterSys
	 * 
	 * @param shooterOff
	 *            boolean: true = shooter motors are off, false = shooter motors
	 *            are on
	 */
	public ShooterCommand(boolean shooterOff) {
		requires(Robot.shooterSys);
		this.shooterOff = shooterOff;
	}

	/**
	 * Sets the talon mode of the exterior motor and turns the exterior motor
	 * on, with the PID set to TARGET_SPEED Enables the entire command
	 */
	@Override
	protected void initialize() {
		Robot.shooterSys.enable();
		Robot.shooterSys.setExteriorMotorMode();
		if (shooterOff) {
			Robot.shooterSys.turnExteriorMotorOff();
		} else {
			Robot.shooterSys.setSetpoint(TARGET_SPEED);
		}

	}

	/**
	 * Turns interior motor on if exterior motor is going fast enough Prints out
	 * the speed of the motor
	 */
	@Override
	protected void execute() {

		System.out.println(Robot.shooterSys.getExteriorSpeed());
		if (Robot.shooterSys.getExteriorSpeed() > 900) {
			Robot.shooterSys.setInteriorMotor(1);
		} else {
			Robot.shooterSys.setInteriorMotor(0);
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
		Robot.shooterSys.setInteriorMotor(0);
		Robot.shooterSys.setExteriorMotor(0);
	}

	/**
	 * turns flywheels off if interrupted
	 */
	@Override
	protected void interrupted() {
		Robot.shooterSys.setInteriorMotor(0);
		Robot.shooterSys.setExteriorMotor(0);
	}

}
