package Exe.Ex4.geo;

import java.awt.geom.Line2D;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;

	//Constructor for Segment2D, get two points
	public Segment2D(Point2D p1, Point2D p2) {
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
	}

	@Override
	public String toString()
	{ return _p1.toString()+ "," + _p2.toString();}

	@Override
	public boolean contains(Point2D ot) {
		double distOt_p1=ot.distance(_p1);
		double distOt_p2=ot.distance(_p2);
		if((distOt_p1+distOt_p2)==_p1.distance(_p2))
			return true;
		else return false;
	}

	public static boolean cutSegments(Point2D p1,Point2D p2,Point2D p3,Point2D p4){
		Line2D line1=new Line2D.Double(p1.x(),p1.y(),p2.x(),p2.y());
		Line2D line2=new Line2D.Double(p3.x(),p3.y(),p4.x(),p4.y());
		if(line1.intersectsLine(line2))
		{
			return true;
		}
		return false;

	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return 2*_p1.distance(_p2);
	}

	@Override
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Segment2D(_p1, _p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
	}
	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[2];
		ans[0] = new Point2D(this._p1);
		ans[1] = new Point2D(this._p2);
		return ans;
	}
}