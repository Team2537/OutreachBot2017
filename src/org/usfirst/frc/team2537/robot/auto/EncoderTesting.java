package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EncoderTesting extends Command{

	@Override
	protected boolean isFinished() {
		// use this to find the encoder values in relation to gear ratio(HINT: it will be different than the 
		// pulses per revolution out of box unless there is no gearing on the wheels	
		System.out.println(Robot.driveSys.rencoder.get());
		return false;
	}

}
