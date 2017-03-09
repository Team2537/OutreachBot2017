package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.Specs;
import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;
import org.usfirst.frc.team2537.robot.auto.RotateCommand;
import org.usfirst.frc.team2537.robot.auto.UltrasonicDrive;
import org.usfirst.frc.team2537.robot.auto.VisionRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RightGear extends CommandGroup {
	public RightGear() {
		addSequential(new DriveStraightCommand(Specs.WALL_TO_AIRSHIP - (Specs.ROBOT_LENGTH + Specs.BUMPER_WIDTH)/2 - 10));
		addSequential(new RotateCommand(-60));
		addSequential(new WaitCommand(1));
		addSequential(new VisionRotate());
		addSequential(new UltrasonicDrive());
	}
}
