package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

	public static final double FAST_SPEED = 1;
	public static final double SLOW_SPEED = .5;
	public static final int LEEWAY = 1;
	public static final int DISTANCE_TO_BOILER = 10;
	private DigitalInput limitSwitch = new DigitalInput(Ports.LIMITSWITCH);
	private Ultrasonic ultrasonicToBoiler = new Ultrasonic(Ports.ULTRASONIC_INPUT, Ports.ULTRASONIC_OUTPUT);
	private CANTalon slowMotor = new CANTalon(Ports.SLOW_SHOOTER);
	private CANTalon fastMotor = new CANTalon(Ports.FAST_SHOOTER);

	/**
	 * Constructor that sets up Ultrasonic by setting Automatic Mode to true
	 */
	public ShooterSubsystem() {
		// ultrasonicToBoiler.setAutomaticMode(true);
	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOnButton, new ShooterCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOffButton, new ShooterOffCommand());
	}

	@Override
	protected void initDefaultCommand() {

	}

	public boolean getLimitSwitch() {
		return limitSwitch.get();
	}

	/**
	 * Sets all 2 flywheels to speed SPEED
	 */
	public void FlyOn() {
		slowMotor.set(SLOW_SPEED);
		fastMotor.set(FAST_SPEED);
	}

	/**
	 * Sets all 2 Flywheels to speed 0
	 */
	public void FlyOff() {
		slowMotor.set(0);
		fastMotor.set(0);
	}

	/**
	 * 
	 * @return the distance of the ultrasonic on the robot to the boiler
	 */
	public double UltronRange() {
		return ultrasonicToBoiler.getRangeInches();
	}

}
