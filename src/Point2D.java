import java.util.StringTokenizer;

import API.AbstractNode;
import API.Vector2;

public class Point2D extends AbstractNode {
	double x;
	double y;
	
	public Point2D(){
		super();
	}

	public Point2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point2D(Point2D p){
		super();
		Point2D res = (Point2D)newInstance(p);
		this.x = res.x;
		this.y = res.y;
	}
	
	public Point2D(String line){
		Point2D p = (Point2D)newInstance(line);
		this.x = p.x;
		this.y = p.y;
		
	}

	@Override
	public AbstractNode newInstance() {
		return new Point2D();
	}

	@Override
	public AbstractNode newInstance(AbstractNode n) {
		Point2D p = (Point2D)n;
		return new Point2D(p.x, p.y);
	}
	

	@Override
	public AbstractNode newInstance(String line) {
		
		Point2D p = new Point2D();
		StringTokenizer st = new StringTokenizer(line);
		x = Double.parseDouble(st.nextToken());
		y = Double.parseDouble(st.nextToken());
		
		return p;
	}

	@Override
	public double getDistance(AbstractNode b) {
		if (b instanceof Point2D) {
			Point2D p = (Point2D) b;
			return Math.sqrt(Math.pow(p.x - x, 2.0) + Math.pow(p.y - y, 2.0));
		}
		return Double.MAX_VALUE;
	}

	@Override
	public AbstractNode copy() {
		return new Point2D(this);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}


	@Override
	public AbstractNode merge(AbstractNode b) {
		if (b instanceof Point2D) {
			Point2D p = (Point2D) b;
			Point2D n = new Point2D((x+p.x/2), (y+p.y/2));
			return n;
		}
		return null;
		
	}

	@Override
	public Vector2 projectTo2D() {
		return new Vector2(x, y);
	}

}
