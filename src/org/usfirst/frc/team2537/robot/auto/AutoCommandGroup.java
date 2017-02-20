package org.usfirst.frc.team2537.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandGroup extends CommandGroup {
	
	public AutoCommandGroup() {
		this.addSequential(new DriveForwardTimedAuto());
		this.addSequential(new DriveForwardTimedAuto2());
	}

}
