 package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Wait4Gear extends Command{
	long startTime = System.nanoTime();
	@Override
	protected boolean isFinished() {
//		if(System.nanoTime()-startTime>3000){
//			return true;
//		}
		System.out.println(Robot.driveSys.diosaur.get());
		if(!Robot.driveSys.infrared.isPulsing()){
			System.out.println("gays will be homo");
			Robot.driveSys.infrared.pulse(1);
		}
		if(!Robot.driveSys.diosaur.get()){
			System.out.println("done with infra");
			return true;
		}
		return false;
	}
	

}
