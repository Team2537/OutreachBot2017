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
		if (Robot.climberSys.getXboxTrigger(3) > 0.2) {
			Robot.climberSys.setClimberMotor(-Robot.climberSys.getXboxTrigger(3));
		} else {
			Robot.climberSys.setClimberMotor(0);
		}
		
		writer.println(
				System.currentTimeMillis() - startTime + "," + Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL));
		System.out.println(Robot.pdp.getCurrent(Ports.CLIMBER_MOTOR_PDP_CHANNEL));

	}

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
