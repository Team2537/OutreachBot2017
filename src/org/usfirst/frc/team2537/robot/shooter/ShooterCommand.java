package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	private boolean shooterOff;
	private final static double TARGET_SPEED = 500;
	private final static double INNER_TARGET_1 = 400;
	private final static double INNER_TARGET_2 = 600;
	private final static double P = 1200;
	private final static double TOLERANCE = 10;
	private double currentVoltage = 0;
	private long startTime;
	private long resetStartTime; // resets after every iteration of the 1st if statement in execute

	/**
	 * constructor that requires Robot.shooterSys
	 * 
	 * @param shooterOff
	 *            boolean: true = shooter motors are off, false = shooter
	 *            motors are on
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
		startTime = System.currentTimeMillis();
		resetStartTime = System.currentTimeMillis();
		Robot.shooterSys.setExteriorMotorMode();
		if (shooterOff) {
			Robot.shooterSys.turnExteriorMotorOff();
		} else {
			Robot.shooterSys.setExteriorMotor(0.7);
		}

	}

	/**
	 * Turns interior motor on if exterior motor is going fast enough Prints out
	 * the speed of the motor
	 */
	@Override

	protected void execute() {
		System.out.println(Robot.shooterSys.getExteriorSpeed());
		if (System.currentTimeMillis() - startTime >= 800) {
			if (Robot.shooterSys.getExteriorSpeed() < TARGET_SPEED - TOLERANCE || Robot.shooterSys.getExteriorSpeed() > TARGET_SPEED + TOLERANCE && System.currentTimeMillis() - resetStartTime >= 200) {
				if ((TARGET_SPEED - Robot.shooterSys.getExteriorSpeed() > 0)) {
					currentVoltage += (TARGET_SPEED - Robot.shooterSys.getExteriorSpeed()) / (3 * P);
				}
				if ((TARGET_SPEED - Robot.shooterSys.getExteriorSpeed() < 0)) {
					currentVoltage += (TARGET_SPEED - Robot.shooterSys.getExteriorSpeed()) / (P);
				}
				if (currentVoltage > 0.7) { // limits voltage for PID loop
					currentVoltage = 0.7;
				}
				if (currentVoltage < 0.35) {
					currentVoltage = 0.35;
				}
				Robot.shooterSys.setExteriorMotor(currentVoltage);
				resetStartTime = System.currentTimeMillis();
			}

		}
		if (System.currentTimeMillis() - startTime >= 600) {
			if (Robot.shooterSys.getExteriorSpeed() > INNER_TARGET_1
					&& Robot.shooterSys.getExteriorSpeed() < INNER_TARGET_2) {
				Robot.shooterSys.setShooterServo(0);
			} else {
				Robot.shooterSys.setShooterServo(0.5);
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
