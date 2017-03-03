package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Specs;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRun extends CommandGroup {
	public AutoRun() {
//		addSequential(new DriveForwardCommand(24));
//		System.out.println("Starting autorun course correct");
//		addSequential(new CourseCorrect(Specs.WALL_TO_AIRSHIP - Specs.LENGTH/2));
//		addSequential(new DriveForwardCommand(Specs.WALL_TO_AIRSHIP - Specs.ROBOT_LENGTH / 2));
//		System.out.println("Starting autorotate command");
		addSequential(new RotateCommand(-60));
//		System.out.println("Starting autorotatecamera command");
//		addSequential(new AutoRotateCameraCommand());
//		System.out.println("starting ultrasonic course correct");
//		addSequential(new UltraSonicCourseCorrect());
	}
}
