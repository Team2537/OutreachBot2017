package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardCommand extends Command {
	
	private final double distance; // inches
	private static final double SPEED = 0.2;
    public DriveForwardCommand(double distance) {
    	requires(Robot.driveSys);
    	this.distance = distance;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSys.resetEncoders();
		Robot.driveSys.setDriveMotors(SPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("[DriveForwardCommand] distance: " +  Robot.driveSys.getEncoderAverage());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSys.getEncoderAverage() >= distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("[DriveForwardCommand] finished");
    	Robot.driveSys.setDriveMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
