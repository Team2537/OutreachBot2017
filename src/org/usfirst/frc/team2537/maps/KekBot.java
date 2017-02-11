package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class KekBot extends CommandGroup {
	public KekBot() {
		addSequential(new AutoRotateCommand(42.929969346958906));
		addSequential(new CourseCorrect(140.94764985624983));
		addSequential(new AutoRotateCommand(-54.405496098381946));
		addSequential(new CourseCorrect(81.42775939444729));
		addSequential(new AutoRotateCommand(-110.33386253637013));
		addSequential(new GearCommand());
		addSequential(new AutoRotateCommand(131.1864499820975));
		addSequential(new CourseCorrect(-160.584930799873));
		addSequential(new AutoRotateCommand(84.52343304807755));
		addSequential(new ShootCommand());
		addSequential(new AutoRotateCommand(9.384372742520299));
		addSequential(new CourseCorrect(-50.50346522764552));
		addSequential(new AutoRotateCommand(-106.22053993132337));
		addSequential(new GearCommand());
	}
}
