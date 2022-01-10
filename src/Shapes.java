package TerraBlocks;

import java.awt.Point;

////////////////////////////////////////////////////////////////////////////
///        @author Gustaf Franzén :: https://github.com/BjorneEk;        ///
////////////////////////////////////////////////////////////////////////////

public class Shapes {

	private static Point[] rotateShape(Point[] pts, double v){
		Point[] res = new Point[pts.length];
		int i = 0;
		for(Point p : pts){
			res[i] = rotatePoint(p,v);
			i++;
		}
		return res;
	}
	static private Point[] translateShape(Point[] pts, Point org){
		Point[] res = new Point[pts.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = new Point((pts[i].x + org.x), (pts[i].y + org.y));
		}
		return res;
	}
	static private Point rotatePoint(Point p, double v){
		double x;
		double y;

		x = (p.x*Math.cos(Math.toRadians(v)))-p.y*Math.sin(Math.toRadians(v));
		y = p.x*Math.sin(Math.toRadians(v))+p.y*Math.cos(Math.toRadians(v));
		return new Point((int)x,(int)y);
	}
	static private Point[] arc(double r, int type, int res){
		double length = (((r*2)*Math.PI))/4;
		Point[] arc = new Point[(int)(length/res)];

		double lb = length/arc.length;    // length of one sub arc
		double v1 = lb/((Math.PI/180)*r); // angle of subarc
		double v2 = (180-v1)/2; //angle betwen x axis and point
		double d = (r*Math.sin(Math.toRadians(v1)))/Math.sin(Math.toRadians(v2)); //distance to point

		arc[0] = new Point((int)(Math.cos(Math.toRadians(v2))*d),-(int)(Math.sin(Math.toRadians(v2))*d));
		for(int i = 1; i < arc.length; i++){
			Point temp = rotatePoint(new Point((int)(arc[0].x-r),(int)arc[0].y),i*-v1);
			arc[i] = new Point((int)(temp.x+r),-temp.y);
		}

		if(type == 0)return arc;
		for(int i = 0; i < arc.length; i++){
			arc[i].y = -arc[i].y;
		}
		return arc;
	}
}
