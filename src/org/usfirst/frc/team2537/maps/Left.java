package org.usfirst.frc.team2537.maps;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;

public class Left extends CommandGroup {
	public Left(){
		addSequential(new AutoRotateCommand(-16.51570258895312));
		addSequential(new CourseCorrect(215.2821404575865));
		addSequential(new AutoRotateCommand(134.6942926989123));
		addSequential(new CourseCorrect(57.17656862736692));
	}
}
