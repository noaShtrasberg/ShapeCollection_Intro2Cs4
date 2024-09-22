package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	private Point2D _p1; //rightUp
	private Point2D _p2; //rightDown
	private Point2D _p3; //leftDown
	private Point2D _p4; //leftUp
	private Point2D _pCen; //the center of the rectangle
	
	//Constructor for Rect2D, get four vertices
	public Rect2D(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
		this._p3 = new Point2D(p3);
		this._p4 = new Point2D(p4);
	}
	
	//Constructor for Rect2D, get two vertices
	public Rect2D(Point2D p1, Point2D p2) {
		double maxX = Math.max(p1.x(), p2.x());
		double minX = Math.min(p1.x(), p2.x());
		double maxY = Math.max(p1.y(), p2.y());
		double minY = Math.min(p1.y(), p2.y());
		this._p1 = new Point2D(maxX,maxY);
		this._p2 = new Point2D(maxX,minY);
		this._p3 = new Point2D(minX,minY);
		this._p4 = new Point2D(minX,maxY);
		this._pCen = new Point2D((_p1.x()+_p3.x())/2,(_p1.y()+_p3.y())/2);
	}
	
	public Point2D[] getAllPoints() {
		Point2D [] arr = new Point2D[4];
		arr[0]=_p1;
		arr[1]=_p2;
		arr[2]=_p3;
		arr[3]=_p4;
		return arr;
	}
	
	 @Override
	    public String toString()
	    { return _p1.toString()+ "," + _p2.toString() + "," + _p3.toString() + "," + _p4.toString();}

	@Override
	public boolean contains(Point2D ot) {
		
		double halfDia = _p1.distance(_p3)/2; //the length of half the diagonal 
		double halfS1 = _p1.distance(_p2)/2; //the length of half the side1
		double halfS2 = _p2.distance(_p3)/2; //the length of half the side2
		double dist = ot.distance(_pCen); //the distance between ot to the _pCen
		if(dist<=halfDia && dist<=halfS1 && dist<=halfS2) {
			return true;
		}
		return false;
	}
	
	@Override
	public double area() {
		double side1 = _p1.distance(_p2);
		double side2 = _p2.distance(_p3);
		return side1*side2;
	}

	@Override
	public double perimeter() {
		double side1 = _p1.distance(_p2);
		double side2 = _p2.distance(_p3);
		return (side1+side2)*2;
	}

	@Override
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
		_p4.move(vec); 
	}

	@Override
	public GeoShapeable copy() {
		return new Rect2D(_p1, _p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
		this._p4.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
		this._p4.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[2];
		ans[0] = new Point2D(_p1);
		ans[1] = new Point2D(_p3);
		return ans;
	}
}
