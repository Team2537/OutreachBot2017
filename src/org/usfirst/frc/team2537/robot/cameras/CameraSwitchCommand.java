package org.usfirst.frc.team2537.robot.cameras;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CameraSwitchCommand extends Command {

	@Override
	protected void initialize() {
		System.out.println("Camera Switch Command Initialized");
		Robot.cams.toggleCams();
	}
	
	@Override
	protected void execute() {
		
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
		
	}
	
	@Override
	protected void interrupted() {
		
	}

}
