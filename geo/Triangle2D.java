package Exe.Ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;

	//Constructor for Triangle2D, get three vertices
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
		this._p3 = new Point2D(p3);
	}

	@Override
	public String toString()
	{ return _p1.toString()+ "," + _p2.toString() + "," + _p3.toString();}

	@Override
	public boolean contains(Point2D ot) {
		//The triangle contains the point ot only if the sum of the three little triangle (a,b,c)
		//it makes with the vertices of the big triangle is equal to the big triangle's area
		Triangle2D a = new Triangle2D(_p1,_p2,ot);
		double areaA = a.area();
		Triangle2D b = new Triangle2D(_p2,_p3,ot);
		double areaB = b.area();
		Triangle2D c = new Triangle2D(_p3,_p1,ot);
		double areaC = c.area();
		double sumAreas = areaA + areaB + areaC;
		if(sumAreas == this.area()) {
			return true;
		}
		return false;
	}

	@Override
	public double area() {
		//area from three points
		//https://www.youtube.com/watch?v=g38HBQQ8Fig
		double area1 = (_p1.x()*_p2.y()) + (_p2.x()*_p3.y()) + (_p3.x()*_p1.y());
		double area2 = (_p1.y()*_p2.x()) + (_p2.y()*_p3.x()) + (_p3.y()*_p1.x());
		double areaT = (Math.abs(area1-area2))*0.5;
		return areaT;
	}

	@Override
	public double perimeter() {
		double side1 = _p1.distance(_p2);
		double side2 = _p2.distance(_p3);
		double side3 = _p3.distance(_p1);
		return side1+side2+side3;
	}

	@Override
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(_p1, _p2, _p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[3];
		ans[0] = new Point2D(_p1);
		ans[1] = new Point2D(_p2);
		ans[2] = new Point2D(_p3);
		return ans;
	}
}
