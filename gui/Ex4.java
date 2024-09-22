package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.MouseEvent;
import java.io.File;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 *ID: 
 *209325703
 *315158568
 */
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1;
	private  Point2D _p2;
	private  Polygon2D _polygon;
	private int _tag = 0;

	private  static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);}

	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}

	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}

		if(gs instanceof Rect2D) {
			Rect2D r = (Rect2D)gs;
			Point2D[] arr = r.getAllPoints();
			double[] x = {arr[0].x(),arr[1].x(),arr[2].x(),arr[3].x()};
			double[] y = {arr[0].y(),arr[1].y(),arr[2].y(),arr[3].y()};
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else { 
				StdDraw_Ex4.polygon(x, y);
			}
		}

		if(gs instanceof Segment2D) {
			Segment2D s = (Segment2D)gs;
			Point2D p1 = s.getPoints()[0];
			Point2D p2 = s.getPoints()[1];
			double[] x = {p1.x(), p2.x()};
			double[] y = {p1.y(), p2.y()};
			StdDraw_Ex4.polygon(x, y);
		}

		if(gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D)gs;
			Point2D p1 = t.getPoints()[0];
			Point2D p2 = t.getPoints()[1];
			Point2D p3 = t.getPoints()[2];
			double[] x = {p1.x(), p2.x(), p3.x()};
			double[] y = {p1.y(), p2.y(), p3.y()};
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else { 
				StdDraw_Ex4.polygon(x, y);
			}
		}

		if(gs instanceof Polygon2D) {
			Polygon2D p = (Polygon2D)gs;
			double [] _xp= new double [p.getLength()];
			double [] _yp= new  double [p.getLength()];
			for(int i=0;i<p.getLength();i++) {
				_xp[i]=p.getPointByindex(i).x();
				_yp[i]=p.getPointByindex(i).y();
			}
			if(isFill) {
				StdDraw_Ex4.filledPolygon(_xp, _yp);
			}
			else {
				StdDraw_Ex4.polygon(_xp,_yp);
			}
		}
	}

	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}

		if(p.equals("ByArea")) {_shapes.sort(ShapeComp.CompByArea);}
		if(p.equals("ByAntiArea")) {_shapes.sort(ShapeComp.CompByAntiArea);}
		if(p.equals("ByPerimeter")) {_shapes.sort(ShapeComp.CompByPerimeter);}
		if(p.equals("ByAntiPerimeter")) {_shapes.sort(ShapeComp.CompByAntiPerimeter);}
		if(p.equals("ByToString")) {_shapes.sort(ShapeComp.CompByToString);}
		if(p.equals("ByAntiToString")) {_shapes.sort(ShapeComp.CompByAntiToString);}
		if(p.equals("ByTag")) {_shapes.sort(ShapeComp.CompByTag);}
		if(p.equals("ByAntiTag")) {_shapes.sort(ShapeComp.CompByAntiTag);}

		if(p.equals("Clear")) {
			_shapes.removeAll();
			_tag = 0;
		}
		if(p.equals("All")) {
			for(int i=0;i<_shapes.size();i++)
			{
				_shapes.get(i).setSelected(true);
			}
		}
		if(p.equals("None")) {
			for(int i=0;i<_shapes.size();i++)
			{
				_shapes.get(i).setSelected(false);
			}
		}
		if(p.equals("Anti")) {
			for(int i=0;i<_shapes.size();i++)
			{
				if(_shapes.get(i).isSelected()==true)
					_shapes.get(i).setSelected(false);
				else _shapes.get(i).setSelected(true);
			}
		}
		if(p.equals("Remove")) {
			for(int i=0;i<_shapes.size();i++)
			{
				if(_shapes.get(i).isSelected()==true)
					_shapes.removeElementAt(i);
			}
		}
		if(p.equals("Save")) {save();}
		if(p.equals("Load")) {load();}
		if(p.equals("Info")) {
			System.out.println(getInfo());
		}
		
		drawShapes();
	}

	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+" "+p);
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_tag);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_tag++;
			}
		}
		if(_mode.equals("Rect")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_tag);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_tag++;
			}
		}
		if(_mode.equals("Segment")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setTag(_tag);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_tag++;
			}
		}
		if(_mode.equals("Triangle")) {
			if(_gs==null || _p2==null) {
				if(_p1==null) {
					_p1 = new Point2D(p);
				}
				else {_p2 = new Point2D(p);}
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_tag);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;
				_tag++;
			}
		}
		if(_mode.equals("Polygon")) {
			if (_p1==null) {
				_polygon=new Polygon2D();
			}
			_p1=new Point2D(p);
			_polygon.add(_p1);
		}
		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}
		if(_mode.equals("Copy"))
		{
			if(_p1== null) {
				_p1= new Point2D(-p.x(),-p.y());
			}
			else {
				int size = _shapes.size();
				for(int i=0; i<size; i++) {
					GUI_Shapeable m=_shapes.get(i);
					if(m.isSelected()) {
						GeoShapeable g =m.getShape();
						GeoShapeable d= g.copy();
						d.move(_p1);
						d.move(p);
						_gs = new GUIShape(d,false, Color.pink,0);
						_gs.setColor(m.getColor());
						_gs.setFilled(m.isFilled());
						_gs.setTag(_tag);
						_shapes.add(_gs);
						_gs=null;
					}
				}
				_p1 = null;
			}
		}
		if(_mode.equals("Scale_90%")) {
			_p1 = new Point2D(p);
			scale(0.9);
		}
		if(_mode.equals("Scale_110%")) {
			_p1 = new Point2D(p);
			scale(1.1);
		}
		if(_mode.equals("Rotate")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				double angle = Math.atan2(p.y()-_p1.y(), p.x()-_p1.x());
				double degrees = Math.toDegrees(angle);
				rotate(degrees);
				_p1 = null;
			}
		}
		if(_mode.equals("Point")) {
			select(p);
		}

		drawShapes();
	}

	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}

	private void Copy(double angledegrees) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.copy();
				_tag++;
			}
		}
	}
	private void load() {
		FileDialog file=new FileDialog(StdDraw_Ex4.getFrame(),"Load",0);
		file.setVisible(true);
		if(file.getFile()!=null) {
			this._shapes.load(file.getDirectory()+File.separator+file.getFile());
		}
	}
	private void save() {
		FileDialog file=new FileDialog(StdDraw_Ex4.getFrame(),"Save",1);
		file.setVisible(true);
		if(file.getFile()!=null) {
			this._shapes.save(file.getDirectory()+File.separator+file.getFile());
		}
	}

	private void scale(double ratio) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.scale(_p1, ratio);
			}
		}
	}

	private void rotate(double angleDegrees) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.rotate(_p1, angleDegrees);
			}
		}
	}

	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
		if(_polygon!=null && _mode.equals("Polygon"));{
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_shapes.add(new GUIShape(this._polygon,this._fill,this._color, _tag));
			_tag++;
			System.out.println(this._polygon.toString());
			_gs = null;
			_p1 = null;
			_polygon=null;
			drawShapes();
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			//	System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1,y1);
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}
			if(_mode.equals("Rect")) {
				gs = new Rect2D(_p1,p);
			}
			if(_mode.equals("Segment")) {
				gs = new Segment2D(_p1,p);
			}
			if(_mode.equals("Triangle")) {
				if(_p2==null) {
					gs = new Segment2D(_p1,p);
				}
				else {
					gs = new Triangle2D(_p1,_p2,p);
				}
			}
			if(_mode.equals("Polygon")) {
				if(_polygon==null) {
					this._polygon=new Polygon2D();
					this._polygon.add(_p1);
					_p1=null;
				}

				gs=new Polygon2D(_polygon);
				((Polygon2D)gs).add(p);
			}

			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public ShapeCollectionable getShape_Collection() {
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
