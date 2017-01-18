package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.input.Sensors;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem {
	Counter counter = new Counter(Sensors.limitSwitch);

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
	
	public boolean isSwitchSet() {
		return counter.get() == 0; //check w/ electrical for wiring of limit switch
	}
}
