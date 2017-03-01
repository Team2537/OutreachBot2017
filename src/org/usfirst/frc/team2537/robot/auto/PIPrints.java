package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PIPrints extends Command{
	@Override
	public boolean isFinished(){
		System.out.println(Robot.pwm.getDutyCycle());
		return false;
		
	}

}
