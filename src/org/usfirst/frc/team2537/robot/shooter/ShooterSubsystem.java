package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	



	public static final int LEEWAY = 1;
	private static final int TICKS_PER_REVOLUTION = 80;
	private CANTalon exteriorFlywheel = new CANTalon(Ports.EXTERIOR_SHOOTER); // creates
																				// motors
	private Servo shooterServo = new Servo(Ports.SHOOTER_SERVO);
	public static final int SPEED_MULTIPLIER = 1;

	public ShooterSubsystem() {
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
		exteriorFlywheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		exteriorFlywheel.configEncoderCodesPerRev(TICKS_PER_REVOLUTION);

	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOnButton, new ShooterCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOffButton, new ShooterKillCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.feedBallButton, new FeedOneBallCommand(false));
	}
	

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * sets exterior motor
	 */
	public void setExteriorMotor(double speed) {
		exteriorFlywheel.set(speed * SPEED_MULTIPLIER);
	}

	/**
	 * turns exterior motor off
	 */
	public void turnExteriorMotorOff() {
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
		exteriorFlywheel.set(0);
	}

	/**
	 * changes the mode of the exterior motor
	 */
	public void setExteriorMotorMode() {
		exteriorFlywheel.changeControlMode(TalonControlMode.PercentVbus);
	}

	/**
	 * gets speed of the exterior motor from the CIMcoder
	 * 
	 * @return
	 */
	public double getExteriorSpeed() {
		return exteriorFlywheel.getSpeed();
	}

	public void setShooterServo(double speed) {
		shooterServo.set(speed);

	}
	
	public void setShooterServoAngle(double angle) {
		shooterServo.setAngle(angle);
	}


}
