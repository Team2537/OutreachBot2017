package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberKillCommand extends Command {
	
	/**
	 * Turns climber motor off and interrupts climber command
	 */
	public ClimberKillCommand() {
		requires(Robot.climberSys);
	}

	/**
	 * Turns climber motor off
	 */
	@Override
	protected void initialize() {
		Robot.climberSys.setClimberMotor(0);
	}

	@Override
	protected void execute() {
	}

	/**
	 * Finishes immediately
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	/**
	 * Turns climber motor off
	 */
	@Override
	protected void end() {
		Robot.climberSys.setClimberMotor(0);
	}

	/**
	 * Turns climber motor off
	 */
	@Override
	protected void interrupted() {
		Robot.climberSys.setClimberMotor(0);
	}
}
