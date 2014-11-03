package edu.imsa.students.inertia.shapes.regular;

import javafx.scene.shape.Polygon;

public class RegularTriangle extends Polygon{
	
	private Double scale;
	
	private static double[] points = { 	(double) 50,(double) 0,
										(double) 7,(double) 75,
										(double) 93,(double) 75
									 };
	public RegularTriangle(){
		super(points);
	}
	
	public RegularTriangle(double scale){
		super(points);
		setScale(scale);
	}
	

	public RegularTriangle(Double x, Double y, Double scale) {
		super(points);
		setScale(scale);
		setX(x);
		setY(y);
	}

	public Double getY() {

		return getTranslateY();
	}

	public void setY(Double y) {
		setLayoutY(y);
	}

	public Double getX() {
		return getTranslateX();
	}

	public void setX(Double x) {
		setLayoutX(x);
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
		setScaleX(scale);
		setScaleY(scale);
	}
}
