package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.maps.Left;
import org.usfirst.frc.team2537.maps.Square;
import org.usfirst.frc.team2537.maps.UltrasonicTest;
import org.usfirst.frc.team2537.maps.Test;
import org.usfirst.frc.team2537.maps.SimpleGearPlacement;
import org.usfirst.frc.team2537.maps.Straight;
import org.usfirst.frc.team2537.maps.Right;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser extends SendableChooser<Command> {
	public AutoChooser() {
		addObject("Left", new Left());
		addObject("Square", new Square());
		addObject("UltrasonicTest", new UltrasonicTest());
		addObject("Test", new Test());
		addObject("SimpleGearPlacement", new SimpleGearPlacement());
		addObject("Straight", new Straight());
		addObject("Right", new Right());
	}
}