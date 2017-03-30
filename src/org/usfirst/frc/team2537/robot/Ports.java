package org.usfirst.frc.team2537.robot;

public class Ports {
	// TODO: Change ports to line up with actual ports

	// Drive Talon Ports
	public static final int FRONT_LEFT_MOTOR = 1, FRONT_RIGHT_MOTOR = 2, BACK_LEFT_MOTOR = 3, BACK_RIGHT_MOTOR = 2;
	// Red bot ports: 2, 1. Blue bot ports: 3, 2

	// Mechanism Talon Ports
	public static final int CLIMBER_MOTOR_ONE = 5, CLIMBER_MOTOR_TWO = 6;

	public static final int SHOOTER_MOTOR = 1;

	// Joystick and XBox Ports
	public static final int LEFT_JOYSTICK = 0, RIGHT_JOYSTICK = 1;

	// Joystick Buttons
	public static final int CLIMBER_ACTIVATOR_BUTTON = 3, CLIMBER_SLOW_ACTIVATOR_BUTTON = 4, CLIMBER_KILL_SWITCH = 2;

	public static final int CAMERA_SWITCH_BUTTON = 3, CAMERA_SWITCHEROO_BUTTON = 7;

	public static final int SHOOTER_BALL_RELEASE = 1;
	

	// Sensor ports
	public static final int LEFT_ENCODER_A = 4, LEFT_ENCODER_B = 5;
	public static final int RIGHT_ENCODER_A = 6, RIGHT_ENCODER_B = 7;
	
	public static final int RASP_PI = 9;
	
	public static final int INFRARED_TRIGGER = 1, INFRARED_ECHO =2;
	
	public static final int ULTRASONIC_TRIGGER = 3, ULTRASONIC_ECHO = 8;
	
	// PDP ports
	public static final int CLIMBER_MOTOR_PDP_CHANNEL = 14;
}
