package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.vision.DutyCycleOutput;
import org.usfirst.frc.team2537.robot.vision.DutyCycleSource;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class AutoRotateCameraCommand extends Command {
	private AHRS ahrs;

	// values are from 0 to 1 (0 is left of camera, 1 is right)
	private static final double DESTINATION_DUTY = 0.5;
	private static final double DEFAULT_SPEED = 0.6;
	private static final int SLOWDOWN_POWER = 16;
	private static final double TOLERANCE = 0.05;
	/* P, I, D, MIN SPEED */
	private static final double[] CARPET_PID = new double[]{0.1, 0.2, 0.1, 0.15};
	private static final double[] SMOOTH_CONCRETE_PID = new double[]{0.2, 0, 0, 0.3};
	private static final double[] CURRENT_SURFACE = CARPET_PID;
	
	private static final double NO_TARGET_DUTY = 0.99; // duty output by the Pi
														// when no target is
														// visible
	private double speed;
	private Side lastSideTurned;
	private PIDController pidControl;
	private DutyCycleOutput pidOut;

	/**
	 * spins destinationAngle degrees
	 * 
	 * @param destinationDuty
	 *            relative angle in degrees
	 */
	public AutoRotateCameraCommand() {
		requires(Robot.driveSys);
		ahrs = Robot.driveSys.getAhrs();
		speed = DEFAULT_SPEED;
		lastSideTurned = Side.LEFT;
		pidOut = new DutyCycleOutput();
		pidControl = new PIDController(CURRENT_SURFACE[0], CURRENT_SURFACE[1], CURRENT_SURFACE[2], new DutyCycleSource(), pidOut);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double currentDuty;
		if (Robot.pwm.getDutyCycle() <= 1) {
			currentDuty = Robot.pwm.getDutyCycle();
		} else {
			currentDuty = 1;
		}
		System.out.println("this robot is bad"+Robot.pwm.getDutyCycle());
		if (currentDuty < NO_TARGET_DUTY) {
			
			//speed = Math.pow(2*Math.abs(DESTINATION_DUTY - currentDuty), SLOWDOWN_POWER)
			//		* DEFAULT_SPEED;
			
			speed = pidOut.get();
			if(speed < CURRENT_SURFACE[3])
				speed = CURRENT_SURFACE[3];
			if (currentDuty >= DESTINATION_DUTY + TOLERANCE) {

				Robot.driveSys.setDriveMotors(speed, -speed);
				lastSideTurned = Side.RIGHT;
			} else if (currentDuty <= DESTINATION_DUTY - TOLERANCE) {
				Robot.driveSys.setDriveMotors(-speed, speed);
				lastSideTurned = Side.LEFT;
			} else {
				Robot.driveSys.setDriveMotors(0);
			}
			
		} else {
			
			speed = DEFAULT_SPEED;
			if(speed < CURRENT_SURFACE[3])
				speed = CURRENT_SURFACE[3];
			if (lastSideTurned == Side.LEFT)
				Robot.driveSys.setDriveMotors(-speed, speed);
			else if (lastSideTurned == Side.RIGHT)
				Robot.driveSys.setDriveMotors(speed, -speed);
			
		}
		System.out.println("SPEED: " + speed);
	}

	@Override
	protected boolean isFinished() {
		double currentDuty = Robot.pwm.getDutyCycle();
		return false;
		//return (currentDuty <= DESTINATION_DUTY + TOLERANCE && currentDuty >= DESTINATION_DUTY - TOLERANCE);
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		System.out.println("disabled");
		Robot.driveSys.setDriveMotors(0);
	}

	private enum Side {
		LEFT, RIGHT;
	}

}
