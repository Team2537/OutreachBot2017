package org.usfirst.frc.team2537.robot.cameras;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.command.Command;

public class CameraSelctionCommand extends Command{
	@Override
	protected void initialize() {
		Robot.cams.toggleCams();
	}
	
	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false; 
	}
	
	@Override
	protected void end() {
	}
	 
	@Override
	 protected void interrupted() {
	 }
}