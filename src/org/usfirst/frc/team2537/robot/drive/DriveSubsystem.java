package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private CANTalon talonFrontLeft;
	private CANTalon talonFrontRight;
	private Talon talonBackRight;
	private Talon talonBackLeft;

	private boolean drivingStraight;
	private boolean driveLowerSpeed;
	private boolean reversed;
	public static final double WHEEL_DIAMETER = 7.5; // Inches TODO: Magic
														// numbers
														// are fun
	public static final int PulsesPerRevolution = 1040; // for encoders

	// Atlas encoder code
	//public Encoder lencoder = new Encoder(Ports.LEFT_ENCODER_A, Ports.LEFT_ENCODER_B);
	//public Encoder rencoder = new Encoder(Ports.RIGHT_ENCODER_A, Ports.RIGHT_ENCODER_B);
	public Ultrasonic ultraSanic = new Ultrasonic(Ports.ULTRASONIC_TRIGGER, Ports.ULTRASONIC_ECHO); 
	public DigitalInput diosaur = new DigitalInput(Ports.INFRARED_ECHO);
	public DigitalOutput infrared = new DigitalOutput(Ports.INFRARED_TRIGGER);
	private AHRS ahrs;
	
	public static final double ticksPerInch = PulsesPerRevolution / (Math.PI * WHEEL_DIAMETER);

	public DriveSubsystem() {
		talonFrontLeft = new CANTalon(Ports.FRONT_LEFT_MOTOR);
		talonFrontRight = new CANTalon(Ports.FRONT_RIGHT_MOTOR);
		talonBackLeft = new Talon(Ports.BACK_LEFT_MOTOR);
		talonBackRight = new Talon(Ports.BACK_RIGHT_MOTOR);
		ultraSanic.setAutomaticMode(true);
		
		talonFrontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonFrontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonFrontLeft.configEncoderCodesPerRev(PulsesPerRevolution);
		talonFrontRight.configEncoderCodesPerRev(PulsesPerRevolution);

		try {
			ahrs = new AHRS(Port.kMXP);
		} catch (Exception ex) {
			//System.out.println(ex);
		}
		// SPEED MODE CODE
		// setDriveControlMode(TalonControlMode.Speed);
		drivingStraight = false;
		driveLowerSpeed = false;
		reversed = true;
		enableForwardSoftLimit(false);
		enableReverseSoftLimit(false);
	}

	/**
	 * Sets the output of a CANTalon
	 *
	 * @param outputValue
	 *            consult talon javadocs
	 * @param talon
	 *            The CANTalon to set the speed on
	 *
	 */

	private void set(double outputValue, CANTalon talon) {
		// SPEED MODE CODE
		// switch(talon.getControlMode()){
		// case Speed: talon.set(1500 * outputValue); break;
		// default: if(outputValue != 0)
		// System.out.println(talonFrontRight.getEncPosition());
		// talon.set(outputValue);
		// }
		talon.set(outputValue);
	}

	/**
	 * sets the left drive motors to a value corrects for inverted motor speeds.
	 *
	 * @param value
	 *            Voltage to set left motors
	 */
	public void setLeftDriveMotors(double value) {
		set(value, talonFrontLeft);
		talonBackLeft.set(value);
	}

	/**
	 * sets the right motors to a certain value
	 *
	 * @param value
	 *            Voltage to set right motors
	 */
	public void setRightDriveMotors(double value) {
		set(-value, talonFrontRight);
		talonBackRight.set(-value);
	}

	/**
	 * sets all drive motors to the same value corrects for inverted left motor
	 *
	 * @param value
	 *            Voltage to set all motors
	 */
	public void setDriveMotors(double value) {
		setDriveMotors(value, value);
	}

	/**
	 * sets the drive motors. Corrects for inverted left motor
	 *
	 * @param left
	 *            Voltage to set the left motor
	 * @param right
	 *            Voltage to set the right motor
	 */
	public void setDriveMotors(double left, double right) {
		setLeftDriveMotors(left);
		setRightDriveMotors(right);
	}

	/**
	 * returns the average between all the encoders
	 *
	 * @return double showing the average of all the encoders
	 */
	public double getEncoderAverage() {
		/*
		 * ATLAS drive straight: return ((-Robot.driveSys.lencoder.get() +
		 * Robot.driveSys.rencoder.get()) / 2.0) / 250.0 * 12.0 * Math.PI;
		 * coursecorrect: return ((Robot.driveSys.lencoder.get() -
		 * Robot.driveSys.rencoder.get()) / 2.0) / 250.0 * 6.0 * Math.PI;
		 */

		// Knightfall
		//System.out.println("Encoder Average:"
		//		+ (getLeftEncoders() + getRightEncoders()) / 2);
		
		return (getLeftEncoders() - getRightEncoders()) / 2;
	}
	

	/**
	 * Gets the average value of the left drive encoders compensates for
	 * negative left values
	 *
	 * @return the average of the front left and back left encoders in ticks
	 */
	public double getLeftEncoders() {
		// gets average encoder value, converts to revolutions,
		// converts to distance in inches using circumfrense of the wheel,
		// subtracts from original position to find distance
		// multiply by -1 because left motors are wired backwards
		// return -(talonBackLeft.getEncPosition() + talonFrontLeft
		// .getEncPosition())
		// / 2
		// / PulsesPerRevolution
		// * WHEEL_DIAMETER
		// * Math.PI - initialLeftEncoders;

		// ATLAS
		//System.out.println("lencoders:"+(-lencoder.get()));
// 		For use when the encoders are not in the DIO ports
  		return talonFrontLeft.getEncPosition();
		/*return -lencoder.get() / PulsesPerRevolution * WHEEL_DIAMETER * Math.PI
				- initialLeftEncoders;*/
	}

	/**
	 * Gets the average value of the right drive encoders
	 *
	 * @return the average of the front right and back right encoders in ticks
	 */
	public double getRightEncoders() {
		// gets average encoder value, converts to revolutions,
		// converts to distance in inches using circumfrense of the wheel,
		// subtracts from original position to find distance
//		return (talonBackRight.getEncPosition() + talonFrontRight
//				.getEncPosition())
//				/ 2
//				/ PulsesPerRevolution
//				* WHEEL_DIAMETER
//				* Math.PI - initialRightEncoders;
		
		//ATLAS
		//System.out.println("rencoders: "+rencoder.getRaw());
		// 		For use when the encoders are not in the DIO ports
		  		return talonFrontRight.getEncPosition();
		/*return rencoder.getRaw()/ PulsesPerRevolution * WHEEL_DIAMETER * Math.PI - initialRightEncoders;*/
	}

	/**
	 * Sets the encoder position to 0.
	 */
	public void resetEncoders() {
//		initialLeftEncoders += getLeftEncoders();
//		initialRightEncoders += getRightEncoders();

		// For use when there are not enough DIO Ports
		 talonFrontRight.setEncPosition(0);
		 talonFrontLeft.setEncPosition(0);

		 
//		 lencoder.reset();
//		 rencoder.reset();
	}

	private void enableForwardSoftLimit(boolean b) {
		talonFrontRight.enableForwardSoftLimit(b);
		talonFrontLeft.enableForwardSoftLimit(b);
	}

	private void enableReverseSoftLimit(boolean b) {
		talonFrontRight.enableReverseSoftLimit(b);
		talonFrontLeft.enableReverseSoftLimit(b);
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new DriveCommand());
	}

	protected void setDriveStraight(boolean b) {
		drivingStraight = b;
	}

	public boolean getDrivingStraight() {
		return drivingStraight;
	}

	protected void setLowSpeed(boolean b) {
		driveLowerSpeed = b;
	}

	protected boolean getLowSpeed() {
		return driveLowerSpeed;
	}

	protected boolean getReversed() {
		return reversed;
	}

	public AHRS getAhrs() {
		return ahrs;
	}

	protected void setReversed(boolean reversed) {
		this.reversed = reversed;
	}

	public void registerButtons() {
	}
	
	public boolean getBeamBreak(){
		return diosaur.get();
	}
	
	public void setMode(TalonControlMode tcm) {
		talonFrontLeft.changeControlMode(tcm);
		talonFrontRight.changeControlMode(tcm);
	}

	public void disableMotors() {
		talonFrontLeft.disable();
		talonFrontRight.disable();
		talonFrontLeft.set(0);
		talonFrontRight.set(0);
	}
	
	public void enableMotors() {
		talonFrontLeft.enable();
		talonFrontRight.enable();
	}
	
	public void enablePIDControl(double p, double i, double d) {
		talonFrontLeft.setPID(p, i, d);
		talonFrontRight.setPID(p, i, d);
		talonFrontLeft.enable();
		talonFrontRight.enable();
	}
	
	public void setBackMotors(double left, double right) {
		talonBackLeft.set(left);
		talonBackRight.set(right);
	}
	
	public void setFrontMotors(double left, double right) {
		talonFrontLeft.set(left);
		talonFrontRight.set(right);
	}

	public void setMotors(double speed) {
		setDriveMotors(speed);
	}

	public double getLeftJoystick() {
		return -HumanInput.leftJoystick.getAxis(AxisType.kY);
	}

	public double getRightJoystick() {
		return -HumanInput.rightJoystick.getAxis(AxisType.kY);
	}
	
}