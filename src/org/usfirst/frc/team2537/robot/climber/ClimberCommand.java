package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command {
	// executes climber function

	public int shortClimbTimems = 5000;
	public int longClimbTimems = 30000;
	private long climbStartTime;
	private boolean startedLongClimb;
	private int ropeRange = 2; //range away from ultrasonic that rope is

	public ClimberCommand() {
		requires(Robot.climberSys);
	}

	@Override
	protected void initialize() {
		System.out.println("Climber is running");
		/*
		 * climbStartTime = System.currentTimeMillis();
		 * System.out.println("The climber is running");
		 * Robot.climberSys.setCLimberMotor(.25);
		 * System.out.println("The climber is running slowly"); startedLongClimb
		 * = false;
		 */

	}

	@Override
	protected void execute() {
		/*
		 * if (System.currentTimeMillis() - climbStartTime > shortClimbTimems &&
		 * !startedLongClimb) { Robot.climberSys.setCLimberMotor(.75);
		 * System.out.println("The climber is running quiRobotckly");
		 * Robot.climberSys.setCLimberMotor(longClimbTimems); startedLongClimb =
		 * true; }
		 */
//		if (Robot.climberSys.getRopeCheck() <= ropeRange ) {
//			System.out.println("The rope is within range");
//		}
//		if (Robot.climberSys.getClimberPressureSensor()) {
//			System.out.println("The Pressure Sensor is pressed");
//			Robot.climberSys.setCLimberMotor(0);
//		} else {
		
		if (Robot.climberSys.getXboxTrigger(3) > 0.1) {
			Robot.climberSys.setCLimberMotor(Robot.climberSys.getXboxTrigger(3));
		} else if (Robot.climberSys.getXboxTrigger(2) > 0.1) {
			Robot.climberSys.setCLimberMotor(-Robot.climberSys.getXboxTrigger(2));
		} else {
			Robot.climberSys.setCLimberMotor(0);
		}
//		}
	}

	@Override
	protected boolean isFinished() {
		return false;
		/*
		 * if (System.currentTimeMillis() - climbStartTime > longClimbTimems) {
		 * return true; } else if (Robot.climberSys.getEncoderVelocity() == 0) {
		 * return true; }
		 */

	}

	@Override
	protected void end() {
		Robot.climberSys.setCLimberMotor(0);
		System.out.println("The climber is done");
	}

	@Override
	protected void interrupted() {
		Robot.climberSys.setCLimberMotor(0);
		System.out.println("The climber has been interrupted");
	}
}
