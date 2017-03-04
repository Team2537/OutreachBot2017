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
		BACK_LEFT_MOTOR_PORT = 2, BACK_RIGHT_MOTOR_PORT = 1,

		/////// PWM Output ///////


		/////// DIO Input (Sensors) ///////
		ULTRASONIC_TRIGGER = 3, ULTRASONIC_ECHO = 8,
	
		//LEFT_ENCODER_A = 4, LEFT_ENCODER_B = 5,
		//RIGHT_ENCODER_A = 6, RIGHT_ENCODER_B = 7,
		
		RASP_PI = 9,
		
		INFRARED_TRIGGER = 1, INFRARED_ECHO =2;
		
}