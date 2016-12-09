package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	CANTalon leftMotor = new CANTalon(Ports.LEFT_MOTOR);
	CANTalon rightMotor = new CANTalon(Ports.RIGHT_MOTOR);
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void moveLeftMotor(double speed){
		leftMotor.set(speed);
	}
	
	public void moveRightMotor(double speed){
		rightMotor.set(speed);
	}
	
	
}
