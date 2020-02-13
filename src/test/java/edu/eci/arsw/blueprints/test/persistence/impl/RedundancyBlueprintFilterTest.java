package edu.eci.arsw.blueprints.test.persistence.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsFilter;
import edu.eci.arsw.blueprints.persistence.impl.RedundancyBlueprintFilter;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedundancyBlueprintFilterTest {

	@Test
    public void repeatedPointsTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
		Point a=new Point(3,2);
		Point b=new Point(3,2);
		Point[] pts1=new Point[]{a,b};
		Point c=new Point(3,2);
		Point d=new Point(1,0);
		Point[] pts2=new Point[]{c,d};
		Point e=new Point(2,1);
		Point f=new Point(-1,-2);
		Point[] pts3=new Point[]{e,f};
		Point g=new Point(-2,-3);
		Point h=new Point(-3,-4);
		Point[] pts4=new Point[]{g,h};
		Point i=new Point(3,2);
		Point j=new Point(-4,-5);
		Point[] pts5=new Point[]{i,j};
		Blueprint bp1 =new Blueprint("pruebastian", "pintura1",pts1);
		Blueprint bp2 =new Blueprint("pruebastian", "pintura2",pts2);
		Blueprint bp3 =new Blueprint("pruebastian", "pintura3",pts3);
		Blueprint bp4 =new Blueprint("pruebastian", "pintura4",pts4);
		Blueprint bp5 =new Blueprint("pruebastian", "pintura5",pts5);
		try{
			bps.addNewBlueprint(bp1);
			bps.addNewBlueprint(bp2);
			bps.addNewBlueprint(bp3);
			bps.addNewBlueprint(bp4);
			bps.addNewBlueprint(bp5);
		} catch (Exception z){}
		
		assertEquals(bps.getPoints().size(),11);
    }
	
	@Test
    public void noEliminaPuntosEnLaMismaRecta() throws BlueprintPersistenceException, BlueprintNotFoundException{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
		Point a=new Point(3,2);
		Point b=new Point(-1,-2);
		Point[] pts1=new Point[]{a,b};
		Point c=new Point(0,-1);
		Point d=new Point(1,0);
		Point[] pts2=new Point[]{c,d};
		Point e=new Point(2,1);
		Point f=new Point(-1,-2);
		Point[] pts3=new Point[]{e,f};
		Point g=new Point(-2,-3);
		Point h=new Point(-3,-4);
		Point[] pts4=new Point[]{g,h};
		Point i=new Point(3,2);
		Point j=new Point(-4,-5);
		Point[] pts5=new Point[]{i,j};
		Blueprint bp1 =new Blueprint("pruebastina", "pintura1",pts1);
		Blueprint bp2 =new Blueprint("pruebastina", "pintura2",pts2);
		Blueprint bp3 =new Blueprint("pruebastina", "pintura3",pts3);
		Blueprint bp4 =new Blueprint("pruebastina", "pintura4",pts4);
		Blueprint bp5 =new Blueprint("pruebastina", "pintura5",pts5);
		try{
			bps.addNewBlueprint(bp1);
			bps.addNewBlueprint(bp2);
			bps.addNewBlueprint(bp3);
			bps.addNewBlueprint(bp4);
			bps.addNewBlueprint(bp5);
		} catch (Exception z){}
		assertEquals(bps.getPoints().size(),12);
    }


}
