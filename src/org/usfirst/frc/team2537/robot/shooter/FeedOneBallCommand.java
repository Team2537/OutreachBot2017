package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FeedOneBallCommand extends Command {
	
	private long startTime;
	
	protected void initialize(){
		startTime = System.currentTimeMillis();
		Robot.shooterSys.setInteriorMotor(1);
		
	}
	
	protected void execute(){
		
	}
	
	protected boolean isFinished(){
		return System.currentTimeMillis() - 100 > startTime;
		
	}
	
	protected void end(){
		Robot.shooterSys.setInteriorMotor(0);
	}
	
	protected void interrupted(){
		Robot.shooterSys.setInteriorMotor(0);
	}
}
