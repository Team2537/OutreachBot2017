import java.io.BufferedWriter;
import java.io.IOException;

public class SuperPoint {

	/**
	 * Prints the course of the robot
	 *
	 * @param bot
	 * @param currentAngle
	 */
	public static void printCourse(SuperBot bot, double currentAngle, BufferedWriter writer) {
		if (bot == null)
			throw new IllegalArgumentException("Null bot");
		if (bot.getNext() == null){
			if(bot.getMode() != null){
				try {
					writer.write(bot.getMode().codesnippet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return;
		}

		int y2 = bot.getNext().getPoint().y;
		int x2 = bot.getNext().getPoint().x;

		double angle = -Math.atan2(bot.getPoint().y - y2, x2 - bot.getPoint().x)
				* 180 / Math.PI; // SuperGUI uses angles and AutoRotate uses bearings... so yea
		double distance = Math.sqrt(Math.pow(y2 - bot.getPoint().y, 2)
				+ Math.pow(x2 - bot.getPoint().x, 2))
				/ SuperGUI.SCALE * 12; // distance in inches

		double angleDiff = angle - currentAngle;

		while (angleDiff > 180)
			angleDiff -= 360;
		while (angleDiff < -180)
			angleDiff += 360;
		try {
			writer.write("\t\taddSequential(new AutoRotateCommand("+angleDiff+"));\n");
			writer.write("\t\taddSequential(new CourseCorrect("+ distance +"));\n");

			if(bot.getMode() != null){
				System.out.println(bot.getMode().codesnippet);
				writer.write(bot.getMode().codesnippet);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Turn " + angleDiff);
		System.out.println("Drive " + distance);
		System.out.println();

		printCourse(bot.getNext(), angle, writer);
	}
}
