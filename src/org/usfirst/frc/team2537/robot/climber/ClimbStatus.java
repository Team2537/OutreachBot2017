package org.usfirst.frc.team2537.robot.climber;

public enum ClimbStatus {
	GROUNDED (0.4),
	AIR (1),
	TOUCHING (0);
	
	private final double speed;
	
	ClimbStatus(double speed) {
		this.speed = speed;
	}
	
	double speed() { return speed; }
}
