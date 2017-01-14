package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearCommand extends Command {
	private long startTime;

	public GearCommand() {
		requires(Robot.gearSys);
	}

	@Override
	protected void initialize() {
		System.out.println("Gear Command Initiated");
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		Robot.gearSys.gearMove();
	}

	@Override
	protected boolean isFinished() {
		// TODO Add Or statement - Return true when Limit Switch is presses
		// TODO Add Or statement - Return true when Encoder Reaches Limit
		return System.currentTimeMillis() >= startTime + 100; // .1 seconds of running
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
