package edu.eci.arsw.blueprints.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintsFilter;

public class RedundancyBlueprintFilter implements BlueprintsFilter {

	@Override
	public List<Point> filter(List<Point> points) {
		List<Point>newṔoints=new ArrayList<Point>();
		Point prev=null;
		for(Point p:points) {
			if(prev==null) {
				prev=p;
				newṔoints.add(prev);
			}
			else {
				if(p.getX()==prev.getX() && p.getY()==prev.getY()) {
					System.out.println(true);
				}
				else {
					prev=p;
					newṔoints.add(prev);
				}
			}
		}
		return newṔoints;
	}
}
