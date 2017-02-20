package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardTimedAuto extends Command {
	
	public DriveForwardTimedAuto() {
		// TODO Auto-generated constructor stub
		super(5);
		requires(Robot.driveSys);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.driveSys.setMotors(1);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isTimedOut();
	}
	
	protected void end() {
		Robot.driveSys.setMotors(0);
	}

}
