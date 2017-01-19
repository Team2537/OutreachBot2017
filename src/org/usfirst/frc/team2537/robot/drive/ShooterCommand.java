package org.usfirst.frc.team2537.robot.drive;

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
		requires(Robot.shooter);
		this.shooterOn = true;
	}

	@Override
	/**
	 * Ends by Turing FlyWheels Off
	 */
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.FlyOff();
	}

	/**
	 * if the robot is in range And shooterOn = true AND Limitswitch is pressed- Flywheels to speed 1
	 */
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if (shooterOn && Robot.shooter.getLimitSwitch()) {
			if (Robot.shooter.UltronRange() > ShooterSubsystem.DISTANCE_TO_BOILER - ShooterSubsystem.LEEWAY
					&& Robot.shooter.UltronRange() < ShooterSubsystem.DISTANCE_TO_BOILER + ShooterSubsystem.LEEWAY) {
				Robot.shooter.FlyOn();
			}
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Turns flywheels off when interrupted
	 */
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.shooter.FlyOff();
	}

	@Override
	/**
	 * if Robot is out of range or shooterOn is not true - Stop Running
	 */
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.shooter.UltronRange() < Robot.shooter.DISTANCE_TO_BOILER - Robot.shooter.LEEWAY
				|| Robot.shooter.UltronRange() > Robot.shooter.DISTANCE_TO_BOILER + Robot.shooter.LEEWAY;
	}
}
