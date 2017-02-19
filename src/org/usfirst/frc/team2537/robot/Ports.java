package org.usfirst.frc.team2537.robot;

public class Ports {
	//TODO: Change ports to line up with actual ports
	
	// Drive Talon Ports
	public static final int FRONT_LEFT_MOTOR = 1, FRONT_RIGHT_MOTOR = 2, BACK_LEFT_MOTOR = 0, BACK_RIGHT_MOTOR = 2;

	// Mechanism Talon Ports
	public static final int CLIMBER_MOTOR_ONE = 5;

	public static final int SLOW_SHOOTER = 3, FAST_SHOOTER = 2;

	// Joystick and XBox Ports
	public static final int LEFT_JOYSTICK = 0, RIGHT_JOYSTICK = 1, XBOX = 2;

	// Joystick Buttons
	public static final int CLIMBER_ACTIVATOR_BUTTON = 3, CLIMBER_KILL_SWITCH = 2;

	public static final int CAMERA_SWITCH_BUTTON = 5;

	public static final int SHOOTER_ON_BUTTON = 1, SHOOTER_OFF_BUTTON = 4;

	// Sensor ports
	public static final int ULTRASONIC_TRIGGER = 1, ULTRASONIC_ECHO = 2;

	// PDP ports
	public static final int CLIMBER_MOTOR_PDP_CHANNEL = 14;
}