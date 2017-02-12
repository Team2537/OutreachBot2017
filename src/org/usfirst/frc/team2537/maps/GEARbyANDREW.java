package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.GearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GEARbyANDREW extends CommandGroup {
	public GEARbyANDREW() {
		addSequential(new GearCommand());
	}
}
