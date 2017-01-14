package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Ports;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem {
	
	private CANTalon gearMotor = new CANTalon(Ports.GEAR_MOTOR);

	@Override
	public void initDefaultCommand() {
		GearCommand gc = new GearCommand();
		this.setDefaultCommand(gc);
	}
	
	public void gearDown() {
		gearMotor.set(.1);
	}
}
