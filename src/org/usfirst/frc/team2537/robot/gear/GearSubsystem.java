package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem {

	private CANTalon gearMotor = new CANTalon(Ports.GEAR_MOTOR);

	@Override
	public void initDefaultCommand() {
	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.gearDownButton, new GearCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.gearUpButton, new GearCommand(true));
	}

	public void setGearMotor(double speed) {
		gearMotor.set(speed);
	}
	
	public void endOrInterrupted() {
		gearMotor.set(0);
	}
}
