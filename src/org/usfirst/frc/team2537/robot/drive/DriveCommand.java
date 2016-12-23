package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	
	public DriveCommand(){
		requires(Robot.driveSys);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Drive Command Initiated");
		
	}

	@Override
	protected void execute() {
		System.out.println("I'm executing! Drive mode: " + Robot.driveSys.driveMode);
		switch(Robot.driveSys.driveMode){
		case TANK_DRIVE:
			Robot.driveSys.setLeftMotor(Robot.driveSys.getLeftJoystick(AxisType.kY));
			Robot.driveSys.setRightMotor(Robot.driveSys.getRightJoystick(AxisType.kY));
			break;
			
		case ARCADE_DRIVE:
			double fwdSpeed = Robot.driveSys.getLeftJoystick(AxisType.kY);
			double sideSpeed = Robot.driveSys.getRightJoystick(AxisType.kX);
			
			if(sideSpeed < 0){
				Robot.driveSys.setLeftMotor(fwdSpeed*(sideSpeed + 0.5)*2);
				Robot.driveSys.setRightMotor(fwdSpeed);
			} else {
				Robot.driveSys.setLeftMotor(fwdSpeed);
				Robot.driveSys.setRightMotor(fwdSpeed*-(sideSpeed - 0.5)*2);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
		
		

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		System.out.println("Drive command end");
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		
	}

	@Override
	protected void interrupted() {
		System.out.println("Drive command interrupted");
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
	}

}
