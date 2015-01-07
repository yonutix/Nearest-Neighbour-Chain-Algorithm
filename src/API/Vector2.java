package API;

public class Vector2 {
	public double x;
	public double y;
	
	public Vector2() {
		super();
	}

	public Vector2(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 b){
		this.x = b.x;
		this.y = b.y;
	}
	
}
