package org.usfirst.frc.team2537.robot.cameras;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CameraSwitchCommand extends Command {

	public CameraSwitchCommand() {
		requires(Robot.camSys);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Camera Switch Command Initialized");
		Robot.camSys.switchCameras();
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
