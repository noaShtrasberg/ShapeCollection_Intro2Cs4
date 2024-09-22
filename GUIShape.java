package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	public GUIShape(String file) {
		this._g=null;
		String [] spliteFile=file.split(",");
		this._color=new Color(Integer.parseInt(spliteFile[1]));
		this._fill=Boolean.parseBoolean(spliteFile[2]);
		this._tag=Integer.parseInt(spliteFile[3]);
		this.init(spliteFile);
	}

	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;

	}

	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	
	@Override
	public String toString() {
		String str = this.getClass().getSimpleName()+","+Math.abs(this._color.getBlue()+(this._color.getGreen()*256)+(this._color.getRed()*256*256))+","+this._fill+","+this._tag+","+this._g.getClass().getSimpleName()+","+_g.toString();
		return str;
	}
	
	private void init(String[] ww) {
		this._color = new Color(Integer.parseInt(ww[1]));
		this._fill = Boolean.parseBoolean(ww[2]);
		this._tag = Integer.parseInt(ww[3]);
		if(ww[4].equals("Segment2D")) {
			Point2D p1 = new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
			Point2D p2 = new Point2D(Double.parseDouble(ww[7]),Double.parseDouble(ww[8]));
			_g = new Segment2D(p1,p2);
		}
		if(ww[4].equals("Circle2D")) {
			Point2D center = new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
			double radius = Double.parseDouble(ww[7]);
			_g = new Circle2D(center,radius);
		}
		if(ww[4].equals("Triangle2D")) {
			Point2D p1 = new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
			Point2D p2 = new Point2D(Double.parseDouble(ww[7]),Double.parseDouble(ww[8]));
			Point2D p3 = new Point2D(Double.parseDouble(ww[9]),Double.parseDouble(ww[10]));
			_g = new Triangle2D(p1,p2,p3);
		}
		if(ww[4].equals("Rect2D")) {
			Point2D p1 = new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
			Point2D p2 = new Point2D(Double.parseDouble(ww[7]),Double.parseDouble(ww[8]));
			Point2D p3 = new Point2D(Double.parseDouble(ww[9]),Double.parseDouble(ww[10]));
			Point2D p4 = new Point2D(Double.parseDouble(ww[11]),Double.parseDouble(ww[12]));
			_g = new Rect2D(p1,p2,p3,p4);
		}
		if(ww[4].equals("Polygon2D")) {
			Polygon2D poly = new Polygon2D();
			for(int i=5 ; i<ww.length ; i+=2) {
				Point2D p = new Point2D(Double.parseDouble(ww[i]),Double.parseDouble(ww[i+1]));
				poly.add(p);
			}
			_g = poly;
		}
	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	public void setShape(GeoShapeable g) {
		_g = g;
	}
}
