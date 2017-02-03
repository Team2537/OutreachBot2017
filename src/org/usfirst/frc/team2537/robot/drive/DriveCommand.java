package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	
	long startTime = System.currentTimeMillis();
	
	public DriveCommand(){
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {

	}

	protected void execute() {
		Robot.driveSys.setLeftMotors(Robot.driveSys.getLeftJoystick());
		Robot.driveSys.setRightMotors(Robot.driveSys.getRightJoystick());
	}
	

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveSys.setLeftMotors(0);
		Robot.driveSys.setRightMotors(0);		
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setLeftMotors(0);
		Robot.driveSys.setRightMotors(0);
	}
}
