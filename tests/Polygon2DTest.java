package Exe.Ex4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;

class Polygon2DTest {

	@Test
	void testPolygon2D() {
		fail("Not yet implemented");
	}

	@Test
	void testPolygon2DPolygon2D() {
		fail("Not yet implemented");
	}

	@Test
	void testAdd() {
		Polygon2D polygon = new Polygon2D();
		int size = polygon.getLength();
		Point2D p1 = new Point2D(2,8);
		polygon.add(p1);
		boolean flag = false;
		Point2D lastP = polygon.getPointByindex(polygon.getLength()-1);
		if(polygon.getLength()==(size+1)) {
			if(lastP.x()==2 && lastP.y()==8)
				flag = true;
		}
		assertTrue(flag);
	}

	@Test
	void testToString() {
		Point2D p1 = new Point2D(2,8);
		Point2D p2 = new Point2D(9,4);
		Point2D p3 = new Point2D(5,3);
		Polygon2D polygon = new Polygon2D();
		polygon.add(p1);
		polygon.add(p2);
		polygon.add(p3);
		String strP = polygon.toString();
		String myStrP = "(2.0,8.0),(9.0,4.0),(5.0,3.0)";
		assertEquals(strP,myStrP);
	}

	@Test
	void testContains() {
		Point2D p1 = new Point2D(2,8);
		Point2D p2 = new Point2D(9,4);
		Point2D p3 = new Point2D(5,3);
		Polygon2D polygon = new Polygon2D();
		polygon.add(p1);
		polygon.add(p2);
		polygon.add(p3);
		Point2D onP = new Point2D(5,4);
		Point2D notOnP = new Point2D(1.9,8);
		boolean in = polygon.contains(onP);
		boolean out = polygon.contains(notOnP);
		assertEquals(in,!out);
	}

	@Test
	void testArea() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(7,6);
		Point2D p4 = new Point2D(2,6);
		Polygon2D polygon = new Polygon2D();
		polygon.add(p1);
		polygon.add(p2);
		polygon.add(p3);
		polygon.add(p4);
		double area = polygon.area();
		System.out.println("the area is:"+area);
		assertEquals(area,20.0);
	}

	@Test
	void testPerimeter() {
		fail("Not yet implemented");
	}

	@Test
	void testMove() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);
		Polygon2D polygon = new Polygon2D();
		polygon.add(p1);
		polygon.add(p2);
		polygon.add(p3);
		Point2D vector = new Point2D(4,3);
		polygon.move(vector);
		Point2D[] arrP = polygon.getPoints();
		Point2D[] myArrP = new Point2D[3];
		myArrP[0] = new Point2D(6.0,5.0);
		myArrP[1] = new Point2D(11.0,5.0);
		myArrP[2] = new Point2D(10.0,9.0);
		assertArrayEquals(arrP,myArrP);

		Point2D inNewP = new Point2D(10,7); //this point should be only in the new polygon
		assertTrue(polygon.contains(inNewP));
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testScale() {
		fail("Not yet implemented");
	}

	@Test
	void testRotate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPoints() {
		fail("Not yet implemented");
	}

}
