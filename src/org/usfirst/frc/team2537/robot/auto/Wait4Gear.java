 package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Wait4Gear extends Command{
	long startTime = System.nanoTime();
	@Override
	protected boolean isFinished() {
		if(System.nanoTime()-startTime>3000){
			return true;
		}
		if(!Robot.driveSys.infrared.isPulsing()){
			Robot.driveSys.infrared.pulse(100);
		}
		if(!Robot.driveSys.diosaur.get()){
			return true;
		}
		return false;
	}
	

}
