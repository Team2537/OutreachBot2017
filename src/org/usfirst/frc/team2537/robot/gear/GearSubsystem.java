package org.usfirst.frc.team2537.robot.gear;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem {
	private Servo gearServo = new Servo(Ports.GEAR_SERVO);
	private DigitalInput limitSwitchDown = new DigitalInput(Ports.LIMIT_SWITCH_GEAR_DOWN);
	private DigitalInput limitSwitchUp = new DigitalInput(Ports.LIMIT_SWITCH_GEAR_UP);
	int upAngle = 50;
	int downAngle = 90;

	@Override
	public void initDefaultCommand() {
	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.gearDownButton, new GearCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.gearUpButton, new GearCommand(true));
	}
	
	public void setGearServo(boolean up) {
		if (up) {
			gearServo.setAngle(upAngle);
			System.out.println("Gear Up");
		} else {
			gearServo.setAngle(downAngle);
			System.out.println("Gear Down");
		}
	}

	public boolean getLimitSwitch(boolean up) {
		if (up) {
			return limitSwitchUp.get();
		} else {
			return limitSwitchDown.get();
		}
	}
}
