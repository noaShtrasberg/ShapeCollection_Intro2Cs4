package Exe.Ex4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Point2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	public GUI_Shapeable get(int i) {
		if(i>=0 && i<_shapes.size()) {
			return _shapes.get(i);
		}
		return null;
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		GUI_Shapeable element = _shapes.get(i);
		for(int j=i+1; j<_shapes.size() ; j++) {
			_shapes.get(j).setTag(_shapes.get(j).getTag()-1);
		}
		_shapes.remove(i);
		return element;
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		if(s!=null && s.getShape()!=null) {
			for(int j=i+1; j<_shapes.size() ; j++) {
				_shapes.get(j).setTag(_shapes.get(j).getTag()+1);
			}
			_shapes.add(i, s);
			_shapes.get(i).setTag(i);
		}
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() {
		ShapeCollectionable s = null;
		for(int i=0 ; i<_shapes.size() ; i++) {
			s.add(_shapes.get(i));
		}
		return s;
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes);
	}

	@Override
	public void save(String file) {
		try {
			FileWriter save=new FileWriter(file);
			for(int i=0;i< this._shapes.size(); i++) {
				save.write((this._shapes.get(i).toString()+"\n"));
			}
			save.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String file) {
		try {
			Scanner scanner=new Scanner(new File(file));
			this._shapes.clear();
			while(scanner.hasNext()) {
				this._shapes.add(new GUIShape(scanner.nextLine()));
			}
			scanner.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans;
		double minX = 100;
		double maxX = 0;
		double minY = 100;
		double maxY = 0;
		for(int i=0 ; i<_shapes.size() ; i++) {
			Point2D[] shapeP = _shapes.get(i).getShape().getPoints();
			for(int j=0 ; j<shapeP.length ; j++) {
				if(shapeP[j].x()<minX) {
					minX = shapeP[j].x();
				}
				if(shapeP[j].x()>maxX) {
					maxX = shapeP[j].x();
				}
				if(shapeP[j].y()<minY) {
					minY = shapeP[j].y();
				}
				if(shapeP[j].y()>maxY) {
					maxY = shapeP[j].y();
				}
			}
		}
		Point2D p1 = new Point2D(maxX, maxY); 
		Point2D p3 = new Point2D(minX, minY);
		ans = new Rect2D(p1, p3);
		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}


}
