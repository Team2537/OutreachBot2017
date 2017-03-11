package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;
import org.usfirst.frc.team2537.robot.auto.UltrasonicDrive;
import org.usfirst.frc.team2537.robot.auto.VisionRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class MiddleGear extends CommandGroup {
	public MiddleGear() {
		addSequential(new DriveStraightCommand(45.65)); // Optimal distance to move before starting vision
		addSequential(new WaitCommand(1)); // Give robot time to settle before starting vision
		addSequential(new VisionRotate()); // Start vision
		addSequential(new UltrasonicDrive()); // Drive until ultrasonic is within range
	}
}
