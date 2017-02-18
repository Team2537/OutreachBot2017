package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class ShooterSubsystem extends PIDSubsystem {

	public static final double EXTERIOR_SPEED = 5000;
	public static final double INTERIOR_SPEED = 1;
	public static final int LEEWAY = 1;
	public static final int DISTANCE_TO_BOILER = 10;
	private static final int TICKS_PER_REVOLUTION = 80;
	private CANTalon exteriorFlywheel = new CANTalon(Ports.INTERIOR_SHOOTER);
	private CANTalon interiorFlywheel = new CANTalon(Ports.EXTERIOR_SHOOTER);
	private static double p = 4.2, i = 0, d = 0.3;
	private boolean DEBUG = true;
	private static final double UNITS_PER_100MS_TO_RPM = 100.0 / 4096 * 1000 * 60;
	private static final double ERROR_TOLERANCE = 5;
	public static final int SPEED_MULTIPLIER = 1;

	public ShooterSubsystem() {
		super("Shooter", p, i, d);
		setAbsoluteTolerance(50);
		getPIDController().setContinuous();
		enable();
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
		exteriorFlywheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		exteriorFlywheel.configEncoderCodesPerRev(TICKS_PER_REVOLUTION);
//		interiorMotor.changeControlMode(TalonControlMode.Speed);
		
	}

	

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOnButton, new ShooterCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOffButton, new ShooterKillCommand());
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

	// Set and get speed
	/**
	 * Set the speed of the motors.
	 *
	 * @param speed
	 *            in RPM.
	 */

	public void setExteriorMotor(double speed) {
		interiorFlywheel.set(speed * SPEED_MULTIPLIER);
	}
	

	public void setInteriorMotor(double speed) {
		exteriorFlywheel.set(speed * SPEED_MULTIPLIER);
	}

	public void setSpeed(double speed) {
		exteriorFlywheel.set(speed);
		if (DEBUG) {
			System.out.println(exteriorFlywheel.getControlMode());
			System.out.println("Left Flywheel Speed: " + exteriorFlywheel.getSpeed());
		}
	}

	public void turnInteriorMotorOff() {
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
		this.setSetpoint(0);
		exteriorFlywheel.set(0);
	}

	public void setInteriorMotorMode() {
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
	}

	public double getInteriorSpeed() {
		return exteriorFlywheel.getSpeed();
	}

	// LEFT
	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */
	public double getLeftSpeed() {
		if (DEBUG) {
			System.out.println("Interior Flywheel Speed: " + exteriorFlywheel.getSpeed());
		}
		return exteriorFlywheel.getSpeed();
	}

	/**
	 * Get the error of the left flywheel. This is the distance from the wanted
	 * speed and the current speed.
	 * 
	 * @return Difference between current speed and wanted speed. In RPM
	 */
	public double getLeftError() {
		// We want to change this from native units (units per 100ms) to RPM.
		// return interiorMotor.getError()/100/4096*1000*60;
		/*
		 * Here is how I came to this conversion factor.
		 * 
		 */
		// Simplified for speed.
		return exteriorFlywheel.getError() * UNITS_PER_100MS_TO_RPM;
	}

	// RIGHT
	/**
	 * Get the current speed of the left flywheel.
	 * 
	 * @return flywheel speed in RPM
	 */

	/**
	 * Get the error of the left flywheel. This is the distance from the wanted
	 * speed and the current speed.
	 * 
	 * @return Difference between current speed and wanted speed in RPM.
	 */

	/**
	 * Check if a ball is in the shooter.
	 * 
	 * @return if the proximity sensor is returning true to seeing something.
	 */

	public boolean isAtSpeed(double speed) {

		if (DEBUG) {
			System.out.print("Flywheel ");
			System.out.print("L Speed: " + Robot.shooterSys.getLeftSpeed());
			System.out.print("L Error: " + Robot.shooterSys.getLeftError());
		}

		return (getLeftError() <= ERROR_TOLERANCE);

	}

	// This is routenly called regardless of motor activation, should use this
	// to
	// put debug code.

	public void slowOn() {
		exteriorFlywheel.set(INTERIOR_SPEED);
	}

	/**
	 * sets the outer flywheel to its speed
	 */
	public void fastOn() {
		interiorFlywheel.set(EXTERIOR_SPEED);
	}

	public void activateSlowMotor() {
		exteriorFlywheel.set(INTERIOR_SPEED);
	}

	/**
	 * turns both flywheels off
	 */
	public void flyOff() {
		exteriorFlywheel.set(0);
		interiorFlywheel.set(0);
	}

	@Override
	protected double returnPIDInput() {
		return exteriorFlywheel.getSpeed();
	}

	@Override
	protected void usePIDOutput(double output) {
		exteriorFlywheel.set(output);

	}

}
