package edu.imsa.students.inertia.shapes.regular;

public class RegularPentagon extends RegularShapeBase{

	private static double[] points = { (double) 50,(double) 0,
								(double) 2,(double) 35,
								(double) 21,(double) 90,
								(double) 79,(double) 90,
								(double) 98,(double) 35
								};
	public RegularPentagon(){
		super(points);
	}
	
	public RegularPentagon(double scale){
		super(points, scale);
	}
	

	public RegularPentagon(Double x, Double y, Double scale) {
		super(points, x, y, scale);
	}
	
}
