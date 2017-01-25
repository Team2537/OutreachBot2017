package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

	private CANTalon climberMotor = new CANTalon(Ports.CLIMBER_MOTOR); //creates Talon motor for climber
	protected static final double SPEED_MULTIPLIER = 1;
	private static final double DEADZONE_THRESHOLD = 0.1;
	
	
	public int getEncoderVelocity(){
		return climberMotor.getEncVelocity();
	}
	
	DigitalInput climberPressureSensor = new DigitalInput(Ports.CLIMBER_PRESSURE_SENSOR);

	@Override
	public void initDefaultCommand() {
	}
	
	public void registerButtons(){ //registers buttons
		HumanInput.registerWhenPressedCommand(HumanInput.climberActivateButton, new ClimberCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.climberKillSwitch, new ClimberKillCommand());
		
	}
	
	public void setCLimberMotor(double speed){
		climberMotor.set(speed * SPEED_MULTIPLIER);
	}
	
	public double getRightXboxTrigger(int axis) {
		double rightXboxTrigger = HumanInput.xboxController.getRawAxis(axis);
		if (Math.abs(rightXboxTrigger) > DEADZONE_THRESHOLD){
			return rightXboxTrigger;
		} else {
			return 0;
		}
		
		
	}
		
	}

