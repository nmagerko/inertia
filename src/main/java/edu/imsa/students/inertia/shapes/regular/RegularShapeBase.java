package edu.imsa.students.inertia.shapes.regular;

import javafx.scene.shape.Polygon;

public class RegularShapeBase extends Polygon {

	private Double scale;
	
	public RegularShapeBase(double[] points){
		super(points);
	}
	
	public RegularShapeBase(double[] points, double scale){
		super(points);
		setScale(scale);
	}
	

	public RegularShapeBase(double[] points, Double x, Double y, Double scale) {
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
