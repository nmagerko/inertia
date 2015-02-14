package edu.imsa.students.inertia.services.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.InertialApplication;
import edu.imsa.students.inertia.InertialSupervisor;
import edu.imsa.students.inertia.services.physics.collision.CollisionDetectionService;
import edu.imsa.students.inertia.services.physics.collision.CollisionEvaluationService;
import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialPhysicsService {
	
	private static final double TIME_STEP = 0.25;
	private static final String X_POSITION_IDENTIFIER = "X Position";
	private static final String Y_POSITION_IDENTIFIER = "Y Position";

	/**
	 * Steps forward in time, moving all objects
	 * 
	 * @param objectList
	 * @param timeStep
	 * @param supervisor
	 */
	public static void advance(List<InertialBridge> objectList) {
		InertialSupervisor supervisor = InertialApplication.getMainController();
		for (InertialBridge i : objectList) {

			InertialAttributes attributes = i.getInertialAttributes();
			if (!attributes.isInDrag()) {

				Vector2d velocity = attributes.getVelocity();
				Point2d position = i.getPosition();

				// changes acceleration
				attributes.applyForces(TIME_STEP);

				// changes velocity
				// also check collisions
				Pane parentPane = supervisor.getInertialPane();

				Bounds container = parentPane.getLayoutBounds();
				Bounds object = i.getBounds();

				boolean outOfBounds = false;
				// test reflection in x direction
				if (isOutOfXBounds(object, container)) {
					velocity.set(-velocity.x, velocity.y);
					outOfBounds = true;
				}
				// test reflection in y direction
				if (isOutOfYBounds(object, container)) {
					velocity.set(velocity.x, -velocity.y);
					outOfBounds = true;
				}
				
				if (!outOfBounds) {
					Vector2d acceleration = attributes.getAcceleration();
					Vector2d accelerationStep = new Vector2d(acceleration.x,
							acceleration.y);
					accelerationStep.scale(TIME_STEP);
					velocity.add(accelerationStep);
				}

				// changes position
				Vector2d velocityStep = new Vector2d(velocity.x, velocity.y);
				velocityStep.scale(TIME_STEP);
				position.sub(velocityStep);
				i.setPosition(position);
			}
		}
	}

	/**
	 * Checks whether an object is within x bounds of its container.
	 * 
	 * @param object
	 * @param container
	 * @return true if out of bounds, false if in bounds.
	 */
	public static boolean isOutOfXBounds(Bounds object, Bounds container) {
		double leftX = container.getMinX();
		double rightX = container.getMaxX();
		double objectLeftX = object.getMinX();
		double objectRightX = object.getMaxX();
		if (objectLeftX < leftX) {
			return true;
		} else if (objectRightX > rightX) {
			return true;
		}
		return false;
	}

	/**
	 * Checks whether an object is within y bounds of its container.
	 * 
	 * @param object
	 * @param container
	 * @return true if out of bounds, false if in bounds.
	 */
	public static boolean isOutOfYBounds(Bounds object, Bounds container) {
		double topY = container.getMinY();
		double bottomY = container.getMaxY();
		double objectTopY = object.getMinY();
		double objectBottomY = object.getMaxY();
		if (objectTopY < topY) {
			return true;
		}
		if (objectBottomY > bottomY) {
			return true;
		}
		return false;
	}

	public static void updateCharts(ArrayList<InertialBridge> objectList, double UPDATE_AFTER_SECONDS, int MAX_SECONDS) {
		for(InertialBridge object : objectList) {
			ObservableList<Series<Number, Number>> data = object.getInertialAttributes().getPositionChart().getData();
			Point2d position = object.getPosition();
			// if there are no series yet, set them up
			if(data.size() == 0) {
				XYChart.Series<Number, Number> xPositionSeries = new XYChart.Series<Number, Number>();
				XYChart.Series<Number, Number> yPositionSeries = new XYChart.Series<Number, Number>();
				xPositionSeries.setName(X_POSITION_IDENTIFIER);
				yPositionSeries.setName(Y_POSITION_IDENTIFIER);
				
				xPositionSeries.getData().add(new XYChart.Data<Number, Number>(0, position.x));
				yPositionSeries.getData().add(new XYChart.Data<Number, Number>(0, position.y));
				
				data.add(xPositionSeries);
				data.add(yPositionSeries);
			// otherwise, add the next datapoint
			} else {
				for(XYChart.Series<Number, Number> series : data) {
					int seriesSize = series.getData().size();
					double currentX = series.getData().get(seriesSize - 1).getXValue().doubleValue()+ UPDATE_AFTER_SECONDS;
					double MAX_DATA_POINTS=  Math.ceil(MAX_SECONDS/UPDATE_AFTER_SECONDS);
					if(series.getName().equals(X_POSITION_IDENTIFIER)) {
						series.getData().add(new XYChart.Data<Number, Number>(currentX, position.x));
					} else if(series.getName().equals(Y_POSITION_IDENTIFIER)) {
						series.getData().add(new XYChart.Data<Number, Number>(currentX, InertialApplication.getMainController().getInertialPane().getBoundsInLocal().getMaxY()-position.y));
					}
					// remove points to keep us at no more than MAX_DATA_POINTS
			        if (series.getData().size() > MAX_DATA_POINTS) {
			        	series.getData().remove(0, series.getData().size() - (int)MAX_DATA_POINTS);
			        }
			        // update 
			        NumberAxis axis =  (NumberAxis) series.getChart().getXAxis();
			        axis.setLowerBound(currentX-MAX_SECONDS);
			        axis.setUpperBound(currentX);
			        
				}
			}
		}
	}
	
	public static void handleCollisions(ArrayList<InertialBridge> objectList) {
		List<Pair<InertialBridge, InertialBridge>> colliders = CollisionDetectionService.findIntersectingObjects(objectList);
		for(Pair<InertialBridge, InertialBridge> collidingPair : colliders) {
			CollisionEvaluationService.evaluateCollidingPair(collidingPair);
		}
	}

}
