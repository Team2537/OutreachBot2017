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
			double midAngle = startAngle;
			double midAngleDiff;
			for (SuperAction a : point.getActions()) {
				midAngleDiff = midAngle - Math.toDegrees(a.getAngle()); // -(destAngle - starAngle) angle -> bearing
				midAngle = Math.toDegrees(a.getAngle());
				while(midAngleDiff > 180) midAngleDiff -= 360;
				while(midAngleDiff < -180) midAngleDiff += 360;
				
				// turn to command
				if(midAngleDiff != 0){
					writer.write("\t\taddSequential(new AutoRotateCommand(" + midAngleDiff + "));\n");
					System.out.println("Turn " + midAngleDiff);					
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
			
			midAngleDiff = midAngle - destinationAngle;
			while(midAngleDiff > 180) midAngleDiff -= 360;
			while(midAngleDiff < -180) midAngleDiff += 360;

			if (midAngleDiff != 0) {
				writer.write("\t\taddSequential(new AutoRotateCommand(" + midAngleDiff + "));\n");
				System.out.println("Turn " + midAngleDiff);
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
