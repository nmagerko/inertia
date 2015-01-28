package edu.imsa.students.inertia.shapes.regular;

public class RegularTriangle extends RegularShapeBase{
	
	
	private static double[] points = { 	(double) 50,(double) 0,
										(double) 7,(double) 75,
										(double) 93,(double) 75
									 };
	public RegularTriangle(){
		super(points);
	}
	
	public RegularTriangle(double scale){
		super(points, scale);
	}
	

	public RegularTriangle(Double x, Double y, Double scale) {
		super(points, x, y, scale);
	}

}
