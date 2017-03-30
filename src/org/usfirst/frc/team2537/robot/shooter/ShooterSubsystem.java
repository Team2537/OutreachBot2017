package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	private CANTalon shooterMotor = new CANTalon(Ports.SHOOTER_MOTOR);
	//private Talon shooterMotor = new Talon(Ports.SHOOTER_MOTOR);
	public ShooterSubsystem(){
	}
	@Override
	protected void initDefaultCommand() {
	}
	
	public void setShooterLocked() {
		shooterMotor.set(-1);
	}
	
	public void setShooterReleased(){
		shooterMotor.set(1);
	}
	
	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterBallRelease, new ShooterCommand());
	}

	

}
