package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.maps.DriveForward;
import org.usfirst.frc.team2537.maps.GearPlacementCommand;
import org.usfirst.frc.team2537.maps.Left;
import org.usfirst.frc.team2537.maps.PerfectStraightLine;
import org.usfirst.frc.team2537.maps.Right;
import org.usfirst.frc.team2537.maps.Square;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser extends SendableChooser<Command> {
	public AutoChooser() {
		addDefault("DriveForward", new DriveForward());
		addObject("GearPlacementCommand", new GearPlacementCommand());
		addObject("Left", new Left());
		addObject("PerfectStraightLine", new PerfectStraightLine());
		addObject("Right", new Right());
		addObject("Square", new Square());
	}
}