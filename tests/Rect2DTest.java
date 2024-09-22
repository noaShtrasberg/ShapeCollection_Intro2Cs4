package Exe.Ex4.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;

class Rect2DTest {

	@Test
	void testRect2D() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Point2D p3=new Point2D(0,5);
		Point2D p4=new Point2D(5,0);
		Rect2D r1=new Rect2D(p1,p2);
		ArrayList <Point2D> arr=new ArrayList <Point2D>();
		arr.add(p1);
		arr.add(p2);
		arr.add(p3);
		arr.add(p4);
		boolean contain=true;
		Point2D [] arr1=r1.getAllPoints();
		for(int i=0;i<4;i++)
		{
			if(!arr.contains(arr1[i]))
			{
				contain=false;
			}
		}
		assertEquals(contain,true);
	}

	@Test
	void testToString() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(2,6);
		Point2D p4 = new Point2D(7,6);
		Rect2D r = new Rect2D(p1,p2,p3,p4);
		String strT = r.toString();
		String myStrT = "(2.0,2.0),(7.0,2.0),(2.0,6.0),(7.0,6.0)";
		assertEquals(strT,myStrT);
	}
	
	@Test
	void testContains() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Rect2D r1=new Rect2D(p1,p2);
		Point2D p3=new Point2D(7,7);
		assertTrue(!r1.contains(p3));
	}

	@Test
	void testArea() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Rect2D r1=new Rect2D(p1,p2);
		assertEquals(r1.area(),25.0);
	}

	@Test
	void testPerimeter() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Rect2D r1=new Rect2D(p1,p2);
		assertEquals(r1.perimeter(),20.0);
	}

	@Test
	void testMove() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Rect2D r1=new Rect2D(p1,p2);
		Point2D vec=new Point2D(1,1);
		r1.move(vec);
		Point2D [] arr=r1.getAllPoints();
		assertEquals(arr[0].x(),6.0);
		assertEquals(arr[0].y(),6.0);
		assertEquals(arr[1].x(),6.0);
		assertEquals(arr[1].y(),1.0);
		assertEquals(arr[2].x(),1.0);
		assertEquals(arr[2].y(),1.0);
		assertEquals(arr[3].x(),1.0);
		assertEquals(arr[3].y(),6.0);
	}

	@Test
	void testCopy() {
		Point2D p1=new Point2D(2,3);
		Point2D p2=new Point2D(6,7);
		Rect2D r1=new Rect2D(p1,p2);
		GeoShapeable newr=r1.copy();
		Point2D [] arr =newr.getPoints();
		assertEquals(arr[1].x(),p1.x());
		assertEquals(arr[1].y(),p1.y());
		assertEquals(arr[0].x(),p2.x());
		assertEquals(arr[0].y(),p2.y());


	}

	@Test
	void testScale() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Rect2D r1=new Rect2D(p1,p2);
		Point2D p3=new Point2D(4,4);
		r1.scale(p3, 0.9);
		if(r1.area()>30.0) {
			fail("the area is not true");
		}
	}

	@Test
	void testRotate() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Rect2D r1=new Rect2D(p1,p2);
		Rect2D r2=new Rect2D(p1,p2);
		Point2D pCenter=new Point2D(2.5,2.5);
		r1.rotate(pCenter, 90);
		Point2D [] arrR1=r1.getAllPoints();
		Point2D [] arrR2=r2.getAllPoints();
		assertEquals(arrR1[3].x(),arrR2[2].x());
		assertEquals(arrR1[3].y(),arrR2[2].y());
	}

	@Test
	void testGetPoints() {
		Point2D p1=new Point2D(2.0,3.0);
		Point2D p2=new Point2D(5.0,8.0);
		Rect2D r1=new Rect2D(p1,p2);
		Point2D [] arr=r1.getPoints();
		if(!arr[0].equals(p2)&&arr[1].equals(p1)) {
			fail("the points need to be (2,3) and (5,8)");
		}
	}
}

