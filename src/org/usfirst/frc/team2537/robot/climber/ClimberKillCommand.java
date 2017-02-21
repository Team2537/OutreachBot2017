package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberKillCommand extends Command {
	/**
	 * Cancels climber completely
	 */

	public ClimberKillCommand() {
		requires(Robot.climberSys); // Interrupt climber command
	}

	protected void initialize() {
		Robot.climberSys.setClimberMotor(0); // Only set speed to 0
	}

	@Override
	protected void execute() {

	}

	/**
	 * isFinished is always true so that the command immediately goes to end
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		Robot.climberSys.setClimberMotor(0);
	}

	@Override
	protected void interrupted() {
		Robot.climberSys.setClimberMotor(0);
	}
}
