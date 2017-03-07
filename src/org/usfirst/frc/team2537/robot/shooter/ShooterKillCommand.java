package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterKillCommand extends Command {
	
	/**
	 * shuts down the entire command
	 */
	public ShooterKillCommand() {
		requires(Robot.shooterSys);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		Robot.shooterSys.setExteriorMotor(0);
		Robot.shooterSys.setShooterServo(0.5);

		


	}

}
