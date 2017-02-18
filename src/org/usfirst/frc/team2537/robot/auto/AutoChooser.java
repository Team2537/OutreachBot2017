package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.maps.DriveForward;
import org.usfirst.frc.team2537.maps.GEARbyANDREW;
import org.usfirst.frc.team2537.maps.jfwvhrwgi;
import org.usfirst.frc.team2537.maps.jvsjvrwn;
import org.usfirst.frc.team2537.maps.Left;
import org.usfirst.frc.team2537.maps.MidRedLowGear;
import org.usfirst.frc.team2537.maps.PerfectStraightLine;
import org.usfirst.frc.team2537.maps.Right;
import org.usfirst.frc.team2537.maps.SimpleGearPlacement;
import org.usfirst.frc.team2537.maps.Square;
import org.usfirst.frc.team2537.maps.Straight;
import org.usfirst.frc.team2537.maps.Test;
import org.usfirst.frc.team2537.maps.UltrasonicTest;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser extends SendableChooser<Command> {
	public AutoChooser() {
		addDefault("DriveForward", new DriveForward());
		addObject("GEARbyANDREW", new GEARbyANDREW());
		addObject("jfwvhrwgi", new jfwvhrwgi());
		addObject("jvsjvrwn", new jvsjvrwn());
		addObject("Left", new Left());
		addObject("MidRedLowGear", new MidRedLowGear());
		addObject("PerfectStraightLine", new PerfectStraightLine());
		addObject("Right", new Right());
		addObject("SimpleGearPlacement", new SimpleGearPlacement());
		addObject("Square", new Square());
		addObject("Straight", new Straight());
		addObject("Test", new Test());
		addObject("UltrasonicTest", new UltrasonicTest());
	}
}