package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem {
	private CANTalon gearMotor = new CANTalon(Ports.GEAR_MOTOR);
	private DigitalInput limitSwitchDown = new DigitalInput(Ports.LIMIT_SWITCH_GEAR_DOWN);
	private DigitalInput limitSwitchUp = new DigitalInput(Ports.LIMIT_SWITCH_GEAR_UP);


	@Override
	public void initDefaultCommand() {
	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.gearDownButton, new GearCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.gearUpButton, new GearCommand(true));
	}

	/**
	 * sets speed of gear motor
	 * 
	 * @param speed
	 *            double, speed of gear motor
	 */
	public void setGearMotor(double speed) {
		gearMotor.set(speed);
	}

	/**
	 * sets speed of gear motor to 0 if the method is ended or interrupted
	 */
	public void endOrInterrupted() {
		gearMotor.set(0);
	}
	
	public boolean getLimitSwitch(boolean up) {
		if (up) {
			return limitSwitchUp.get();
		} else {
			return limitSwitchDown.get();
		}
	}
}
