package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.UltraSonicCourseCorrect;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class UltrasonicTest extends CommandGroup{
	public UltrasonicTest(){
		addSequential(new UltraSonicCourseCorrect());
	}

}
