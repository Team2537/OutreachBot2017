package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTypeCommand extends Command {

	DriveTypeCommand(){
		requires(Robot.driveSys);
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		if(Robot.driveSys.driveMode.equals(DriveTypeEnum.TANK_DRIVE))
			Robot.driveSys.driveMode = DriveTypeEnum.ARCADE_DRIVE;
		else
			Robot.driveSys.driveMode = DriveTypeEnum.TANK_DRIVE;
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
