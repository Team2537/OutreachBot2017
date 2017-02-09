package org.usfirst.frc.team2537.robot.climber;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command {
	// executes climber function

	public int shortClimbTimems = 5000;
	public int longClimbTimems = 30000;
	private long climbStartTime;
	private boolean startedLongClimb;
	private int ropeRange = 2; // range away from ultrasonic that rope is
	private int limitCurrent = 11; // TODO this number almost definetly isn't
									// right. Measure # on actual robot

	private String filename; // name of file to write current/time to (ex.
								// /home/lvuser/climberstats20170204_113440.csv)
	private Path dataPath; // used to check if the file already exists (it
							// shouldn't exist)
	private PrintWriter writer;
	private long startTime; // time the command initializes

	public ClimberCommand() {
		requires(Robot.climberSys);
	}

	@Override
	protected void initialize() {
		// System.out.println("Climber is running");
		/*
		 * climbStartTime = System.currentTimeMillis(); System.out.println(
		 * "The climber is running"); Robot.climberSys.setCLimberMotor(.25);
		 * System.out.println("The climber is running slowly"); startedLongClimb
		 * = false;
		 */
		filename = "/home/lvuser/climberstats"
				+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".csv";
		dataPath = Paths.get(filename);
		if (Files.exists(dataPath)) {
			System.out.println("File " + dataPath + " already exists. It shouldn't.");
		}

		try {
			Files.createFile(dataPath);
			writer = new PrintWriter(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.println("Time (ms),Current (amps)");
		startTime = System.currentTimeMillis();
		writer.println(
				System.currentTimeMillis() - startTime + "," + Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL));
	}

	@Override
	protected void execute() {
		// System.out.println("Climber Running");
		/*
		 * if (System.currentTimeMillis() - climbStartTime > shortClimbTimems &&
		 * !startedLongClimb) { Robot.climberSys.setCLimberMotor(.75);
		 * System.out.println("The climber is running quiRobotckly");
		 * Robot.climberSys.setCLimberMotor(longClimbTimems); startedLongClimb =
		 * true; }
		 */

		/*
		 * if (Robot.climberSys.getRopeCheck() <= ropeRange ) {
		 * System.out.println("The rope is within range"); }
		 */
		if (!Robot.climberSys.getClimberPressureSensor()) {
			System.out.println("The Pressure Sensor is pressed");
			Robot.climberSys.setClimberMotor(0);
			writer.flush();
		} else if (Robot.climberSys.getXboxTrigger(3) > 0) {
			Robot.climberSys.setClimberMotor(-Robot.climberSys.getXboxTrigger(3));
		} else {
			Robot.climberSys.setClimberMotor(0);
		}

		/*
		 * if (Robot.climberSys.getXboxTrigger(3) > 0.1) { if
		 * (Robot.climberSys.getClimber1Velocity() == 0) { System.out.println(
		 * "Motor one is offline"); } if (Robot.climberSys.getClimber2Velocity()
		 * == 0) { System.out.println("Climber Motor Two is Offline"); } } else
		 * if (Robot.climberSys.getXboxTrigger(2) > 0.1) { if
		 * (Robot.climberSys.getClimber1Velocity() == 0) { System.out.println(
		 * "Climber Motor One is offline"); } if
		 * (Robot.climberSys.getClimber2Velocity() == 0) { System.out.println(
		 * "Climber Motor One is offline"); }
		 */
		writer.println(
				System.currentTimeMillis() - startTime + "," + Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL));
		System.out.println(Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL));

	}
	// }

	@Override
	protected boolean isFinished() {
		return Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL) > limitCurrent;
	}

	@Override
	protected void end() {
		Robot.climberSys.setClimberMotor(0);
		writer.close();
		// System.out.println("The climber is done");
	}

	@Override
	protected void interrupted() {
		Robot.climberSys.setClimberMotor(0);
		writer.close();
		// System.out.println("The climber has been interrupted");
	}
}
