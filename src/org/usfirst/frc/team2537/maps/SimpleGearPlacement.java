package org.usfirst.frc.team2537.maps;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCameraCommand;
import org.usfirst.frc.team2537.robot.auto.UltraSonicCourseCorrect;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;

public class SimpleGearPlacement extends CommandGroup {
	public SimpleGearPlacement(){
		addSequential(new AutoRotateCommand(1.9251837083231547));
		addSequential(new CourseCorrect(71.44032474730221));
		addSequential(new AutoRotateCameraCommand());
		addSequential(new UltraSonicCourseCorrect(0));
	}
}
