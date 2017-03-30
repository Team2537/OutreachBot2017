package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.climber.ClimbStatus;
import org.usfirst.frc.team2537.robot.climber.ClimberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {
	
	static boolean shooterLock = true;
	
	public ShooterCommand() {
		requires(Robot.shooterSys);
		shooterLock = !shooterLock;
	}

	@Override
	protected void initialize() {
		if(shooterLock == true){
			Robot.shooterSys.setShooterLocked();
		} else {
			Robot.shooterSys.setShooterReleased();
		}
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {

	}

}
