package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	private boolean shooterOff;
	private final static double TARGET_SPEED = 500;
	private final static double INNER_TARGET_1 = 450;
	private final static double INNER_TARGET_2 = 550;
	private final static double MAKESHIFT_P = 2300;
	private final static double TOLERANCE = 30;
	private double currentVoltage = 0;
	private long startTime = System.currentTimeMillis();

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
		// Robot.shooterSys.enable();
		Robot.shooterSys.setExteriorMotorMode();
		if (shooterOff) {
			Robot.shooterSys.turnExteriorMotorOff();
		} else {
			Robot.shooterSys.setExteriorMotor(0.75);
		}

	}

	/**
	 * Turns interior motor on if exterior motor is going fast enough Prints out
	 * the speed of the motor
	 */
	@Override
	protected void execute() {
		System.out.println(Robot.shooterSys.getExteriorSpeed());
		if (System.currentTimeMillis() - startTime >= 500) {
			if ((System.currentTimeMillis() - startTime) >= 200 && (Robot.shooterSys.getExteriorSpeed() < TARGET_SPEED - TOLERANCE || Robot.shooterSys.getExteriorSpeed() > TARGET_SPEED + TOLERANCE)) {
				currentVoltage += (TARGET_SPEED - Robot.shooterSys.getExteriorSpeed()) / (MAKESHIFT_P);
				if (currentVoltage > 1) { // limits voltage for PID loop
					currentVoltage = 1;
				}
				if (currentVoltage < 0) {
					currentVoltage = 0;
				}
				// System.out.println(currentVoltage);
				Robot.shooterSys.setExteriorMotor(currentVoltage);
				startTime = System.currentTimeMillis();
			}
		}
		if (System.currentTimeMillis() - startTime >= 1000) {
			if (Robot.shooterSys.getExteriorSpeed() > INNER_TARGET_1 && Robot.shooterSys.getExteriorSpeed() < INNER_TARGET_2) {
				Robot.shooterSys.setInteriorMotor(1);
			} else {
				Robot.shooterSys.setInteriorMotor(0);
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
