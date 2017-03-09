package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateCommand extends Command {
	private double targetAngle;
	private static final double DEFAULT_SPEED = 1.0;
	private static final double REDUCED_SPEED = 0.7;
	private static final double TOLERANCE = 1; // degrees
	private static final int SLOW_DOWN_ANGLE = 10;
	private double currentAngle;
    public RotateCommand(double angle) {
    	requires(Robot.driveSys);
    	Robot.driveSys.getAhrs().reset();
    	targetAngle = angle;
    	if(targetAngle > 180){
    		targetAngle -= 360;
    	}
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSys.getAhrs().reset();
    	Robot.driveSys.setMode(TalonControlMode.PercentVbus);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle=Robot.driveSys.getAhrs().getAngle();
    	if(currentAngle > 180){
    		currentAngle -= 360;
    	}
    	double speed = DEFAULT_SPEED;
    	if (Math.abs(currentAngle-targetAngle) < SLOW_DOWN_ANGLE) { //reduces speed if angle is close to finishing angle
			speed=REDUCED_SPEED;
		}
    	double deltaAngle = currentAngle - targetAngle;
		if (deltaAngle > TOLERANCE)
			Robot.driveSys.setDriveMotors(-speed, speed);
		else if (deltaAngle < -TOLERANCE)
			Robot.driveSys.setDriveMotors(speed, -speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double absoluteDeltaAngle = Math.abs(currentAngle - targetAngle);
    	return (absoluteDeltaAngle <= TOLERANCE);
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.driveSys.setDriveMotors(0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}