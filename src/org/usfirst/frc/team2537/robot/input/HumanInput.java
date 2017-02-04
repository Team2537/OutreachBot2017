package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class HumanInput {

	public static Joystick leftJoystick = new Joystick(Ports.LEFT_JOYSTICK);
	public static Joystick rightJoystick = new Joystick(Ports.RIGHT_JOYSTICK);
	 
	public static Joystick xboxController = new Joystick(Ports.XBOX);

	public static Button climberActivateButton = new JoystickButton(xboxController, Ports.CLIMBER_ACTIVATOR_BUTTON);
	public static Button climberKillSwitch = new JoystickButton(xboxController, Ports.CLIMBER_KILL_SWITCH);

	public static Button shooterOnButton = new JoystickButton(xboxController, Ports.SHOOTER_ON_BUTTON);
	public static Button shooterOffButton = new JoystickButton(xboxController, Ports.SHOOTER_OFF_BUTTON);

	public static void registerWhenPressedCommand(Button b, Command c) {
		b.whenPressed(c);
	}

}
