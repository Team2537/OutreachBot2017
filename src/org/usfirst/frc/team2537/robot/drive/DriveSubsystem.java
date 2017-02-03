package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private CANTalon backLeftMotor = new CANTalon(Ports.BACK_LEFT_MOTOR);
	private CANTalon backRightMotor = new CANTalon(Ports.BACK_RIGHT_MOTOR);
	private CANTalon frontLeftMotor = new CANTalon(Ports.FRONT_LEFT_MOTOR);
	private CANTalon frontRightMotor = new CANTalon(Ports.FRONT_RIGHT_MOTOR);
	
	private static final double DEADZONE_THRESHOLD = 0.1;
	protected static final double SPEED_MULTIPLIER = 1;

	@Override
	public void initDefaultCommand() {
		DriveCommand dc = new DriveCommand();
		this.setDefaultCommand(dc);
	}

	/**
	 * Set left motor to speed; inverted due to wiring
	 * 
	 * @param speed
	 * 
	 */
	public void setLeftMotors(double speed) {
		backLeftMotor.set(-speed * SPEED_MULTIPLIER);
		frontLeftMotor.set(-speed * SPEED_MULTIPLIER);
	}

	/**
	 * Set right motor to speed
	 * 
	 * @param speed
	 */
	public void setRightMotors(double speed) {
		backRightMotor.set(speed * SPEED_MULTIPLIER);
		frontRightMotor.set(-speed * SPEED_MULTIPLIER);
	}
	
	/**
	 * Set both motors to speed
	 * 
	 * @param speed
	 */
	public void setMotors(double speed) {
		setLeftMotors(speed);
		setRightMotors(speed);
	}

	/**
	 * Gets value based on direction left joy stick is pressed
	 * 
	 * @param axis
	 * @return
	 */
	public double getLeftJoystick(AxisType axis) {
		double leftJoystickValue = HumanInput.leftJoystick.getAxis(axis);
		if (Math.abs(leftJoystickValue) > DEADZONE_THRESHOLD)
			return leftJoystickValue;
		else
			return 0;
	}

	/**
	 * Gets value based on direction right joy stick is pressed
	 * 
	 * @param axis
	 * @return
	 */
	public double getRightJoystick(AxisType axis) {
		double rightJoystickValue = HumanInput.rightJoystick.getAxis(axis);
		
		if (Math.abs(rightJoystickValue) > DEADZONE_THRESHOLD) return rightJoystickValue;
		
		return 0;
	}

	public double getLeftEncoderCount() {
		return backLeftMotor.getEncPosition();
	}
	
	public double getLeftEncoderVelocity() {
		return backLeftMotor.getEncVelocity();
	}		
}
