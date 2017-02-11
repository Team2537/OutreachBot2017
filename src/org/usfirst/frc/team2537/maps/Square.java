package org.usfirst.frc.team2537.maps;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCameraCommand;
import org.usfirst.frc.team2537.robot.auto.UltraSonicCourseCorrect;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;

public class Square extends CommandGroup {
	public Square(){
		addSequential(new AutoRotateCommand(-0.9710219310791666));
		addSequential(new CourseCorrect(35.40508438063663));
		addSequential(new AutoRotateCommand(90.97102193107916));
		addSequential(new CourseCorrect(37.8));
		addSequential(new AutoRotateCommand(82.98349825527711));
		addSequential(new CourseCorrect(39.29427439207906));
	}
}
