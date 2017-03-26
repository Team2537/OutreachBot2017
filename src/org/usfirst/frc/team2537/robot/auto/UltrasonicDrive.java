package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UltrasonicDrive extends Command {
	private static final int TOLERANCE = 9;
	private static final double SPEED = 0.25;
	public UltrasonicDrive() {
		super(5);
		requires(Robot.driveSys);
	}
	
	public void initialize() {
		Robot.driveSys.setDriveMotors(SPEED);
	}

	@Override
	public boolean isFinished(){
		double ultraDistance = Robot.driveSys.ultraSanic.getRangeInches();
		//System.out.println(ultraDistance);
		if(Robot.driveSys.ultraSanic.getRangeInches() <= 1){
			return isTimedOut();
		} else{
			return ultraDistance <= TOLERANCE;
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
