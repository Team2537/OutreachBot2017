package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class HumanInput {

	// Register two joysticks
	public static Joystick leftJoystick = new Joystick(Ports.LEFT_JOYSTICK);
	public static Joystick rightJoystick = new Joystick(Ports.RIGHT_JOYSTICK);

	// Create climber buttons
	public static Button climberActivateButton = new JoystickButton(leftJoystick, Ports.CLIMBER_ACTIVATOR_BUTTON);
	public static Button climberKillSwitch = new JoystickButton(leftJoystick, Ports.CLIMBER_KILL_SWITCH);

	// Create shooter buttons
	public static Button shooterOnButton = new JoystickButton(leftJoystick, Ports.SHOOTER_ON_BUTTON);
	public static Button shooterOffButton = new JoystickButton(leftJoystick, Ports.SHOOTER_OFF_BUTTON);
	public static Button feedBallButton = new JoystickButton(leftJoystick, Ports.FEED_BALL_BUTTON);

	// Create camera buttons
	// Camera switcheroo switches overlay without switching sensors
	public static Button cameraSwitchButton = new JoystickButton(rightJoystick, Ports.CAMERA_SWITCH_BUTTON);
	public static Button cameraSwitcherooButton = new JoystickButton(rightJoystick, Ports.CAMERA_SWITCHEROO_BUTTON);

	/**
	 * Register button to command
	 * 
	 * @param b
	 *            button to register command to
	 * 
	 * @param c
	 *            command to register to button
	 */
	public static void registerWhenPressedCommand(Button b, Command c) {
		b.whenPressed(c);
	}

}
