package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.GearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class jvsjvrwn extends CommandGroup {
	public jvsjvrwn() {
		addSequential(new AutoRotateCommand(0.3430843995235294));
		addSequential(new CourseCorrect(100.20179639108272));
	}
}
