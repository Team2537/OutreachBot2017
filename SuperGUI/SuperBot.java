import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class SuperBot {

	private SuperBot next;
	private Point p;
	private double angle;
	private int alpha;

	/**
	 * Adds a SuperBot to the end of the bot list
	 *
	 * @param p
	 *            - position of the last bot in the chain
	 */
	public SuperBot(Point p) {
		this.p = (Point) p.clone();
		next = null;
		alpha = 255;
	}

	/**
	 * Adds a SuperBot that turns to the angle
	 *
	 * @param p
	 *            - the position of the previous SuperBot in the chain
	 * @param angle
	 *            - the angle the new bot points to
	 */
	private SuperBot(Point p, double angle) {
		this.angle = angle;
		this.p = (Point) p.clone();
		next = null;
		alpha = 255;
	}

	// adds new robot in pixel position
	public void add(Point p) {
		if (next == null) next = new SuperBot(p);
		else next.add(p);
	}

	/**
	 * Makes the robot point towards a point
	 *
	 * @param p
	 *            - The point to point to
	 */
	public void point(Point p) {
		if (next != null) {
			next.point(p);
			return;
		}
		double tmpAngle = Math.atan2(p.y - this.p.y, p.x - this.p.x);
		System.out.println(tmpAngle);
		System.out.println(this.p);
		System.out.println(p);
		next = new SuperBot(this.p, tmpAngle);
	}

	/**
	 * returns the index of the robot containing point p
	 *
	 * @param p
	 * @return
	 */
	public int contains(Point p) {
		return contains(p, 0);
	}

	public int contains(Point p, int index) {
		// bot collision
		if (Math.sqrt(Math.pow(p.x - this.p.x, 2) + Math.pow(p.y - this.p.y, 2)) <= SuperGUI.ROBOT_DIAMETER / 2
				* SuperGUI.SCALE)
			return index;

		// path collision
		if (next != null && alpha > 50) {
			double angle = Math.atan2(-next.getPoint().y + this.p.y, next.getPoint().x - this.p.x);
			Polygon path = new Polygon(new int[] {
					this.p.x + (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.cos(angle + Math.PI / 2)),
					this.p.x + (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.cos(angle - Math.PI / 2)),
					next.getPoint().x
							+ (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.cos(angle - Math.PI / 2)),
					next.getPoint().x
							+ (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.cos(angle + Math.PI / 2)) },

					new int[] { this.p.y
							- (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.sin(angle + Math.PI / 2)),
							this.p.y - (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE
									* Math.sin(angle - Math.PI / 2)),
							next.getPoint().y - (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE
									* Math.sin(angle - Math.PI / 2)),
							next.getPoint().y - (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE
									* Math.sin(angle + Math.PI / 2)) },

					4);
			if (path.contains(p)) return index + 1;
		}

		if (next == null) return -1;

		return next.contains(p, index + 1);
	}

	/**
	 * removes a robot at an index > 0
	 *
	 * @param index
	 * @return success or fail
	 */
	public boolean remove(int index) {
		if (index <= 0) return false;
		if (index == 1) {
			next = null;
			return true;
		}
		if (next == null) return false;
		return next.remove(index - 1);
	}

	public void draw(Graphics g, int alpha) {
		this.alpha = alpha;

		if (next != null && !next.p.equals(p)) {
			double angle = Math.atan2(-next.p.y + p.y, next.p.x - p.x);

			// draw path to next bot
			g.setColor(new Color(255, 155, 0, alpha));
			Polygon path = new Polygon(new int[] {
					p.x + (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.cos(angle + Math.PI / 2)),
					p.x + (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.cos(angle - Math.PI / 2)),
					next.getPoint().x
							+ (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.cos(angle - Math.PI / 2)),
					next.getPoint().x
							+ (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.cos(angle + Math.PI / 2)) },

					new int[] {
							p.y - (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.sin(angle + Math.PI / 2)),
							p.y - (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE * Math.sin(angle - Math.PI / 2)),
							next.getPoint().y - (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE
									* Math.sin(angle - Math.PI / 2)),
							next.getPoint().y - (int) (.5 * SuperGUI.ROBOT_WIDTH * SuperGUI.SCALE
									* Math.sin(angle + Math.PI / 2)) },

					4);

			g.fillPolygon(path);

			// draw arrow within path
			g.setColor(new Color(255, 255, 255, alpha));
			double distance = Math.sqrt(Math.pow(next.getPoint().x - p.x, 2) + Math.pow(-next.getPoint().y + p.y, 2));
			double arrowSize = distance / 15;
			if (arrowSize > SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE / 2.0)
				arrowSize = SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE / 2.0;
			g.drawPolyline(new int[] { p.x + (int) (distance * 2 / 5 * Math.cos(angle)),
					p.x + (int) (distance * 3 / 5 * Math.cos(angle)),
					p.x + (int) (distance * 3 / 5 * Math.cos(angle) + arrowSize * Math.cos(angle + Math.PI * 5 / 6)),
					p.x + (int) (distance * 3 / 5 * Math.cos(angle)),
					p.x + (int) (distance * 3 / 5 * Math.cos(angle) + arrowSize * Math.cos(angle - Math.PI * 5 / 6)) },

					new int[] { p.y - (int) (distance * 2 / 5 * Math.sin(angle)),
							p.y - (int) (distance * 3 / 5 * Math.sin(angle)),
							p.y - (int) (distance * 3 / 5 * Math.sin(angle)
									+ arrowSize * Math.sin(angle + Math.PI * 5 / 6)),
							p.y - (int) (distance * 3 / 5 * Math.sin(angle)),
							p.y - (int) (distance * 3 / 5 * Math.sin(angle)
									+ arrowSize * Math.sin(angle - Math.PI * 5 / 6)) },

					5);

		}

		// Increase alpha for bots
		int botAlpha = alpha + 100;
		if (botAlpha > 255) botAlpha = 255;

		// Draw translucent circle around bot
		g.setColor(new Color(0, 255, 0, alpha / 2));
		if (next == null) g.setColor(new Color(0, 255, 0, botAlpha));
		if (next == null || !next.p.equals(p)) g.fillOval(p.x - (int) (SuperGUI.ROBOT_DIAMETER / 2 * SuperGUI.SCALE),
				p.y - (int) (SuperGUI.ROBOT_DIAMETER / 2 * SuperGUI.SCALE),
				(int) (SuperGUI.SCALE * SuperGUI.ROBOT_DIAMETER), (int) (SuperGUI.SCALE * SuperGUI.ROBOT_DIAMETER));

		if (next != null) {
			double angle = next.p.equals(p) ? this.angle
					: Math.atan2(-next.getPoint().y + p.y, next.getPoint().x - p.x);

			// draw square for bot
			g.setColor(new Color(0, 255, 0, botAlpha));
			double cornerAngle = Math.atan2(SuperGUI.ROBOT_WIDTH, SuperGUI.ROBOT_LENGTH);
			g.fillPolygon(new int[] {
					p.x + (int) (.5 * SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE * Math.cos(cornerAngle + angle)),
					p.x + (int) (.5 * SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE * Math.cos(angle - cornerAngle)),
					p.x + (int) (.5 * SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE
							* Math.cos(Math.PI + cornerAngle + angle)),
					p.x + (int) (.5 * SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE
							* Math.cos(Math.PI + angle - cornerAngle)) },

					new int[] {
							p.y - (int) (.5 * SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE * Math.sin(cornerAngle + angle)),
							p.y - (int) (.5 * SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE * Math.sin(angle - cornerAngle)),
							p.y - (int) (.5 * SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE
									* Math.sin(Math.PI + cornerAngle + angle)),
							p.y - (int) (.5 * SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE
									* Math.sin(Math.PI + angle - cornerAngle)) },

					4);

			// draw arrow within bot
			g.setColor(new Color(0, 0, 255, botAlpha));
			double distance = SuperGUI.ROBOT_DIAMETER * SuperGUI.SCALE / 2.0;
			double arrowSize = distance / 2;
			g.drawPolyline(new int[] { p.x + (int) (distance * 2 / 5 * Math.cos(angle + Math.PI)),
					p.x + (int) (distance * 3 / 5 * Math.cos(angle)),
					p.x + (int) (distance * 3 / 5 * Math.cos(angle) + arrowSize * Math.cos(angle + Math.PI * 5 / 6)),
					p.x + (int) (distance * 3 / 5 * Math.cos(angle)),
					p.x + (int) (distance * 3 / 5 * Math.cos(angle) + arrowSize * Math.cos(angle - Math.PI * 5 / 6)) },

					new int[] { p.y - (int) (distance * 2 / 5 * Math.sin(angle + Math.PI)),
							p.y - (int) (distance * 3 / 5 * Math.sin(angle)),
							p.y - (int) (distance * 3 / 5 * Math.sin(angle)
									+ arrowSize * Math.sin(angle + Math.PI * 5 / 6)),
							p.y - (int) (distance * 3 / 5 * Math.sin(angle)),
							p.y - (int) (distance * 3 / 5 * Math.sin(angle)
									+ arrowSize * Math.sin(angle - Math.PI * 5 / 6)) },

					5);

			next.draw(g, alpha);
		}

	}

	public int getNumBots() {
		if (next == null) return 1;
		return 1 + next.getNumBots();
	}

	public Point getPoint() {
		return p;
	}

	public SuperBot getNext() {
		return next;
	}
}
