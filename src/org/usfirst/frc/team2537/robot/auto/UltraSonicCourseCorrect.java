package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic.Unit;

public class UltraSonicCourseCorrect extends CourseCorrect {
	public static final double TOLERANCE = 4;
	public UltraSonicCourseCorrect(double distance) {
		super(distance);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isFinished(){
		Robot.driveSys.ultraSanic.setDistanceUnits(Unit.kInches);
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
