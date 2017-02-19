package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class GearCommand extends CommandGroup{
	public GearCommand(){
		addSequential(new UltraSonicCourseCorrect());
		addSequential(new Wait4Gear());
		addSequential(new CourseCorrect(false));
	}

}
