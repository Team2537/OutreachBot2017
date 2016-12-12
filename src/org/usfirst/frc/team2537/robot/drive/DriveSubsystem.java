package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private CANTalon leftMotor = new CANTalon(Ports.LEFT_MOTOR);
	private CANTalon rightMotor = new CANTalon(Ports.RIGHT_MOTOR);
	private static final double DEADZONE_THRESHOLD = 0.1;
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void setLeftMotor(double speed){
		leftMotor.set(speed);
	}
	
	public void setRightMotor(double speed){
		rightMotor.set(speed);
	}
	
	public double getLeftJoystick(){
		double leftJoystickValue = HumanInput.leftJoystick.getAxis(AxisType.kY);
		if(Math.abs(leftJoystickValue) > DEADZONE_THRESHOLD) return leftJoystickValue;
		else return 0;
	}
	
	public double getRightJoystick(){
		double rightJoystickValue = HumanInput.rightJoystick.getAxis(AxisType.kY);
		if(Math.abs(rightJoystickValue) > DEADZONE_THRESHOLD) return rightJoystickValue;
		else return 0;
	}
	
	
}
