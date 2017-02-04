package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearCommand extends Command {
	private boolean up;

	public GearCommand(boolean up) {
		requires(Robot.gearSys);
		this.up = up;
	}

	@Override
	protected void initialize() {
		System.out.println("Gear Command Initiated");   
		Robot.gearSys.setGearServo(up);
	           
	}
	
   
	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
		// Robot.gearSys.getLimitSwitch(up); // limit switch
	}

	@Override
	protected void end() {
		System.out.println("Gear Command Ended");
	}

	@Override
	protected void interrupted() {
		System.out.println("Gear Command Interrupted");
	}

}
