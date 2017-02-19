package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.GearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BamboozeledAgain extends CommandGroup {
	public BamboozeledAgain() {
		addSequential(new CourseCorrect(42.599999999999994));
		addSequential(new GearCommand());
		addSequential(new AutoRotateCommand(-56.13630943118173));
		addSequential(new CourseCorrect(109.83114312434338));
		addSequential(new AutoRotateCommand(56.13630943118173));
		addSequential(new CourseCorrect(181.2));
	}
	@Override
	public void end(){
		System.out.println(this.isFinished());
	}
}
