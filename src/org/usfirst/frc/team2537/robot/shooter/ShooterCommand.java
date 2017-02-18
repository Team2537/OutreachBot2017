package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	private boolean shooterOff;
	private final static int TARGET_SPEED = 1280;

	/**
	 * constructor that requires Robot.shooterSys
	 * 
	 * @param shooterOff
	 *            boolean: true = shooter motors are off, false = shooter motors
	 *            are on
	 */
	public ShooterCommand(boolean shooterOff) {
		requires(Robot.shooterSys);
		this.shooterOff = shooterOff;
	}

	/**
	 * turns both flywheels on, the inner one WAIT_TIME seconds after the outer
	 * one, in order to allow the outer one to spin up, if the shooter on button
	 * is pressed, or turns both flywheels off if the shooter off buttons is
	 * pressed
	 */
	@Override
	protected void initialize() {
		Robot.shooterSys.setInteriorMotorMode();
		if (shooterOff) {
			Robot.shooterSys.turnInteriorMotorOff();
		} else {
			//Robot.shooterSys.fastOn();
			Robot.shooterSys.setSpeed(TARGET_SPEED);
		}
		Robot.shooterSys.enable();
		
		}
		
		
	

	@Override
	protected void execute() {
		
		
		if (Robot.shooterSys.getInteriorSpeed() >= TARGET_SPEED){
			Robot.shooterSys.setExteriorMotor(1);
		} else {
			Robot.shooterSys.setExteriorMotor(0);
	}
		/*
		 * else if (Robot.shooterSys.getFastVelocity > whateverSpeed) {
		 * Robot.shooterSys.acivateSlowMotor(); } //Above code also to be used
		 * if using an encoder
		 * 
		 * 
		 * /* if (shooterOn && Robot.shooterSys.getLimitSwitch()) { if
		 * (Robot.shooterSys.UltronRange() > ShooterSubsystem.DISTANCE_TO_BOILER
		 * - ShooterSubsystem.LEEWAY && Robot.shooterSys.UltronRange() <
		 * ShooterSubsystem.DISTANCE_TO_BOILER + ShooterSubsystem.LEEWAY) {
		 * Robot.shooterSys.FlyOn(); } }
		 */
//		System.out.println("The Pid Input is " + Robot.shooterSys.returnPIDInput());
		System.out.println(Robot.shooterSys.getInteriorSpeed());
		Robot.shooterSys.setSetpoint(TARGET_SPEED);
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooterSys.turnInteriorMotorOff();
	}
	

	/**
	 * turns flywheels off if interrupted
	 */
	@Override
	protected void interrupted() {
		Robot.shooterSys.flyOff();
	}

}
