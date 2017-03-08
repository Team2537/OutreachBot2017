package org.usfirst.frc.team2537.robot.vision;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.auto.VisionRotate;

import edu.wpi.first.wpilibj.command.Command;

public class RPiCalibration extends Command {
	
	public RPiCalibration() {
		requires(Robot.piSys);
		this.setRunWhenDisabled(true);
	}

	@Override
	protected void initialize() {
		VisionRotate.TARGET_DUTY_CYCLE = Robot.piSys.getDutyCycle();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
