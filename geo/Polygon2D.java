package Exe.Ex4.geo;

import java.util.ArrayList;

import javax.swing.text.Segment;
import Exe.Ex4.geo.Segment2D;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 *
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	private ArrayList<Point2D> _polygon;

	//Constructor for Polygon2D, dynamic amount of points
	public Polygon2D() {
		this._polygon = new ArrayList<Point2D>();
	}

	public Polygon2D(Polygon2D p) {
		this();
		if(p.getLength()>0){
			for(int i=0 ; i<p.getLength() ; i++) {
				this._polygon.add(new Point2D(p._polygon.get(i)));
			}
		}
	}

	public void add(Point2D p)
	{
		this._polygon.add(p);
	}

	@Override
	public String toString() {
		String poly = this._polygon.get(0).toString();
		for(int i=1 ; i<_polygon.size() ; i++) {
			poly = poly + "," + this._polygon.get(i).toString();
		}
		return poly;
	}

	@Override
	public boolean contains(Point2D ot) {
		Point2D [] arrPoints=ExtreemPoints();
		Point2D pmax=arrPoints[0];
		Point2D pmin=arrPoints[1];
		int CountCut=0;
		//in this case we check if the point is inside the polygon with the extreem Points.
		if(!(ot.x() > pmax.x() && ot.x() < pmin.x() && ot.y() > pmax.y() && ot.y() < pmin.y())){
			Point2D pleft=new Point2D(pmin.x()-1,ot.y());
			if(Segment2D.cutSegments(ot,pleft,_polygon.get(0),_polygon.get(_polygon.size()-1)))
				CountCut++;
			for(int i=0;i<_polygon.size()-1;i++){
				if(Segment2D.cutSegments(ot,pleft,_polygon.get(i),_polygon.get(i+1))){
					CountCut++;
				}
			}
		}
		if(CountCut!=0 && CountCut%2!=0)
			return true;
		return false;
	}

	//this is function that return points array in size 2, the first variable is the max point
	//the secont one is the min point.
	public Point2D [] ExtreemPoints() {
		Point2D [] arrPoints=new Point2D[2];
		if(!_polygon.isEmpty()){
			double maxX=_polygon.get(0).x();
			double maxY=_polygon.get(0).y();
			double minY=_polygon.get(0).y();
			double minX=_polygon.get(0).x();

			for(int i=1;i<_polygon.size();i++)
			{
				if(_polygon.get(i).x()> maxX){
					maxX=_polygon.get(i).x();
				}
				if(_polygon.get(i).x()<minX) {
					minX=_polygon.get(i).x();
				}
				if(_polygon.get(i).y()<minY) {
					minY=_polygon.get(i).y();
				}
				if(_polygon.get(i).y()>maxY) {
					maxY=_polygon.get(i).y();
				}
			}
			Point2D pMax=new Point2D(maxX,maxY);
			Point2D pMin=new Point2D(minX,minY);
			arrPoints[0]=pMax;
			arrPoints[1]=pMin;

		}
		return arrPoints;
	}
	@Override
	public double area() {
		//https://he.wikipedia.org/wiki/%D7%A0%D7%95%D7%A1%D7%97%D7%AA_%D7%94%D7%A9%D7%A8%D7%95%D7%9A
		double sum1 = _polygon.get(_polygon.size()-1).x()*(_polygon.get(0).y());
		double sum2 = _polygon.get(0).x()*(_polygon.get(_polygon.size()-1).y());
		int size =_polygon.size();
		for (int i=0; i<(size-1); i++) {
			sum1 = sum1 + (_polygon.get(i).x() * _polygon.get(i+1).y());
			sum2 = sum2 + (_polygon.get(i+1).x() * _polygon.get(i).y());
		}
		return 0.5*(Math.abs(sum1-sum2));
	}

	@Override
	public double perimeter() {
		double peri = _polygon.get(_polygon.size()-1).distance(_polygon.get(0));
		for(int i=1 ; i<_polygon.size() ; i++) {
			peri = peri + _polygon.get(i-1).distance(_polygon.get(i));
		}
		System.out.println("The perimeter is:" + peri);
		return peri;
	}

	@Override
	public void move(Point2D vec) {
		for(int i=0 ; i<_polygon.size() ; i++) {
			_polygon.get(i).move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		return new Polygon2D(this);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		for(int i=0 ; i<_polygon.size() ; i++) {
			_polygon.get(i).scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for(int i=0 ; i<_polygon.size() ; i++) {
			_polygon.get(i).rotate(center, angleDegrees);
		}
	}
	public Point2D getPointByindex(int i) {
		return _polygon.get(i);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[_polygon.size()];
		for(int i=0 ; i<_polygon.size() ; i++) {
			ans[i] = new Point2D(_polygon.get(i));
		}
		return ans;
	}
	public int getLength() {
		if(_polygon.isEmpty()==true)
			return 0;
		int size=_polygon.size();
		return size;
	}
}