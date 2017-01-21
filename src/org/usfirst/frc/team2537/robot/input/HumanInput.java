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
	
	public static Button gearUpButton = new JoystickButton(xboxController, Ports.GEAR_UP_BUTTON);
	public static Button gearDownButton = new JoystickButton(xboxController, Ports.GEAR_DOWN_BUTTON);

	public static void registerWhenPressedCommand(Button b, Command c){
		b.whenPressed(c);
	}
	
}
