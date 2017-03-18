package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UltrasonicDrive extends Command {
	private static final int TOLERANCE = 9;
	public UltrasonicDrive() {
		requires(Robot.driveSys);
	}
	
	public void initialize() {
		Robot.driveSys.setDriveMotors(0.5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isFinished(){
		double ultraDistance = Robot.driveSys.ultraSanic.getRangeInches();
		//System.out.println(ultraDistance);
		if(ultraDistance <= TOLERANCE){
			return true;
		} else {
			return false;
		}
	}
	
	public void end() {
		
		Robot.driveSys.setDriveMotors(0);
		Robot.driveSys.resetEncoders();
	}
	
	public void interrupted() {
		end();
	}
}
