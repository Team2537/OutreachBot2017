package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

	public static final double FAST_SPEED = 1;
	public static final double SLOW_SPEED = 1;
	public static final int LEEWAY = 1;
	public static final int DISTANCE_TO_BOILER = 10;
	private Talon slowMotor = new Talon(Ports.SLOW_SHOOTER);
	private Talon fastMotor = new Talon(Ports.FAST_SHOOTER);

	public ShooterSubsystem() {

	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOnButton, new ShooterCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOffButton, new ShooterCommand(true));
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * sets the inner flywheel to its speed
	 */
	public void slowOn() {
		slowMotor.set(SLOW_SPEED);
	}
	
	/**
	 * sets the outer flywheel to its speed
	 */
	public void fastOn() {
		fastMotor.set(FAST_SPEED);
	}

	/**
	 * turns both flywheels off
	 */
	public void flyOff() {
		slowMotor.set(0);
		fastMotor.set(0);
	}
	
}
