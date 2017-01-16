package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	public ShooterCommand() {
		requires(Robot.shooter);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.FlyOff();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if (Robot.shooter.UltronRange() > Robot.shooter.DISTANCE_TO_BOILER - Robot.shooter.LEE_WAY
				&& Robot.shooter.UltronRange() < Robot.shooter.DISTANCE_TO_BOILER + Robot.shooter.LEE_WAY){
			Robot.shooter.FlyOn();
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.shooter.FlyOff();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
	return Robot.shooter.UltronRange() < Robot.shooter.DISTANCE_TO_BOILER - Robot.shooter.LEE_WAY
			|| Robot.shooter.UltronRange() > Robot.shooter.DISTANCE_TO_BOILER + Robot.shooter.LEE_WAY;
	
	
		
	}

}
