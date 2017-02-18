package org.usfirst.frc.team2537.robot;

public class Ports {
	// TODO: Change ports to line up with actual ports

	// Drive Talon Ports
	public static final int FRONT_LEFT_MOTOR = 60, FRONT_RIGHT_MOTOR = 59, BACK_LEFT_MOTOR = 3, BACK_RIGHT_MOTOR = 61;

	// Mechanism Talon Ports
	public static final int CLIMBER_MOTOR_ONE = 0, CLIMBER_MOTOR_TWO = 62;

	public static final int INTERIOR_SHOOTER = 1, EXTERIOR_SHOOTER = 2; // originally 3 and 2

	// Joystick and XBox Ports
	public static final int LEFT_JOYSTICK = 0, RIGHT_JOYSTICK = 1, XBOX = 2;

	// XBox Buttons
	public static final int CLIMBER_ACTIVATOR_BUTTON = 2, CLIMBER_KILL_SWITCH = 3;

	public static final int SHOOTER_ON_BUTTON = 1, SHOOTER_OFF_BUTTON = 4;

	public static final int CAMERA_SWITCH_BUTTON = 5; // temp

	// Sensor ports
	public static final int ULTRASONIC_TRIGGER = 3, ULTRASONIC_ECHO = 4;

	public static final int CLIMBER_PRESSURE_SENSOR = 0;

}
