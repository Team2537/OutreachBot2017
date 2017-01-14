package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

	private Talon climberMotor = new Talon(Ports.CLIMBER_MOTOR); //creates Talon motor for climber
	protected static final double SPEED_MULTIPLIER = 1;
	

	

	@Override
	public void initDefaultCommand() {
		ClimberCommand dc = new ClimberCommand();
		this.setDefaultCommand(dc);
	}
	
	public void registerButtons(){ //registers buttons
		HumanInput.registerWhenPressedCommand(HumanInput.climberActivateButton, new ClimberCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.climberKillSwitch, new ClimberKillCommand());
		
	}
	
	public void setCLimberMotor(double speed){
		climberMotor.set(speed * SPEED_MULTIPLIER);
	}
	}

