package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Specs;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRun extends CommandGroup {
	public AutoRun() {
		addSequential(new CourseCorrect(Specs.WALL_TO_AIRSHIP - Specs.LENGTH/2));
		addSequential(new AutoRotateCommand(60));
		addSequential(new AutoRotateCameraCommand());
		addSequential(new UltraSonicCourseCorrect());
	}
}
