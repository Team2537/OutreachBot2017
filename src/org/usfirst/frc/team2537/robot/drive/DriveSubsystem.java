package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private CANTalon leftMotor = new CANTalon(Ports.LEFT_MOTOR);
	private CANTalon rightMotor = new CANTalon(Ports.RIGHT_MOTOR);
	private CANTalon frontLeftMotor = new CANTalon(Ports.FRONT_LEFT_MOTOR);
	private CANTalon frontRightMotor = new CANTalon(Ports.FRONT_RIGHT_MOTOR);
	private Ultrasonic ultron = new Ultrasonic(Ports.ULTRASONIC_TRIGGER, Ports.ULTRASONIC_ECHO);
	private static final double DEADZONE_THRESHOLD = 0.1;
	protected static final double SPEED_MULTIPLIER = 1;

	public DriveSubsystem() {
		ultron.setAutomaticMode(true);
	}

	DigitalInput limitSwitch = new DigitalInput(Ports.LIMIT_SWITCH_BUTTON);

	@Override
	public void initDefaultCommand() {
		DriveCommand dc = new DriveCommand();
		this.setDefaultCommand(dc);
	}

 	public void registerButtons() {
	} 

	/**
	 * Set left motor to speed
	 * 
	 * @param speed
	 * 
	 */
	public void setLeftMotor(double speed) {
		leftMotor.set(-speed * SPEED_MULTIPLIER);
	}

	
	/**
	 * Set right motor to speed
	 * 
	 * @param speed
	 */
	public void setRightMotor(double speed) {
		rightMotor.set(speed * SPEED_MULTIPLIER);
	}
	/**
	 * Set front left motor to speed
	 * 
	 * @param speed
	 * 
	 */
	public void setfrontLeftMotor(double speed) {
		frontLeftMotor.set(-speed * SPEED_MULTIPLIER);
	}
	/**
	 * Set front left motor to speed
	 * 
	 * @param speed
	 * 
	 */
	public void setfrontRightMotor(double speed) {
	frontRightMotor.set(speed * SPEED_MULTIPLIER);
	}
	
	/**
	 * Gets value based on direction left joy stick is pressed
	 * 
	 * @param axis
	 * @return
	 */
	public double getLeftJoystick(int axis) {
		double leftJoystickValue = HumanInput.xboxController.getRawAxis(axis);
		if (Math.abs(leftJoystickValue) > DEADZONE_THRESHOLD)
			return leftJoystickValue;
		else
			return 0;
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
		if (Math.abs(rightJoystickValue) > DEADZONE_THRESHOLD)
			return rightJoystickValue;
		else
			return 0;
	}
	
	public double getUltron() {
		return ultron.getRangeInches();
	}
}
