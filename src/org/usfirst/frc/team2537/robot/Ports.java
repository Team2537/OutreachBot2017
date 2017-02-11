package org.usfirst.frc.team2537.robot;

/**
 * List of port numbers that we will use on the robot. Change as necessary in
 * accordance with the IO sheet.
 * 
 * @author Alex Taber
 */
public final class Ports {
	public static final int

		/////// CAN Talons ///////
		// Drive
		FRONT_LEFT_MOTOR_PORT = 1, FRONT_RIGHT_MOTOR_PORT = 2,
		BACK_LEFT_MOTOR_PORT = 3, BACK_RIGHT_MOTOR_PORT = 4,

		/////// PWM Output ///////


		/////// DIO Input (Sensors) ///////
		DRIVE_ULTRASONIC_INPUT = 6,
		DRIVE_ULTRASONIC_ECHO = 7,
		ENCODER_LEFT_A = 4,
		ENCODER_LEFT_B = 5,
		ENCODER_RIGHT_A = 1,
		ENCODER_RIGHT_B = 2,
		RASP_PI = 9;
	
}
