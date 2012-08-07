package neural.tools;

public class Converter {
	
	public static boolean[] doubleToBoolean(double[] src) {
		boolean[] dest = new boolean[src.length];
		for (int i=0; i<src.length; i++) {
			if (src[i]>0)
				dest[i] = true;
		}
		return dest;
	}

}
