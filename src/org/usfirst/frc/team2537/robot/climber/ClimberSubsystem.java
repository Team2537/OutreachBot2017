package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

	private CANTalon climberMotor1 = new CANTalon(Ports.CLIMBER_MOTOR_ONE); // creates motor for climber
	protected static final double SPEED_MULTIPLIER = 1;
	private static final double DEADZONE_THRESHOLD = 0;

	public ClimberSubsystem() {

	}

	@Override
	public void initDefaultCommand() {
	}

	public void registerButtons() { // registers buttons
		HumanInput.registerWhenPressedCommand(HumanInput.climberActivateButton, new ClimberCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.climberKillSwitch, new ClimberKillCommand());

	}

		/**
		 * sets climber motor
		 * @param speed
		 */
	public void setClimberMotor(double speed) {
		climberMotor1.set(speed * SPEED_MULTIPLIER);
	}

	/**
	 * gets the right Xbox Trigger if using xbox
	 * @param axis
	 * @return
	 */
	public double getXboxTrigger(int axis) {
		double rightXboxTrigger = HumanInput.xboxController.getRawAxis(axis);
		if (Math.abs(rightXboxTrigger) > DEADZONE_THRESHOLD) {
			return rightXboxTrigger;
		} else {
			return 0;
		}

	}

	/**
	 * gets the z axis on the joystick if using only joysticks
	 * @param axis
	 * @return
	 */
	public double getZAxisJoystick(int axis) {
		double joystickZAxis = HumanInput.leftJoystick.getRawAxis(axis);
		if (Math.abs(joystickZAxis) > DEADZONE_THRESHOLD) {
			return joystickZAxis;
		} else {
			return 0;
		}
	}
}
