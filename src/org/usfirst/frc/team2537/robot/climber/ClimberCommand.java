package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command {
	// executes climber function


	public ClimberCommand() {
		requires(Robot.climberSys);
	}

	@Override
	protected void initialize() {
		// System.out.println("Climber is running");
	}

	/**
	 * sets climber motor
	 */
	@Override
	protected void execute() {

		if (Robot.climberSys.getXboxTrigger(3) > 0) {
			Robot.climberSys.setClimberMotor(Robot.climberSys.getXboxTrigger(3));
		} else {
			Robot.climberSys.setClimberMotor(0);
		}


	}


	@Override
	protected boolean isFinished() {
		return false;
		
	}

	@Override
	protected void end() {
		Robot.climberSys.setClimberMotor(0);
		// System.out.println("The climber is done");
	}

	@Override
	protected void interrupted() {
		Robot.climberSys.setClimberMotor(0);
		// System.out.println("The climber has been interrupted");
	}
}
