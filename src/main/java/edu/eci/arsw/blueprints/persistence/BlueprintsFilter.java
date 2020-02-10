package edu.eci.arsw.blueprints.persistence;

import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public interface BlueprintsFilter {

	public List<Point> filter(List<Point> points);

}
