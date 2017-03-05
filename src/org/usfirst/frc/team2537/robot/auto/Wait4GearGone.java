 package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Wait4GearGone extends Command{
	long startTime = System.nanoTime();
	@Override
	protected boolean isFinished() {
		System.out.println(Robot.driveSys.diosaur.get());
		if(!Robot.driveSys.infrared.isPulsing()){
			Robot.driveSys.infrared.pulse(1e-5);
		}
		if(Robot.driveSys.diosaur.get()){
			System.out.println("done with infra");
			return true;
		}
		return false;
	}
}
