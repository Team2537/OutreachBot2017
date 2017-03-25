package org.usfirst.frc.team2537.robot;

//github.com/Team2537/Cogsworth.git
import org.usfirst.frc.team2537.robot.auto.AutoChooser;
import org.usfirst.frc.team2537.robot.auto.VisionRotate;
import org.usfirst.frc.team2537.robot.cameras.Cameras;
import org.usfirst.frc.team2537.robot.climber.ClimberSubsystem;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.vision.PISubsystem;
import org.usfirst.frc.team2537.robot.vision.RPiCalibration;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static DriveSubsystem driveSys;
	public static ClimberSubsystem climberSys;
	public static PowerDistributionPanel pdp;
	public static Cameras cameras;

	public static PISubsystem piSys;
	private SendableChooser<Command> autoChooser;
	
	@Override
	public void robotInit() {
		driveSys = new DriveSubsystem();
		driveSys.initDefaultCommand();
		
		climberSys = new ClimberSubsystem();
		climberSys.registerButtons();
		
		cameras = new Cameras();
		cameras.start();
		
		piSys = new PISubsystem();
		piSys.initDefaultCommand();
		
		pdp = new PowerDistributionPanel();
		
		autoChooser = new AutoChooser();
		SmartDashboard.putData("Auto Choices", autoChooser);
		SmartDashboard.putData("Reclibrate RPi", new RPiCalibration());
		SmartDashboard.putNumber("RPi Target Duty Cycle", VisionRotate.TARGET_DUTY_CYCLE);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		Scheduler.getInstance().add(autoChooser.getSelected());
		driveSys.resetEncoders();
		driveSys.getAhrs().reset();
		System.out.println("Autonomous start");
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void autonomousPeriodic() {
		//System.out.println(Robot.driveSys.rencoder.getRaw());
		SmartDashboard.putNumber("RPi Current Duty Cycle", piSys.getDutyCycle());
		SmartDashboard.putNumber("NavX Angle", driveSys.getAhrs().getAngle());
		Scheduler.getInstance().run();
	}


	/**
	 * 
	 */
	public void teleopInit(){
		Scheduler.getInstance().removeAll();
		System.out.println("Teleop init");

	}

	public void testInit() {
		Robot.driveSys.resetEncoders();
		Robot.driveSys.getAhrs().reset();
	}

	@Override
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
//		Scheduler.getInstance().run();
		double angle = Robot.driveSys.getAhrs().getAngle();
		if(angle > 180){
    		angle -= 360;
    	} else if (angle < -180) {
    		angle += 360;
    	}
		System.out.println(Robot.driveSys.getLeftEncoders());
		System.out.println(angle);
	}

	@Override
	public void disabledInit() {
		driveSys.getAhrs().reset();
		SmartDashboard.putNumber("RPi Current Duty Cycle", piSys.getDutyCycle());
	}
	
	@Override
	public void disabledPeriodic() {
		SmartDashboard.putNumber("RPi Current Duty Cycle", piSys.getDutyCycle());
		SmartDashboard.putNumber("RPi Target Duty Cycle", VisionRotate.TARGET_DUTY_CYCLE);
		SmartDashboard.putNumber("NavX Angle", driveSys.getAhrs().getAngle());
		Scheduler.getInstance().run();
	}

}
