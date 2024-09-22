package Exe.Ex4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


class Segment2DTest {

	@Test
	void testSegment2D() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Segment2D s1=new Segment2D(p1,p2);
		boolean contain=true;
		Point2D pContain=new Point2D(2,2);
		contain=s1.contains(pContain);
		assertEquals(contain,true);
	}
	
	@Test
	void testToString() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,5);
		Segment2D s = new Segment2D(p1,p2);
		String strT = s.toString();
		String myStrT = "(2.0,2.0),(7.0,5.0)";
		assertEquals(strT,myStrT);
	}

	@Test
	void testContains() {
		Point2D p1=new Point2D(1,1);
		Point2D p2=new Point2D(3,3);
		Segment2D s1=new Segment2D(p1,p2);
		boolean contain=true;
		Point2D notContain=new Point2D(10,2);
		contain=s1.contains(notContain);
		assertEquals(contain,false);

	}

	@Test
	void testArea() {
		Point2D p1=new Point2D(1,1);
		Point2D p2=new Point2D(3,3);
		Segment2D s1=new Segment2D(p1,p2);
		double area=s1.area();
		assertEquals(0,area);
	}

	@Test
	void testPerimeter() {
		Point2D p1=new Point2D(1,1);
		Point2D p2=new Point2D(3,3);
		Segment2D s1=new Segment2D(p1,p2);
		double area=s1.area();
		assertEquals(0,area);
	}

	@Test
	void testMove() {
		Point2D p1=new Point2D(1,1);
		Point2D p2=new Point2D(3,3);
		Segment2D s1=new Segment2D(p1,p2);
		Point2D vec=new Point2D(1,1);
		s1.move(vec);
		Point2D [] arr=s1.getPoints();
		assertEquals(arr[0].x(),2.0);
		assertEquals(arr[0].y(),2.0);
		assertEquals(arr[1].x(),4.0);
		assertEquals(arr[1].y(),4.0);
	}

	@Test
	void testCopy() {
		Point2D p1=new Point2D(2,3);
		Point2D p2=new Point2D(6,7);
		Segment2D s1=new Segment2D(p1,p2);
		GeoShapeable news=s1.copy();
		Point2D [] arr =news.getPoints();
		assertEquals(arr[0].x(),p1.x());
		assertEquals(arr[0].y(),p1.y());
		assertEquals(arr[1].x(),p2.x());
		assertEquals(arr[1].y(),p2.y());
	}

	@Test
	void testScale() {
		Point2D p1=new Point2D(0,0);
		Point2D p2=new Point2D(5,5);
		Segment2D s1=new Segment2D(p1,p2);
		s1.scale(p1, 0.9);
		if(s1.perimeter()<12)
		{
			fail("the area is not true");
		}
	}
	@Test
	void testRotate() {
		Point2D p1 = new Point2D(2,6);
		Point2D p2 = new Point2D(8,6);
		Segment2D s1=new Segment2D(p1,p2);
		Point2D p3 = new Point2D(5,3);
		Point2D p4 = new Point2D(5,9);
		Segment2D s2=new Segment2D(p3,p4);
		Point2D pCenter=new Point2D(5,6);
		s1.rotate(pCenter, 90);
		Point2D[] getPs1 = s1.getPoints();
		Point2D[] getPs2 = s2.getPoints();
		for(int i=0 ; i<getPs1.length ; i++) {
			assertEquals(getPs1[i].x(),getPs2[i].x(),0.001);
			assertEquals(getPs1[i].y(),getPs2[i].y(),0.001);
		}
	}

	@Test
	void testGetPoints() {
		Point2D p1=new Point2D(2.0,3.0);
		Point2D p2=new Point2D(5.0,8.0);
		Segment2D s1=new Segment2D(p1,p2);
		Point2D [] arr=s1.getPoints();
		if(!(arr[0].equals(p1)&&arr[1].equals(p2)))	{
			fail("the points need to be (2,3) and (5,8)"); }
		}
}

