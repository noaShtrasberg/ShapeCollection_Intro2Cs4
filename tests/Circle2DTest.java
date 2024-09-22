package Exe.Ex4.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;

class Circle2DTest {
	@BeforeEach
	void reset() {
		Circle2D c = new Circle2D(2,5,3);	
	}

	@Test
	void testCircle2D() {
		Point2D cen = new Point2D(2,5);
		Point2D onC = new Point2D(2,8);
		Point2D notC = new Point2D(2,9);
		Circle2D c = new Circle2D(2,5,3);
		Point2D[] getPoints = c.getPoints();
		Point2D[] PointsC = new Point2D[2];
		PointsC[0] = cen;
		PointsC[1] = onC;
	    assertArrayEquals(PointsC,getPoints);
	    PointsC[1] = notC;
	    //assertArrayEquals(PointsC,getPoints); //should fail	
	}

	@Test
	void testGetRadius() {
		Circle2D c = new Circle2D(2,5,3);
		double rad = c.getRadius();
		assertEquals(rad,3);
	}
	
	@Test
	void testGetCenter() {
		Circle2D c = new Circle2D(2,5,3);
		Point2D getC = c.getCenter();
		Point2D center = new Point2D(2,5);
		assertEquals(center,getC);
	}

	@Test
	void testToString() {
		Circle2D c = new Circle2D(2,5,3);
		String strC = c.toString();
		String myStrC = "(2.0,5.0),3.0";
		assertEquals(strC,myStrC);
	}

	@Test
	void testContains() {
		Circle2D c = new Circle2D(2,5,3);
		Point2D onC = new Point2D(2,8);
		Point2D notOnC = new Point2D(2,8.1);
		boolean in = c.contains(onC);
		boolean out = c.contains(notOnC);
		assertEquals(in,!out);
	}

	@Test
	void testArea() {
		Circle2D c = new Circle2D(2,5,3);
		double rad = c.getRadius();
		double a = Math.PI*(Math.pow(rad, 2));
		assertEquals(a,c.area());
	}

	@Test
	void testPerimeter() {
		Circle2D c = new Circle2D(2,5,3);
		double rad = c.getRadius();
		double p = 2*Math.PI*rad;
		assertEquals(p,c.perimeter());
	}

	@Test
	void testMove() {
		Circle2D c = new Circle2D(2,5,3);
		Point2D vector = new Point2D(4,3);
		c.move(vector);
		Point2D[] arrC = c.getPoints();
		assertEquals(arrC[0].x(),6.0);
		assertEquals(arrC[0].y(),8.0);

		Point2D inNewC = new Point2D(7,7); //this point should be only in the new circle
		assertTrue(c.contains(inNewC));
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPoints() {
		Point2D cen = new Point2D(2,5);
		Point2D onC = new Point2D(2,8);
		Point2D notOnC = new Point2D(2,9);
		Circle2D c = new Circle2D(2,5,3);
		Point2D[] getPoints = c.getPoints();
		Point2D[] PointsC = new Point2D[2];
		PointsC[0] = cen;
		PointsC[1] = onC;
	    assertArrayEquals(PointsC,getPoints);
	    PointsC[1] = notOnC;
	    //assertArrayEquals(PointsC,getPoints); //should fail	
	}

	@Test
	void testScale() {
		Circle2D c = new Circle2D(2,5,3);
		Point2D origin = new Point2D(0,0);
		Circle2D c09 = new Circle2D(2,5,3*0.9);
		Circle2D c11 = new Circle2D(2,5,3*1.1);
		c.scale(origin, 0.9);
		assertEquals(c.area(),c09.area());
		c = new Circle2D(2,5,3.0);
		c.scale(origin, 1.1);
		assertEquals(c.area(),c11.area());
	}

	@Test
	void testRotate() {
		Circle2D c = new Circle2D(8,5,2);
		Point2D cenRotate = new Point2D(5,5);
		c.rotate(cenRotate, 180);
		Point2D shouldNewCen = new Point2D(2,5);
		assertEquals(c.getCenter(),shouldNewCen);
	}

}
