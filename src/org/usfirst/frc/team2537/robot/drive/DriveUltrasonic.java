package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUltrasonic extends Command {
	
	public DriveUltrasonic() {
		// TODO Auto-generated constructor stub
		this.requires(Robot.driveSys);
	}

	@Override
	protected void end() {
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		System.out.println("Top nep: " + Robot.driveSys.getUltrasonic());
	}

	@Override
	protected void initialize() {
		Robot.driveSys.setLeftMotor(.1);
		Robot.driveSys.setRightMotor(.1);
		System.out.println("Top nep: " + Robot.driveSys.getUltrasonic());
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if (Robot.driveSys.getUltrasonic() <=12) 
		{return true;}
		else {return false;}
		
		
	}
	
	
	

}
