package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Front wheels are SRX Talons
 * Back wheels are SR Talons
 * 
 * We set the SRX talons to drive a certain distance using their built-in PID loop
 * We set the SR talons to follow the speeds set by the SRX talons by a hardcoded proportion @code{BACK_WHEEL_SPEED_PROPORTION}
 * 
 * Although we cannot set the front wheel speeds explicitly, we manually set the back wheel speeds
 * which enables us to course correct with the NavX
 */
public class DriveStraightCommand extends Command {
	
	private final double targetTicks;
	private static final double P = 0.01;
	private static final double I = 0;
	private static final double D = 0;
	/* the back wheels are a set proportion of the speed of the front wheels */
	private static final double BACK_WHEEL_SPEED_PROPORTION = 0.01;
	/* increasing the correction proportion increases how fast the robot corrects for angled driving */
	private static final double BACK_WHEEL_NAVX_CORRECTION_PROPORTION = 0.2;
	/* the robot begins to turn when the angle is greater than this constant */
	private static final double NAVX_CORRECTION_DEGREE_TOLERANCE = 1;
	/* add @code{DISTANCE_STOP_CORRECTION} inches to distance because the robot consistently stops this many inches early */
	private static final double DISTANCE_STOP_CORRECTION = 0;
	
    public DriveStraightCommand(double distance) {
    	requires(Robot.driveSys);
    	targetTicks = (distance + DISTANCE_STOP_CORRECTION) * DriveSubsystem.ticksPerInch;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("[DriveStraightCommand] target ticks: " + targetTicks);
    	Robot.driveSys.resetEncoders();
    	Robot.driveSys.getAhrs().reset();
    	Robot.driveSys.setMode(TalonControlMode.Position);
    	Robot.driveSys.enablePIDControl(P, I, D);
    	Robot.driveSys.setFrontMotors(targetTicks, -targetTicks);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.driveSys.getAhrs().getAngle();
    	/* convert angle domain from [0,360] to [-180,180] */
    	if(angle > 180){
    		angle -= 360;
    	} else if(angle < -180){
    		angle += 360;
    	}
//    	System.out.println("[DriveStraightCommand] angle: " + angle);
    	/* set the back wheels to a speed proportional to the front wheel speeds
    	 * the front wheels are set by a PID loop */
    	double backLeftWheelSpeed = ((targetTicks - Robot.driveSys.getEncoderAverage()) / targetTicks) * BACK_WHEEL_SPEED_PROPORTION;
    	/* the back right wheel is electrically reversed on SteamWorks robot 2 */
    	double backRightWheelSpeed = -backLeftWheelSpeed;
    	/* if the angle is greater than tolerance */
    	if(Math.abs(angle) > NAVX_CORRECTION_DEGREE_TOLERANCE){
    		/* if the robot is to the left, correction is negative, else it is positive */
    		double correction = angle * BACK_WHEEL_NAVX_CORRECTION_PROPORTION;
    		backRightWheelSpeed -= correction;
    		backLeftWheelSpeed -= correction;
    	}
    	SmartDashboard.putNumber("[DriveStraightCommand] target ticks", targetTicks);
    	SmartDashboard.putNumber("[DriveStraightCommand] encoder average", Robot.driveSys.getEncoderAverage());
    	SmartDashboard.putNumber("[DriveStraightCommand] left ticks", Robot.driveSys.getLeftEncoders());
    	SmartDashboard.putNumber("[DriveStraightCommand] right ticks", Robot.driveSys.getRightEncoders());
    	Robot.driveSys.setBackMotors(backLeftWheelSpeed, backRightWheelSpeed);
//    	System.out.println("[DriveForwardCommand] Encoder Position: " + Robot.driveSys.getEncoderAverage());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return targetTicks > 0 ? Robot.driveSys.getEncoderAverage() >= targetTicks : Robot.driveSys.getEncoderAverage() <= targetTicks;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("[DriveForwardCommand] finished. Ticks: " + Robot.driveSys.getEncoderAverage());
    	Robot.driveSys.setMode(TalonControlMode.PercentVbus);
		Robot.driveSys.setDriveMotors(0);
		/* we disable and then enable the motors to kill the PID loop */
    	Robot.driveSys.disableMotors();
    	Robot.driveSys.enableMotors();
    	Robot.driveSys.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}