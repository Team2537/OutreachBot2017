package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.Specs;
import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;
import org.usfirst.frc.team2537.robot.auto.UltrasonicDrive;
import org.usfirst.frc.team2537.robot.auto.VisionRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleGear extends CommandGroup {
	public MiddleGear() {
		addSequential(new DriveStraightCommand(Specs.AUTO_DISTANCE_FORWARD_TO_SIDE_PEG));
		addSequential(new VisionRotate());
		addSequential(new UltrasonicDrive());
//		addSequential(new Wait4GearGone());
//		addSequential(new DriveStraightCommand(-40));
//		addSequential(new RotateCommand(90));
//		addSequential(new DriveStraightCommand(60));
//		addSequential(new RotateCommand(-90));
//		addSequential(new DriveStraightCommand(84));
	}
}
