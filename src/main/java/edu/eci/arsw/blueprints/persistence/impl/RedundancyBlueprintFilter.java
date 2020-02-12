package edu.eci.arsw.blueprints.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintsFilter;

public class RedundancyBlueprintFilter implements BlueprintsFilter {
	
	/**
	 * Elimina los puntos que sean seguidos e iguales
	 */
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
				if(!(p.getX()==prev.getX() && p.getY()==prev.getY())) {
					prev=p;
					newṔoints.add(prev);
				}
			}
		}
		return newṔoints;
	}
}
