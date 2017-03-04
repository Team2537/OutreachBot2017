package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.Specs;
import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;
import org.usfirst.frc.team2537.robot.auto.UltraSonicCourseCorrect;
import org.usfirst.frc.team2537.robot.auto.VisionRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleGear extends CommandGroup {
	public MiddleGear() {
		addSequential(new DriveStraightCommand(Specs.WALL_TO_AIRSHIP - Specs.ROBOT_LENGTH - 2 * Specs.BUMPER_WIDTH - 24));
//		addSequential(new VisisonRotate());
		addSequential(new UltraSonicCourseCorrect());
	}
}
