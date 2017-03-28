package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command {
	// executes climber function

	private ClimbStatus climbStatus = ClimbStatus.GROUNDED;

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
		if (climbStatus == ClimbStatus.GROUNDED && Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL) > ClimberSubsystem.GROUND_AMPERAGE_THRESHOLD) {
			climbStatus = ClimbStatus.AIR;
		}
		if (climbStatus == ClimbStatus.AIR && Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL) > ClimberSubsystem.AIR_AMPERAGE_CUTOFF) {
			climbStatus = ClimbStatus.TOUCHING;
		}
		Robot.climberSys.setClimberMotor(climbStatus.speed());
	}

	/**
	 * Finishes command if amperage goes over the amperage limit
	 */
	@Override
	protected boolean isFinished() {
		return Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL) > ClimberSubsystem.AIR_AMPERAGE_CUTOFF;
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
