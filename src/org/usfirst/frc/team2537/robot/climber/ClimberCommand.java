package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command {
	// executes climber function

	private int limitCurrent = 45; // Maximum current before climber kills itself

	public ClimberCommand() {
		requires(Robot.climberSys); 
	}

	@Override
	protected void initialize() {
	}

	/**
	 * Turns climber motor on at full speed
	 */
	@Override
	protected void execute() {
		Robot.climberSys.setClimberMotor(-.8);
	}

	/**
	 * Finishes command if amperage goes over the amperage limit
	 */
	@Override
	protected boolean isFinished() {
		return Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL) > limitCurrent;
	}

	/**
	 * Turns motor off and closes the file when the command is ended
	 */
	@Override
	protected void end() {
		Robot.climberSys.setClimberMotor(0);
	}

	/**
	 * Turns motor off and closes the file when the command is interrupted
	 */
	@Override
	protected void interrupted() {
		Robot.climberSys.setClimberMotor(0);
	}
}
