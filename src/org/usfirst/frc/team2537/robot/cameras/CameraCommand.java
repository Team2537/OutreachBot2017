package org.usfirst.frc.team2537.robot.cameras;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CameraCommand extends Command {

	public CameraCommand() {
		requires(Robot.cameraSys);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Camera Command Initialized");
		Robot.cameraSys.switchCams();
	}
	
	@Override
	protected void execute() {
		
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		System.out.println("Camera Command Ended");
	}
	
	@Override
	protected void interrupted() {
		System.out.println("Camera Command Interrupted");
	}

}
