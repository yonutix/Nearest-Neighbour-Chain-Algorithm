package API;

public abstract class AbstractNode {
	
	public abstract AbstractNode newInstance();
	public abstract AbstractNode newInstance(AbstractNode node);
	public abstract AbstractNode newInstance(String line);
	
	public abstract AbstractNode copy();
	public abstract double getDistance(AbstractNode b);
	public abstract String toString();
	public abstract AbstractNode merge(AbstractNode b);
	
	public abstract Vector2 projectTo2D();
}
