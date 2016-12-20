package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLimitSwitchCommand extends Command {

	@Override
	protected void initialize() {
		Robot.driveSys.setLeftMotor(.2);
		Robot.driveSys.setRightMotor(.2);
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		if (Robot.driveSys.limitSwitch.get() == true) {
			return true;
		} else {
			return false;
		}
		// TODO Auto-generated method stub
	}

	@Override
	protected void end() {
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		// TODO Auto-generated method stub

	}

}
