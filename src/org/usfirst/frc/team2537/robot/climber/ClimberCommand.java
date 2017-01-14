package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command {
	//executes climber function
	
	public int shortClimbTimems = 5000;
	public int longClimbTimems = 30000;

		public ClimberCommand() {
				requires(Robot.climberSys);
		}
		
		
	@Override
	protected void initialize() {
		System.out.println("The climber is running");
	}


	@Override
	protected void execute(){
	
		Robot.climberSys.setCLimberMotor(.25);
		System.out.println("The climber is running slowly");	
		try {
			Thread.sleep(shortClimbTimems);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		Robot.climberSys.setCLimberMotor(.75);
		System.out.println("The climber is running quickly");
		Robot.climberSys.setCLimberMotor(longClimbTimems);
		try {
			Thread.sleep(longClimbTimems);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
			
		}
		
	
	@Override
	protected boolean isFinished(){
		return false;
		
	}
	
	@Override
	protected void end() {
		Robot.climberSys.setCLimberMotor(0);
	}
	
	@Override
	protected void interrupted() {
		Robot.climberSys.setCLimberMotor(0);
	}
}
