package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IncrementalSpeed extends Command {

	private double speed = 0;
	private long startTime = System.nanoTime();
	
	protected void execute() {
		if(System.nanoTime() - startTime > 1e9){
			startTime = System.nanoTime();
			speed += 0.05;
			Robot.driveSys.setDriveMotors(speed);
			System.out.println("SPEED INCREMENTED TO: " + speed);
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return speed >= 1;
	}

}
