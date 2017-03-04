package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.vision.PISubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class VisionRotate extends Command {
	
	/* when the pi cannot see the target, we spin faster to try to find the target */
	private static final double FAST_SPEED = 0.75;
	/* we spin slower when the pi can see the target so that we do not overshoot */
	private static final double SLOW_SPEED = 0.6;
	/* if the pi does not see a target, it outputs this duty cycle or less when right side up.  When upside down, it outputs 1 - @code{NO_TARGET_DUTY_CYCLE} */
	private static final double NO_TARGET_DUTY_CYCLE = 0.02;
	/* we are perfectly centered at @code{TARGET_DUTY_CYCLE}.  When mounted right side up, duty cycles greater than
	 * @code{TARGET_DUTY_CYCLE} represents a target to the right of the center of the camera, with offset given by the (the duty cycle - @code{TARGET_DUTY_CYCLE})
	 * likewise, duty cycles less than @code{TARGET_DUTY_CYCLE} represents a target to the left of the center of the camera.
	 * 
	 * the greater the distance of the target is from the center of the camera, the duty cycles gets farther from the @code{TARGET_DUTY_CYCLE}
	 */
	private static final double TARGET_DUTY_CYCLE = 0.52;
	/* we stop this command when the pi duty cycle is within @code{TARGET_DUTY_CYCLE} +/- @code{DUTY_CYCLE_TOLERANCE} */
	private static final double DUTY_CYCLE_TOLERANCE = 0.02;
	
	private int inToleranceCycleCounts;
	private int inToleranceCycleCountsThreshold = 10;
	
	public VisionRotate(){
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {
		inToleranceCycleCounts = 0;
	}

	@Override
	protected void execute() {
		/* if the pi is not connected or there is a malfunction, we receive infinity, so we end this command */
		if(Robot.piSys.getDutyCycle() == Double.POSITIVE_INFINITY){
			this.cancel();
		}
		boolean targetInSight;
		if(PISubsystem.PI_MOUNTED_RIGHT_SIDE_UP){
			targetInSight = (Robot.piSys.getDutyCycle() > NO_TARGET_DUTY_CYCLE);
		} else{
			targetInSight = (Robot.piSys.getDutyCycle() < 1 - NO_TARGET_DUTY_CYCLE);
		}
		
		/* if we cannot see the target, we spin faster */
		if(!targetInSight){
			Robot.driveSys.setDriveMotors(FAST_SPEED, -FAST_SPEED);
		} else{
			double deltaDutyCycle = Robot.piSys.getDutyCycle() - TARGET_DUTY_CYCLE;
			/* if the pi is upside down, we have to invert the interpretation of the duty cycle */
			int orientationConverter = (PISubsystem.PI_MOUNTED_RIGHT_SIDE_UP) ? 1 : -1;
			double speed = SLOW_SPEED * orientationConverter;
			if(deltaDutyCycle > 0){
				Robot.driveSys.setDriveMotors(-speed, speed);
			} else{
				Robot.driveSys.setDriveMotors(speed, -speed);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		System.out.println("[VisionRotate] duty cycle: " + Robot.piSys.getDutyCycle());
		if(Math.abs(Robot.piSys.getDutyCycle() - TARGET_DUTY_CYCLE) <= DUTY_CYCLE_TOLERANCE){
			inToleranceCycleCounts++;
		} else{
			inToleranceCycleCounts = 0;
		}
		return (inToleranceCycleCounts >= inToleranceCycleCountsThreshold);
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
}
