package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.GearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Test extends CommandGroup {
	public Test() {
		addSequential(new AutoRotateCommand(-96.19787763343083));
		addSequential(new CourseCorrect(133.3796086364029));
		addSequential(new AutoRotateCommand(90.28541038746643));
		addSequential(new CourseCorrect(186.3915234124127));
		addSequential(new AutoRotateCommand(136.58740681122595));
		addSequential(new GearCommand());
		addSequential(new AutoRotateCommand(-108.45508519635922));
		addSequential(new CourseCorrect(198.32791029000435));
		addSequential(new AutoRotateCommand(-160.60781035284418));
	}
}
