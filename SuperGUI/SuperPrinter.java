import java.io.BufferedWriter;
import java.io.IOException;

public class SuperPrinter {

	public static void printCourse(SuperPoint p, BufferedWriter writer) {
		printCourse(p, Math.toDegrees(p.getAngle()), writer);
	}

	/**
	 * Prints the course
	 *
	 * @param point
	 *            - current point
	 * @param startAngle
	 *            - current angle (not bearing) in degrees
	 */
	public static void printCourse(SuperPoint point, double startAngle, BufferedWriter writer) {
		if (point == null) throw new IllegalArgumentException("Null point");

		double destinationAngle = Math.toDegrees(point.getAngle());
		try {
			double currAngle = startAngle;
			double angleDiff;
			for (SuperAction a : point.getActions()) {
				angleDiff = currAngle - Math.toDegrees(a.getAngle()); // -(destAngle - starAngle) angle -> bearing
				currAngle = Math.toDegrees(a.getAngle());
				if(a.getAction() == SuperEnum.SHOOT) {
					angleDiff += 180;
					currAngle += 180;
					while(currAngle > 180) currAngle -= 360;
					while(currAngle < -180) currAngle += 360;
				}
				while(angleDiff > 180) angleDiff -= 360;
				while(angleDiff < -180) angleDiff += 360;
				
				// turn to command
				if(angleDiff != 0){
					writer.write("\t\taddSequential(new AutoRotateCommand(" + angleDiff + "));\n");
					System.out.println("Turn " + angleDiff);					
				}
				
				// place gear/shoot
				switch (a.getAction()) {
				case GEAR:
					writer.write("\t\taddSequential(new GearCommand());\n");
					System.out.println("Place Gear");
					break;
				case SHOOT:
					writer.write("\t\taddSequential(new ShootCommand());\n");
					System.out.println("Shoot");
					break;
				}
			}
			
			if(point.getNext() == null) return;
			
			angleDiff = currAngle - destinationAngle;
			while(angleDiff > 180) angleDiff -= 360;
			while(angleDiff < -180) angleDiff += 360;

			if (angleDiff != 0) {
				writer.write("\t\taddSequential(new AutoRotateCommand(" + angleDiff + "));\n");
				System.out.println("Turn " + angleDiff);
			}
			
			// drive distance to next point
			int y2 = point.getNext().getPoint().y;
			int x2 = point.getNext().getPoint().x;
			double distance = Math.sqrt(Math.pow(y2 - point.getPoint().y, 2) + Math.pow(x2 - point.getPoint().x, 2))
					/ SuperGUI.SCALE * 12; // distance in inches
			if (point.isBackwards()) distance = -distance;
			
			if (distance != 0) {
				writer.write("\t\taddSequential(new CourseCorrect(" + distance + "));\n");
				System.out.println("Drive " + distance);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();

		printCourse(point.getNext(), destinationAngle, writer);
	}
}
