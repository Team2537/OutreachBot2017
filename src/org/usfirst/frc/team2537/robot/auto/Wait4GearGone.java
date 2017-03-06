 package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Wait4GearGone extends Command{
	private long startTime = 0;
	@Override
	protected boolean isFinished() {
		if(!Robot.driveSys.infrared.isPulsing()){
			Robot.driveSys.infrared.pulse(1e-5);
		}
		if(Robot.driveSys.diosaur.get()){
			System.out.println("done with infra");
			if (startTime == 0) {
				startTime = System.currentTimeMillis();
			}
			
			if (System.currentTimeMillis() > startTime + 3000) {
				return true;
			}
		} else {
			startTime = 0;
		}
		return false;
	}
}
