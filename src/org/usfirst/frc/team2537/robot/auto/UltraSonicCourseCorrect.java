package org.usfirst.frc.team2537.robot.auto;

import static org.usfirst.frc.team2537.robot.auto.CourseCorrect.MINIMUM_SPEED;

import org.usfirst.frc.team2537.robot.Robot;

public class UltraSonicCourseCorrect extends DriveStraightCommand {
	private static final int TOLERANCE = 9;

	public UltraSonicCourseCorrect() {
		super(30);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isFinished(){
		double ultraDistance = Robot.driveSys.ultraSanic.getRangeInches();
		//System.out.println(ultraDistance);
		if(ultraDistance <= TOLERANCE){
			return true;
		}
		else{
			return false;
		}
	}

}
