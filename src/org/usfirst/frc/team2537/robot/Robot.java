package org.usfirst.frc.team2537.robot;

//github.com/Team2537/Cogsworth.git
import org.usfirst.frc.team2537.robot.auto.AutoChooser;
import org.usfirst.frc.team2537.robot.cameras.Cameras;
import org.usfirst.frc.team2537.robot.climber.ClimberSubsystem;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.vision.PISubsystem;

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
	}
	

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		Scheduler.getInstance().add(autoChooser.getSelected());
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
		Scheduler.getInstance().run();
	}


	/**
	 * 
	 */
	public void teleopInit(){
		System.out.println("Teleop init");

	}

	public void testInit() {
	}

	@Override
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {
		driveSys.getAhrs().reset();
	}
	
	@Override
	public void disabledPeriodic() {
	}

}
