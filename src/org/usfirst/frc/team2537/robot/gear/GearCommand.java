package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class GearCommand extends Command {
	private double speed;
	private boolean up;

	public GearCommand(boolean up) {
		super(10);
		requires(Robot.gearSys);
		this.up = up;
		if (up) {
			speed = .1;
		} else {
			speed = -.1;
		}
	}

	@Override
	protected void initialize() {
		System.out.println("Gear Command Initiated");
		Robot.gearSys.setGearMotor(speed);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut() || Robot.gearSys.getLimitSwitch(up); // 3 seconds of running OR limit switch
	}

	@Override
	protected void end() {
		System.out.println("Gear Command Ended");
		Robot.gearSys.setGearMotor(0);
	}

	@Override
	protected void interrupted() {
		System.out.println("Gear Command Interrupted");
		Robot.gearSys.setGearMotor(0);
	}

}
