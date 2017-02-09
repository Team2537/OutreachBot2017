package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	private boolean shooterOff;
	private final static int WAIT_TIME = 1;

	/**
	 * constructor that requires Robot.shooterSys
	 * 
	 * @param shooterOff
	 *            boolean: true = shooter motors are off, false = shooter motors
	 *            are on
	 */
	public ShooterCommand(boolean shooterOff) {
		super(WAIT_TIME);
		requires(Robot.shooterSys);
		this.shooterOff = shooterOff;
	}

	/**
	 * turns both flywheels on, the inner one WAIT_TIME seconds after the outer
	 * one, in order to allow the outer one to spin up, if the shooter on button
	 * is pressed, or turns both flywheels off if the shooter off buttons is
	 * pressed
	 */
	@Override
	protected void initialize() {
		if (shooterOff) {
			Robot.shooterSys.flyOff(); // TODO: Repeatability test
		} else {
			Robot.shooterSys.fastOn();
		}
	}

	@Override
	protected void execute() {
		/*
		 * else if (Robot.shooterSys.getFastVelocity > whateverSpeed) {
		 * Robot.shooterSys.acivateSlowMotor(); } //Above code also to be used
		 * if using an encoder
		 * 
		 * 
		 * /* if (shooterOn && Robot.shooterSys.getLimitSwitch()) { if
		 * (Robot.shooterSys.UltronRange() > ShooterSubsystem.DISTANCE_TO_BOILER
		 * - ShooterSubsystem.LEEWAY && Robot.shooterSys.UltronRange() <
		 * ShooterSubsystem.DISTANCE_TO_BOILER + ShooterSubsystem.LEEWAY) {
		 * Robot.shooterSys.FlyOn(); } }
		 */
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		if (!shooterOff) {
			Robot.shooterSys.slowOn();
		}
	}

	/**
	 * turns flywheels off if interrupted
	 */
	@Override
	protected void interrupted() {
		Robot.shooterSys.flyOff();
	}

}
