package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.GearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidRedLowGear extends CommandGroup {
	public MidRedLowGear() {
		addSequential(new AutoRotateCommand(53.937700816410256));
		addSequential(new CourseCorrect(144.73437739528228));
		addSequential(new AutoRotateCommand(-71.41247507266593));
		addSequential(new CourseCorrect(101.90289495397076));
		addSequential(new AutoRotateCommand(-104.13272798999324));
		addSequential(new GearCommand());
	}
}
