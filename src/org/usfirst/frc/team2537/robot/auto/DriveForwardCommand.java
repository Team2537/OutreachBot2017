package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardCommand extends Command {
	
	private final double distance; // inches
	private final double targetTicks;
    public DriveForwardCommand(double distance) {
    	requires(Robot.driveSys);
    	this.distance = distance;
    	targetTicks = distance * DriveSubsystem.ticksPerInch;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSys.resetEncoders();
    	Robot.driveSys.setMode(TalonControlMode.Position);
    	Robot.driveSys.enablePIDControl(.25, 0, 0);
    	Robot.driveSys.setFrontMotors(targetTicks, targetTicks);
    	Robot.driveSys.setBackMotors(Robot.driveSys.getLeftTalonSpeed(), Robot.driveSys.getRightTalonSpeed());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSys.setBackMotors(Robot.driveSys.getLeftTalonSpeed(), Robot.driveSys.getRightTalonSpeed());
    	System.out.println("[DriveForwardCommand] Encoder Position: " + Robot.driveSys.getEncoderAverage());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (distance > 0) {
    		return Robot.driveSys.getEncoderAverage() >= targetTicks;
    	} else {
    		return Robot.driveSys.getEncoderAverage() <= targetTicks;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("[DriveForwardCommand] finished");
    	Robot.driveSys.setMode(TalonControlMode.PercentVbus);
    	Robot.driveSys.disableMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
