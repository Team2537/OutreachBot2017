package org.usfirst.frc.team2537.robot.auto;

import static org.usfirst.frc.team2537.robot.auto.CourseCorrect.MINIMUM_SPEED;
import static org.usfirst.frc.team2537.robot.auto.CourseCorrect.getSlowdownStart;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic.Unit;

public class UltraSonicCourseCorrect extends CourseCorrect {
	public static final double TOLERANCE = 6;
	private static final double SLOWDOWN_START = 40;
	private static final double CORRECTION_PROPORTION = 0.25;
	private double startDuty;
	private double speed;
	private boolean debug = false;
	public UltraSonicCourseCorrect() {
		super(0);
		speed = DEFAULT_SPEED;
		startDuty = 0.5;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void execute() {
		double currentAngle = Robot.pwm.getDutyCycle();
		System.out.println(Robot.driveSys.ultraSanic.getRangeInches()<SLOWDOWN_START);
		if (Robot.driveSys.ultraSanic.getRangeInches() < SLOWDOWN_START) {
			speed = Robot.driveSys.ultraSanic.getRangeInches()/SLOWDOWN_START*DEFAULT_SPEED + MINIMUM_SPEED ;
		}
		System.out.println(speed);
		double left = speed;
		double right = speed;
		double correction = 0;

		double angleDiff = (currentAngle - startDuty);
		if (debug ) System.out.println("CourseCorrect exec: start: " + startDuty + "\tdiff: " + angleDiff);

		if (Math.abs(angleDiff) > TOLERANCE) correction = angleDiff / CORRECTION_PROPORTION;

		left += correction;
		right -= correction;

		Robot.driveSys.setDriveMotors(left, right);
	}
	@Override
	public boolean isFinished(){
		double ultraDistance = Robot.driveSys.ultraSanic.getRangeInches();
		System.out.println(ultraDistance);
		if(ultraDistance <= TOLERANCE){
			return true;
		}
		else{
			return false;
		}
	}

}
