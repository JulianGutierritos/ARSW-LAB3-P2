/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }
    
    @SuppressWarnings("resource")
	@Test
    public void getBluePrintService (){
        Point[] pts3=new Point[]{new Point(100, 100),new Point(100, 100)};
        Blueprint bp3 =new Blueprint("pruebastian", "pintura",pts3);
        try {
            ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
            bps.addNewBlueprint(bp3);
            assertEquals(bp3, bps.getBlueprint("pruebastian", "pintura"));
        } catch (BlueprintPersistenceException | BlueprintNotFoundException ex) {
            Logger.getLogger(InMemoryPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @SuppressWarnings("resource")
	@Test
    public void getBluePrintByAuthorService () {
        Point[] pts4=new Point[]{new Point(200, 200),new Point(100, 100)};
        Point[] pts5=new Point[]{new Point(300, 300),new Point(200, 200)};
        Blueprint bp4 =new Blueprint("pruebastina", "pintura",pts4);
        Blueprint bp5 =new Blueprint("pruebastina", "pintura2",pts5);
        try {
            ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
            bps.addNewBlueprint(bp4);
            bps.addNewBlueprint(bp5);
            assertEquals(2, bps.getBlueprintsByAuthor("pruebastina").size());
        } catch (BlueprintPersistenceException | BlueprintNotFoundException ex) {
            Logger.getLogger(InMemoryPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    


    
}
