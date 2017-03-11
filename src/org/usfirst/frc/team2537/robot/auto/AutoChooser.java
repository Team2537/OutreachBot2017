package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.maps.LeftGear;
import org.usfirst.frc.team2537.maps.MiddleGear;
import org.usfirst.frc.team2537.maps.RightGear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser extends SendableChooser<Command> {
	public AutoChooser() {
		addDefault("Middle Gear", new MiddleGear());
		addObject("Left Gear", new LeftGear());
		addObject("Right Gear", new RightGear());
		addObject("Vision test", new VisionRotate());
		addObject("Gearing Calibration Test", new DriveStraightCommand(24));
	}
}