package edu.eci.arsw.blueprints.test.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlueprintServicesTest {

    

    public BlueprintsServices getBlueprintsServices() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
        return bps;
    }

    @Test
    public void addNewBlueprintAndConsultTest() {
        BlueprintsServices bps = getBlueprintsServices();
        Point[] pt = new Point[] { new Point(20, 20), new Point(30, 30) };
        Blueprint bp = new Blueprint("Matt", "Design", pt);
        Blueprint bp2 = null;
        try {
            bps.addNewBlueprint(bp);
            bp2 = bps.getBlueprint("Matt", "Design");
        } catch (BlueprintPersistenceException e) {
            e.printStackTrace();
        } catch (BlueprintNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(bp, bp2);

        
    }

    @Test
    public void getBlueprintsByAuthorTest() {
        BlueprintsServices bps = getBlueprintsServices();
        Point[] pt = new Point[] { new Point(20, 20), new Point(30, 30), new Point(40, 10) };

        Blueprint bp = new Blueprint("Matt", "Design", pt);
        Blueprint bp2 = new Blueprint("Matt", "Walls", pt);
        Blueprint bp3 = new Blueprint("Sam", "Floors", pt);
        try {
            bps.addNewBlueprint(bp);
            bps.addNewBlueprint(bp2);
            bps.addNewBlueprint(bp3);
        } catch (BlueprintPersistenceException e) {
            e.printStackTrace();
        }

        Set<Blueprint> MattPlans = null; 
        Set<Blueprint> SamPlans = null;
        try {
            MattPlans = bps.getBlueprintsByAuthor("Matt");
            SamPlans = bps.getBlueprintsByAuthor("Sam");
        } catch (BlueprintNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals( 2 , MattPlans.size());
        assertEquals( 1 , SamPlans.size());
    }
    
}
