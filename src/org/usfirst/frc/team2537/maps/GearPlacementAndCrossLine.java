package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.GearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearPlacementAndCrossLine extends CommandGroup {
	public GearPlacementAndCrossLine() {
		addSequential(new CourseCorrect(78.6));
		addSequential(new CourseCorrect(-48.0));
		addSequential(new AutoRotateCommand(-50.27389595735176));
		addSequential(new CourseCorrect(110.777615067305));
		addSequential(new AutoRotateCommand(50.27389595735176));
		addSequential(new CourseCorrect(97.19999999999999));
	}
}
