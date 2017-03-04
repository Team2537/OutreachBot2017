package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

public class UltraSonicCourseCorrect extends DriveStraightCommand {
	private static final int TARGET_STOP_DISTANCE = 9;// number of inches to stop at
	private static final int MAX_STOP_DIST = 30; // maximum drive dist
	public UltraSonicCourseCorrect() {
		super(MAX_STOP_DIST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isFinished(){
		return Robot.driveSys.ultraSanic.getRangeInches() <= TARGET_STOP_DISTANCE;
	}

}
