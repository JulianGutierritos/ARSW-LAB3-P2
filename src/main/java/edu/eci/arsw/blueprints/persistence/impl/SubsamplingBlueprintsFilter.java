package edu.eci.arsw.blueprints.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintsFilter;

public class SubsamplingBlueprintsFilter implements BlueprintsFilter{
	
	@Override
	public List<Point> filter(List<Point> points) {
		Point a=null;
		Point b=null;
		Point c=null;
		int n=points.size();
		List<Point>pointsClone=new ArrayList<Point>(points);
		
		int auxA=-1;
		int auxB=-1;
		for(int i=0;i<n;i++) {
			//System.out.println(i);

			if(a==null) {
				a=points.get(i);
				auxA=i;
			}
			else if(b==null && i==auxA+2) {
				b=points.get(i);
				auxB=i;
			
				//System.out.println(auxA+" , "+auxB);
				int reviewPoint=auxB-1;
				c=points.get(reviewPoint);
				if(lineEcuation(a,b,c)) {
					pointsClone.remove(c);
					//System.out.println(true);
					a=b;
					b=null;
					auxA=auxB;
					auxB=-1;
				}
				
			}
		}
		return pointsClone;
	}
	
	/**
	 * Retorna si un punto pertenece o no a una recta formada con otros dos puntos
	 * @param a Primer punto que pertenece a la recta
	 * @param b Segundo punto que pertenece a la recta
	 * @param c Punto a verificar si pertence o no a la recta
	 * @return true o false dependiendo si el punto c pertenece a la recta formada por los punto a y b
	 */
	public boolean lineEcuation(Point a, Point b, Point c) {
		int x1=a.getX();
		int y1=a.getY();
		
		int x2=b.getX();
		int y2=b.getY();
		
		int x3=c.getX();
		int y3=c.getY();
	
		double m=(double)(y2-y1)/(x2-x1);
		//System.out.println(m);
		double mPorX=m*x3;
		double mPorX1=m*x1;
		double menosMPorX1MasY1=-mPorX1+y1;
		
		String xValue=menosMPorX1MasY1>=0?"x+":"x";
		String equation="y="+m+xValue+menosMPorX1MasY1;
		
		double yValue=mPorX-mPorX1+y1;
	//	System.out.println(equation);
		
		return (double)y3==yValue;
	}
	
	/*
	public static void main(String[]args) {
		BlueprintsFilter ff=new SubsamplingBlueprintsFilter();
		Point a=new Point(3,2);
		Point b=new Point(-1,-2);
		Point c=new Point(0,-1);
		Point d=new Point(1,0);
		Point e=new Point(2,1);
		Point f=new Point(-1,-2);
		Point g=new Point(-2,-3);
		Point h=new Point(-3,-4);
		Point i=new Point(3,2);
		Point j=new Point(-4,-5);
		List<Point> points=new ArrayList<Point>();
		points.add(a);points.add(b);points.add(c);points.add(d);points.add(e);points.add(f);points.add(g);
		points.add(h);points.add(i);points.add(j);
		points=ff.filter(points);
		System.out.println(points.size());
		
	}*/
}
