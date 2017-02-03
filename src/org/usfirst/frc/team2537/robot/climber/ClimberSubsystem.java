package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

	private CANTalon climberMotor1 = new CANTalon(Ports.CLIMBER_MOTOR_ONE); // creates
	private CANTalon climberMotor2 = new CANTalon(Ports.CLIMBER_MOTOR_TWO);																	// Talon
																		// motor
																		// for
																		// climber
	protected static final double SPEED_MULTIPLIER = 1;
	private static final double DEADZONE_THRESHOLD = 0;
	
	public ClimberSubsystem() {
		ropeCheck.setAutomaticMode(true);
		climberMotor2.changeControlMode(TalonControlMode.Follower);
		climberMotor2.set(Ports.CLIMBER_MOTOR_ONE);
	}

//	public int getEncoderVelocity() {
//		return climberMotor.getEncVelocity();
//	}

	private DigitalInput climberPressureSensor = new DigitalInput(Ports.CLIMBER_PRESSURE_SENSOR);
	private Ultrasonic ropeCheck = new Ultrasonic(Ports.ULTRASONIC_TRIGGER, Ports.ULTRASONIC_ECHO);

	public boolean getClimberPressureSensor() {
		return climberPressureSensor.get();
	}

	public double getRopeCheck() {
		return ropeCheck.getRangeInches();
	}

	@Override
	public void initDefaultCommand() {
	}
	
	public double getClimber1Velocity(){
		return Robot.climberSys.climberMotor1.getAnalogInVelocity();
	}
	
	public double getClimber2Velocity(){
		return Robot.climberSys.climberMotor2.getAnalogInVelocity();
	}

	public void registerButtons() { // registers sbuttons
		HumanInput.registerWhenPressedCommand(HumanInput.climberActivateButton, new ClimberCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.climberKillSwitch, new ClimberKillCommand());

	}

	public void setClimberMotor(double speed) {
		climberMotor1.set(speed * SPEED_MULTIPLIER);
	}

	public double getXboxTrigger(int axis) {
		double rightXboxTrigger = HumanInput.xboxController.getRawAxis(axis);
		if (Math.abs(rightXboxTrigger) > DEADZONE_THRESHOLD) {
			return rightXboxTrigger;
		} else {
			return 0;
		}

	}

}
