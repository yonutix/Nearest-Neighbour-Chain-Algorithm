package API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * A Cluster contains multiple nodes
 */
public class Cluster<T, C extends AbstractNode> extends ArrayList<T> {

	private static final Map<Class<?>, Class<?>> MAP = new HashMap<Class<?>, Class<?>>() {

		private static final long serialVersionUID = 1L;

		{
			put(Integer.class, int.class);
			put(Long.class, long.class);
			put(Double.class, double.class);
			put(Float.class, float.class);
			put(Short.class, short.class);
			put(Byte.class, byte.class);
			put(Character.class, char.class);
			put(Boolean.class, boolean.class);
		}
	};

	private C centroid;

	public Cluster(C defaultCentroid) {
		super();
		centroid = defaultCentroid;
	}

	public boolean compareTo(Cluster<T, C> b) {
		for (T element : b) {
			if (contains(element))
				return true;
		}
		return false;
	}

	public C getCentroid() {
		return centroid;
	}

	public void setCentroid(C centroid) {
		this.centroid = centroid;
	}

	public String toString() {
		String res = "";
		
		for (T element : this) {
			res += element.toString() + " ";
		}
		res += centroid.toString();
		return res;
	}

	public double getDistance(Cluster<T, C> other) {
		return centroid.getDistance(other.centroid);
	}

	public static <V> V copy(V var) {
		try {
			Class<?> type = MAP.containsKey(var.getClass()) ? MAP.get(var
					.getClass()) : var.getClass();
			return (V) var.getClass().getConstructor(type).newInstance(var);
		} catch (Exception e) {
			System.out.println("Copy faield " + e.getMessage() + " ");
			e.printStackTrace();
		}
		return null;
	}

	public static <X, Y extends AbstractNode> Cluster<X, Y> merge(
			Cluster<X, Y> a, Cluster<X, Y> b) {

		Y p1 = a.centroid;
		Y p2 = b.centroid;
		Y result = (Y) p1.merge(p2);

		//result.print();

		Cluster<X, Y> newCLuster = new Cluster<X, Y>(result);

		//System.out.println("New cluster:");

		//newCLuster.print();

		for (X element : a) {
			X newElement = copy(element);
			//System.out.println(element);
			//System.out.println(newElement);

			newCLuster.add(copy(element));
		}

		for (X element : b) {
			newCLuster.add(copy(element));
		}

		return newCLuster;
	}

}
