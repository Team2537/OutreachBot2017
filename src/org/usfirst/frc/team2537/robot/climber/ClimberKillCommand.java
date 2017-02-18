package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberKillCommand extends Command {
	/**
	 * Cancels climber completely
	 */
	
	public ClimberKillCommand() {
		requires(Robot.climberSys);
	}

	protected void initialize() {

		Robot.climberSys.setClimberMotor(0);
		//System.out.println("Climber is dead");
	}
		


	@Override
	protected void execute(){
		
	}
	
		
		
	
	@Override
	protected boolean isFinished(){
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
