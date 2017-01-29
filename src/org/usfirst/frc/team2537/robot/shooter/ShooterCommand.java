package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {
	private boolean shooterOn;

	/**
	 * Constructor for Command Requiring Shooter
	 * 
	 * @param shooterOn
	 *            - if Fly Wheels are off or on
	 */
	public ShooterCommand() {
		requires(Robot.shooterSys);
		this.shooterOn = true;
	}

	@Override
	/**
	 * Ends by Turing FlyWheels Off
	 */
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooterSys.FlyOff();
	}

	/**
	 * if the robot is in range And shooterOn = true AND Limitswitch is pressed-
	 * Flywheels to speed 1
	 */
	@Override
	protected void execute() {
		// TODO make sure this shit works
		/*
		 * if (shooterOn && Robot.shooterSys.getLimitSwitch()) { if
		 * (Robot.shooterSys.UltronRange() > ShooterSubsystem.DISTANCE_TO_BOILER
		 * - ShooterSubsystem.LEEWAY && Robot.shooterSys.UltronRange() <
		 * ShooterSubsystem.DISTANCE_TO_BOILER + ShooterSubsystem.LEEWAY) {
		 * Robot.shooterSys.FlyOn(); } }
		 */
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.shooterSys.FlyOn();
	}

	@Override
	/**
	 * Turns flywheels off when interrupted
	 */
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.shooterSys.FlyOff();
	}

	@Override
	/**
	 * if Robot is out of range or shooterOn is not true - Stop Running
	 */
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.shooterSys.UltronRange() < Robot.shooterSys.DISTANCE_TO_BOILER - Robot.shooterSys.LEEWAY
				|| Robot.shooterSys.UltronRange() > Robot.shooterSys.DISTANCE_TO_BOILER + Robot.shooterSys.LEEWAY;
	}
}
