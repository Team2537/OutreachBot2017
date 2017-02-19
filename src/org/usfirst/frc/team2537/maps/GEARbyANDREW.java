package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.GearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GEARbyANDREW extends CommandGroup {
	public GEARbyANDREW() {
		addSequential(new CourseCorrect(40.2));
		addSequential(new AutoRotateCommand(40.55589387834731));
		addSequential(new GearCommand());
		addSequential(new AutoRotateCommand(-40.55589387834731));
		addSequential(new CourseCorrect(203.39999999999998));
	}
}
