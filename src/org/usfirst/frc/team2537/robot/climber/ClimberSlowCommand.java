package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

public class ClimberSlowCommand extends ClimberCommand{
	public ClimberSlowCommand() {
		requires(Robot.climberSys);
	}
	
	protected void initialize(){
		Robot.climberSys.setClimberMotor(-.3);
	}
		
	protected void execute(){
		System.out.println("climber is running slowly");
	}
	
	protected boolean isFinished(){
		return false;
	}
	
	protected void end(){
		Robot.climberSys.setClimberMotor(0);
	}
	
	protected void interrupted(){
		Robot.climberSys.setClimberMotor(0);
	}

}
