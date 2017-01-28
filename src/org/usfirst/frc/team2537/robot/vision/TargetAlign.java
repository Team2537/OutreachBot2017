package org.usfirst.frc.team2537.robot.vision;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;

import edu.wpi.first.wpilibj.command.Scheduler;


public class TargetAlign extends AutoRotateCommand {
	
	final double TOLERANCE = 0.1;
	
	double dutyCycle;
	
	public TargetAlign(double destinationAngle) {
		super(destinationAngle);
	}
	
	@Override
	protected void end(){
		super.end();
		dutyCycle = Robot.pwm.getDutyCycle();
		if(dutyCycle > 0.5 + TOLERANCE / 2){
			Scheduler.getInstance().add(new TargetAlign((0.5 + TOLERANCE - dutyCycle)*90));
		} else
		if(dutyCycle < 0.5 - TOLERANCE / 2){
			Scheduler.getInstance().add(new TargetAlign((0.5 - TOLERANCE + dutyCycle)*90));
		}
	}
	
}
