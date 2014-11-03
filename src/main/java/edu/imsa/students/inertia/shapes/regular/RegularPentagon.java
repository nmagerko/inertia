package edu.imsa.students.inertia.shapes.regular;

import javafx.scene.shape.Polygon;

public class RegularPentagon extends Polygon{

	private static double[] points = { (double) 50,(double) 0,
								(double) 2,(double) 35,
								(double) 21,(double) 90,
								(double) 79,(double) 90,
								(double) 98,(double) 35
								};
	public RegularPentagon(){
		super(points);
	}
	
}
