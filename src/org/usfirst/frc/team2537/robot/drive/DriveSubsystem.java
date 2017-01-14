package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.climber.ClimberCommand;
import org.usfirst.frc.team2537.robot.climber.ClimberKillCommand;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private Talon leftMotor = new Talon(Ports.LEFT_MOTOR);
	private Talon rightMotor = new Talon(Ports.RIGHT_MOTOR);
	private static final double DEADZONE_THRESHOLD = 0.1;
	protected static final double SPEED_MULTIPLIER = 1;

	public DriveSubsystem() {

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
}
