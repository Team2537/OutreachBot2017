package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

	public static final double EXTERIOR_SPEED = 5000;
	public static final double INTERIOR_SPEED = 1;
	public static final int LEEWAY = 1;
	public static final int DISTANCE_TO_BOILER = 10;
	private static final int TICKS_PER_REVOLUTION = 80;
	private CANTalon interiorMotor = new CANTalon(Ports.INTERIOR_SHOOTER);
	private Talon exteriorMotor = new Talon(Ports.EXTERIOR_SHOOTER);
	private double p = 1, i = 0, d = 1;

	public ShooterSubsystem() {
		interiorMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		interiorMotor.configEncoderCodesPerRev(TICKS_PER_REVOLUTION);
		interiorMotor.setPID(p, i, d);
		interiorMotor.changeControlMode(TalonControlMode.Speed);
	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOnButton, new ShooterCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOffButton, new ShooterCommand(true));
	}

	@Override
	protected void initDefaultCommand() {

	}
	// This comment should be uncommented if using speed to determine when the
	// inner flywheel should be turned on
	// If using this code, the Talon fastMotor needs to be changed to CANTalon
	/*
	 * public double getFastVelocity(){ return fastMotor.getAnalogInVelocity();
	 * }
	 */

	/**
	 * sets the inner flywheel to its speed
	 */
	public void slowOn() {
		interiorMotor.set(INTERIOR_SPEED);
	}

	/**
	 * sets the outer flywheel to its speed
	 */
	public void fastOn() {
		exteriorMotor.set(EXTERIOR_SPEED);
	}

	public void activateSlowMotor() {
		interiorMotor.set(INTERIOR_SPEED);
	}

	/**
	 * turns both flywheels off
	 */
	public void flyOff() {
		interiorMotor.set(0);
		exteriorMotor.set(0);
	}

}
