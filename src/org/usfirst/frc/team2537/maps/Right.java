package org.usfirst.frc.team2537.maps;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;

public class Right extends CommandGroup {
	public Right(){
		addSequential(new AutoRotateCommand(2.070030653041098));
		addSequential(new CourseCorrect(199.33007801132274));
		addSequential(new AutoRotateCommand(-128.70414452900852));
		addSequential(new CourseCorrect(29.160246912534884));
	}
}
