package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * turns interior motor on long enough to feed one ball into the exterior flywheel
 * @author email
 *
 */
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
