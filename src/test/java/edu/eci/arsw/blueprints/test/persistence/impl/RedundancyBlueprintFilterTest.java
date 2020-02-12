package edu.eci.arsw.blueprints.test.persistence.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsFilter;
import edu.eci.arsw.blueprints.persistence.impl.RedundancyBlueprintFilter;

public class RedundancyBlueprintFilterTest {

	@Test
    public void repeatedPointsTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
		BlueprintsFilter ff=new RedundancyBlueprintFilter();
		Point a=new Point(3,2);
		Point b=new Point(3,2);
		Point c=new Point(3,2);
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
		assertEquals(points.size(),8);
    }


}
