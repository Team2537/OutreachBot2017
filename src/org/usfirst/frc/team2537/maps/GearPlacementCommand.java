package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.GearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearPlacementCommand extends CommandGroup {
	public GearPlacementCommand(){
		//addSequential(new CourseCorrect(55.00));
		//addSequential(new AutoRotateCommand(60.00));
		addSequential(new GearCommand());
	}
}
