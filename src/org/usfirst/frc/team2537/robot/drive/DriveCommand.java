package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	
	long startTime = System.currentTimeMillis();
	
	public DriveCommand(){
		requires(Robot.driveSys);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Drive Command Initiated");
		
	}

	@Override
	protected void execute() {
		Robot.driveSys.setLeftMotor(Robot.driveSys.getLeftJoystick(AxisType.kY));
		Robot.driveSys.setfrontLeftMotor(Robot.driveSys.getLeftJoystick(AxisType.kY));
		Robot.driveSys.setRightMotor(Robot.driveSys.getRightJoystick(AxisType.kY));
		Robot.driveSys.setfrontRightMotor(Robot.driveSys.getRightJoystick(AxisType.kY));
		
		if (System.currentTimeMillis() - startTime > 5000) {
			Robot.driveSys.getLeftEncoderBoolean();
			Robot.driveSys.getLeftEncoderDouble();
			Robot.driveSys.getLeftEncoderInt();
			Robot.driveSys.getRightEncoderBoolean();
			Robot.driveSys.getRightEncoderInt();
			Robot.driveSys.getRightEncoderDouble();
			startTime = System.currentTimeMillis();
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
		Robot.driveSys.setfrontLeftMotor(0);
		Robot.driveSys.setfrontRightMotor(0);
		
	}

	@Override
	protected void interrupted() {
		System.out.println("Drive command interrupted");
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		Robot.driveSys.setfrontLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
	}

}
