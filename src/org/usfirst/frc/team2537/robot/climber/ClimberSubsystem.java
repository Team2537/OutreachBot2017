package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

	private Talon climberMotor1 = new Talon(Ports.CLIMBER_MOTOR_ONE); // creates motor for climber
	protected static final double SPEED_MULTIPLIER = 1;

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
		 * sets climber motor
		 * @param speed
		 */
	public void setClimberMotor(double speed) {
		climberMotor1.set(speed * SPEED_MULTIPLIER);

	}
}
