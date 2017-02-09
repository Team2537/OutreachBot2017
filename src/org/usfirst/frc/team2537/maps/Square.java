package org.usfirst.frc.team2537.maps;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCameraCommand;
import org.usfirst.frc.team2537.robot.auto.UltraSonicCourseCorrect;
import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;

public class Square extends CommandGroup {
	public Square(){
		addSequential(new AutoRotateCommand(92.24473869766018));
		addSequential(new AutoRotateCommand(80.1970578988456));

	}
}
