package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command {
	// executes climber function

	public int shortClimbTimems = 5000;
	public int longClimbTimems = 30000;
	private long climbStartTime;
	private boolean startedLongClimb;

	public ClimberCommand() {
		requires(Robot.climberSys);
	}

	@Override
	protected void initialize() {
		climbStartTime = System.currentTimeMillis();
		System.out.println("The climber is running");
		Robot.climberSys.setCLimberMotor(.25);
		System.out.println("The climber is running slowly");
		startedLongClimb = false;

	}

	@Override
	protected void execute() {
		if (System.currentTimeMillis() - climbStartTime > shortClimbTimems && !startedLongClimb) {
			Robot.climberSys.setCLimberMotor(.75);
			System.out.println("The climber is running quickly");
			Robot.climberSys.setCLimberMotor(longClimbTimems);
			startedLongClimb = true;
		}
	}

	@Override
	protected boolean isFinished() {
		if (System.currentTimeMillis() - climbStartTime > longClimbTimems) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	protected void end() {
		Robot.climberSys.setCLimberMotor(0);
		System.out.println("The climber is done");
	}

	@Override
	protected void interrupted() {
		Robot.climberSys.setCLimberMotor(0);
		System.out.println("The climber has been interrupted");
	}
}
