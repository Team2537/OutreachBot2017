package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class ShooterSubsystem extends PIDSubsystem {

	public static final int LEEWAY = 1;
	private static final int TICKS_PER_REVOLUTION = 80;
	private CANTalon exteriorFlywheel = new CANTalon(Ports.EXTERIOR_SHOOTER); // creates motors
	private CANTalon interiorFlywheel = new CANTalon(Ports.INTERIOR_SHOOTER);
	private static double p = 4.2, i = 0, d = 0.3; // sets pid values
	public static final int SPEED_MULTIPLIER = 1;

	public ShooterSubsystem() {
		/**
		 * creates PID subsystem and enables it
		 */
		super("Shooter", p, i, d);
		setAbsoluteTolerance(50);
		getPIDController().setContinuous();
		enable();
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
		exteriorFlywheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		exteriorFlywheel.configEncoderCodesPerRev(TICKS_PER_REVOLUTION);
		// exteriorFlywheel.changeControlMode(TalonControlMode.Speed);

	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOnButton, new ShooterCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOffButton, new ShooterKillCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.feedBallButton, new FeedOneBallCommand());
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * sets interior motor
	 * 
	 * @param speed
	 */
	public void setInteriorMotor(double speed) {
		interiorFlywheel.set(speed * SPEED_MULTIPLIER);
	}

	/**
	 * sets exterior motor
	 */
	public void setExteriorMotor(double speed) {
		exteriorFlywheel.set(speed * SPEED_MULTIPLIER);
	}

	/**
	 * turns exterior motor off
	 */
	public void turnExteriorMotorOff() {
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
		this.setSetpoint(0);
		exteriorFlywheel.set(0);
	}

	/**
	 * changes the mode of the exterior motor
	 */
	public void setExteriorMotorMode() {
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
	}

	/**
	 * gets speed of the exterior motor from the CIMcoder
	 * 
	 * @return
	 */
	public double getExteriorSpeed() {
		return exteriorFlywheel.getSpeed();
	}

	/**
	 * gets the error between the actual speed and target speed of the motor
	 * 
	 * @return
	 */
	public double getExteriorError() {
		return exteriorFlywheel.getError();
	}

	/**
	 * Returns the current speed for use of the PID loop
	 */
	@Override
	protected double returnPIDInput() {
		return exteriorFlywheel.getSpeed();
	}

	/**
	 * Sets the exterior flywheel to the number the PID loop outputs
	 */
	@Override
	protected void usePIDOutput(double output) {
		exteriorFlywheel.set(output);

	}

}
