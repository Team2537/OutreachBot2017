package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	private Talon BackLeftFly = new Talon(Ports.BLEFT_FLY);
	private Talon FrontLeftFly = new Talon(Ports.FLEFT_FLY);
	private Talon BackRightFly = new Talon(Ports.BRIGHT_FLY);
	private Talon FrontRightFly = new Talon(Ports.FRIGHT_FLY);
	public static final int SPEED = 1;
	public static final int LEEWAY = 1;
	public static final int DISTANCE_TO_BOILER = 10;
	private static Ultrasonic ultrasonic_to_boiler; 
	/**
	 * Constructor that sets up Ultrasonic by setting Automatic Mode to true
	 */
	public ShooterSubsystem() {
		ultrasonic_to_boiler = new Ultrasonic(Ports.ULTRASONIC_INPUT, Ports.ULTRASONIC_OUTPUT); //TODO Change ports to variables in Ports.jav
		ultrasonic_to_boiler.setAutomaticMode(true);
	}

	@Override
	protected void initDefaultCommand() {


	}
	/**
	 * Sets Talon @param t to speed @param speed
	 */
	public void setFlySpeed(Talon t,double speed){
		t.set(speed);
		
	}
	/**
	 * Sets all four flywheels to speed SPEED
	 */
	public void FlyOn(){
		
		setFlySpeed(BackLeftFly, SPEED);
		setFlySpeed(FrontLeftFly, SPEED);
		setFlySpeed(BackRightFly, SPEED);
		setFlySpeed(FrontRightFly, SPEED);
	}
	/**
	 * Sets All four Flywheels to speed 0
	 */
	public void FlyOff(){
		setFlySpeed(BackLeftFly, 0);
		setFlySpeed(FrontLeftFly,0);
		setFlySpeed(BackRightFly,0);
		setFlySpeed(FrontRightFly,0);
	}
	/**
	 * 
	 * @return the distance of the ultrasonic on the robot to the boiler
	 */
	public double UltronRange(){
		return  ultrasonic_to_boiler.getRangeInches();
	}
	/**
	 * Registers Buttons
	 */
	public void registerButtons(){
		HumanInput.registerWhenPressedCommand(HumanInput.shooterFire, new ShooterCommand(true));
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOff, new ShooterCommand(false));
	}
}
