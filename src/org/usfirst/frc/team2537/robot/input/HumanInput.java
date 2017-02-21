package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class HumanInput {

	public static Joystick leftJoystick = new Joystick(Ports.LEFT_JOYSTICK);
	public static Joystick rightJoystick = new Joystick(Ports.RIGHT_JOYSTICK);
	
	public static Button climberActivateButton = new JoystickButton(leftJoystick, Ports.CLIMBER_ACTIVATOR_BUTTON);
	public static Button climberKillSwitch = new JoystickButton(leftJoystick, Ports.CLIMBER_KILL_SWITCH);

	public static Button shooterOnButton = new JoystickButton(leftJoystick, Ports.SHOOTER_ON_BUTTON);
	public static Button shooterOffButton = new JoystickButton(leftJoystick, Ports.SHOOTER_OFF_BUTTON);
	
	public static Button cameraSwitchButton = new JoystickButton(rightJoystick, Ports.CAMERA_SWITCH_BUTTON);
	public static Button cameraSwitcherooButton = new JoystickButton(rightJoystick, Ports.CAMERA_SWITCHEROO_BUTTON);

	public static void registerWhenPressedCommand(Button b, Command c) {
		b.whenPressed(c);
	}

}
