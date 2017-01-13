package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

public class GearCommand extends Command {

	public GearCommand() {
		requires(Robot.gearSys);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Gear Command Initiated");
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
		System.out.println("Gear command end");
	}
	
	@Override
	protected void interrupted() {
		System.out.println("Gear command interrupted");
	}

}
