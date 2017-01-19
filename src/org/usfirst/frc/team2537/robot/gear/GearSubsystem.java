package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem {
	private CANTalon gearMotor = new CANTalon(Ports.GEAR_MOTOR);
	//private DigitalInput limitSwitch = new DigitalInput(Ports.LIMIT_SWITCH);
	//Counter counter = new Counter(limitSwitch);

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

//	/**
//	 * checks if limit switch is pressed
//	 * 
//	 * @return true if gear set
//	 */
//	public boolean isSwitchSet() {
//		return counter.get() == 0; // check w/ electrical for wiring of limit
//									// switch
//	}
}
