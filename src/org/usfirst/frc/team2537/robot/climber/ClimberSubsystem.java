package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

	private Talon climberMotor1 = new Talon(Ports.CLIMBER_MOTOR_ONE); // Creates Talon Motor
	protected static final double SPEED_MULTIPLIER = 1;
	private static final double DEADZONE_THRESHOLD = 0;

	public ClimberSubsystem() {

	}

	@Override
	public void initDefaultCommand() {
	}

	public void registerButtons() { 
		HumanInput.registerWhenPressedCommand(HumanInput.climberActivateButton, new ClimberCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.climberKillSwitch, new ClimberKillCommand());

	}

	/**
	 * Sets climber motor
	 */
	public void setClimberMotor(double speed) {
		climberMotor1.set(speed * SPEED_MULTIPLIER);

	}

	/**
	 * Gets Xbox trigger amount
	 */
	public double getXboxTrigger(int axis) {
		double rightXboxTrigger = HumanInput.xboxController.getRawAxis(axis);
		if (Math.abs(rightXboxTrigger) > DEADZONE_THRESHOLD) {
			return rightXboxTrigger;
		} else {
			return 0;
		}

	}

}
