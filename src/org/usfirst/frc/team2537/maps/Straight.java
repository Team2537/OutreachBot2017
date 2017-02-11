package org.usfirst.frc.team2537.maps;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;

public class Straight extends CommandGroup {
	public Straight(){
		addSequential(new CourseCorrect(12.00));
	}
}
