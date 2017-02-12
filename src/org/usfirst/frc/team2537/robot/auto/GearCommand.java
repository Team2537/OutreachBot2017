package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearCommand extends CommandGroup{
	public GearCommand(){
		addSequential(new AutoRotateCameraCommand());
		addSequential(new UltraSonicCourseCorrect());
	}
}
