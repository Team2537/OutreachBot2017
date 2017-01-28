package org.usfirst.frc.team2537.maps;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;

public class Straight extends CommandGroup {
	public Straight(){
		addSequential(new AutoRotateCommand(1.5765500551837321));
		addSequential(new CourseCorrect(65.4247659529631));
	}
}
