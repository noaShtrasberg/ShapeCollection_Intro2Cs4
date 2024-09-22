package Exe.Ex4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.geo.Point2D;

class Triangle2DTest {
	@BeforeEach
	void reset() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);	
	}
	
	@Test
	void testTriangle2D() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);	
		Point2D notT = new Point2D(2,9);
		Triangle2D t = new Triangle2D(p1,p2,p3);
		Point2D[] getPoints = t.getPoints();
		Point2D[] PointsT = new Point2D[3];
		PointsT[0] = p1;
		PointsT[1] = p2;
		PointsT[2] = p3;
	    assertArrayEquals(PointsT,getPoints);
	    PointsT[2] = notT;
	    //assertArrayEquals(PointsT,getPoints); //should fail	
	}

	@Test
	void testToString() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D t = new Triangle2D(p1,p2,p3);
		String strT = t.toString();
		String myStrT = "(2.0,2.0),(7.0,2.0),(6.0,6.0)";
		assertEquals(strT,myStrT);
	}

	@Test
	void testContains() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D t = new Triangle2D(p1,p2,p3);
		Point2D onT = new Point2D(5,2);
		Point2D notOnT = new Point2D(5,1.9);
		boolean in = t.contains(onT);
		boolean out = t.contains(notOnT);
		assertEquals(in,!out);
	}

	@Test
	void testArea() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D t = new Triangle2D(p1,p2,p3);
		double a = t.area();
		assertEquals(a,10.0);
	}

	@Test
	void testPerimeter() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(5,2);
		Point2D p3 = new Point2D(5,6);
		Triangle2D t = new Triangle2D(p1,p2,p3);
		double pT = t.perimeter();
		assertEquals(pT,12.0);
		Point2D p4 = new Point2D(3,1);
		Point2D p5 = new Point2D(6,1);
		Point2D p6 = new Point2D(6,(1+3*Math.sqrt(3)));
		Triangle2D t2 = new Triangle2D(p4,p5,p6);
		double pT2 = t2.perimeter();
		double myPT2 = 9+(3*Math.sqrt(3));
		assertEquals(pT2,myPT2);
	}

	@Test
	void testMove() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);
		Triangle2D t = new Triangle2D(p1,p2,p3);
		Point2D vector = new Point2D(4,3);
		t.move(vector);
		Point2D[] arrT = t.getPoints();
		Point2D[] myArrT = new Point2D[3];
		myArrT[0] = new Point2D(6.0,5.0);
		myArrT[1] = new Point2D(11.0,5.0);
		myArrT[2] = new Point2D(10.0,9.0);
		assertArrayEquals(arrT,myArrT);

		Point2D inNewT = new Point2D(10,7); //this point should be only in the new triangle
		assertTrue(t.contains(inNewT));
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetPoints() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);	
		Point2D notT = new Point2D(2,9);
		Triangle2D t = new Triangle2D(p1,p2,p3);
		Point2D[] getPoints = t.getPoints();
		Point2D[] PointsT = new Point2D[3];
		PointsT[0] = p1;
		PointsT[1] = p2;
		PointsT[2] = p3;
	    assertArrayEquals(PointsT,getPoints);
	    PointsT[2] = notT;
	    //assertArrayEquals(PointsT,getPoints); //should fail	
	}

	@Test
	void testScale() {
		Point2D p1 = new Point2D(2,2);
		Point2D p2 = new Point2D(7,2);
		Point2D p3 = new Point2D(6,6);	
		Triangle2D t = new Triangle2D(p1,p2,p3);
		Point2D cen = new Point2D(5,9);
		Point2D newp1 = new Point2D(p1.vector(cen).x()*0.9,p1.vector(cen).y()*0.9);
		Point2D newp2 = new Point2D(p2.vector(cen).x()*0.9,p2.vector(cen).y()*0.9);
		Point2D newp3 = new Point2D(p3.vector(cen).x()*0.9,p3.vector(cen).y()*0.9);	
		Triangle2D t09 = new Triangle2D(newp1,newp2,newp3);
		t.scale(cen, 0.9);
		assertEquals(t.area(),t09.area(),0.0001);
	}

	@Test
	void testRotate() {
		Point2D p1 = new Point2D(0,3);
		Point2D p2 = new Point2D(0,0);
		Point2D p3 = new Point2D(6,0);	
		Triangle2D t = new Triangle2D(p1,p2,p3);
		Point2D trRotate = new Point2D(3,1.5);
		t.rotate(trRotate, 180);
		Point2D[] getP = t.getPoints();
		Point2D newp1 = new Point2D(6,0);
		Point2D newp2 = new Point2D(6,3);
		Point2D newp3 = new Point2D(0,3);
		Triangle2D newT = new Triangle2D(newp1,newp2,newp3);
		Point2D[] getNewP = newT.getPoints();
		for(int i=0 ; i<getP.length ; i++) {
			assertEquals(getP[i].x(),getNewP[i].x(),0.001);
			assertEquals(getP[i].y(),getNewP[i].y(),0.001);
		}
	}

}
