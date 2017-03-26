package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveStraight extends CommandGroup {
	public DriveStraight() {
		addSequential(new DriveStraightCommand(33));
		addSequential(new WaitCommand(.5));
		addSequential(new DriveStraightCommand(33));
		addSequential(new WaitCommand(.5));
		addSequential(new DriveStraightCommand(33));
	}

}
