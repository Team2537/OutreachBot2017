package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.climber.ClimbStatus;
import org.usfirst.frc.team2537.robot.climber.ClimberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {
	
	long startTime;
	static boolean shooterLock = true;
	
	public ShooterCommand() {
		requires(Robot.shooterSys);
		shooterLock = !shooterLock;
	}

	@Override
	protected void initialize() {
		if(shooterLock == true){
			Robot.shooterSys.setShooterReleased();
		} else {
			Robot.shooterSys.setShooterReleased();
		}
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		if (System.currentTimeMillis() - startTime > 10){
			Robot.shooterSys.setShooterLocked();
		} else {
			Robot.shooterSys.setShooterMotor(0);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {

	}

}
