package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.CourseCorrect;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveForward extends CommandGroup {
	public DriveForward() {
		addSequential(new CourseCorrect(220));
	}
}
