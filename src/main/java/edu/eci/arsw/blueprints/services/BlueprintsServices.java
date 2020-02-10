/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsFilter;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp;
    
    @Autowired
    BlueprintsFilter filter;
    
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        bpp.saveBlueprint(bp);
    }
    
    @Autowired 
    public void setBlueprintsPersistence (BlueprintsPersistence bpp){
        this.bpp = bpp;
    }
    
    public Set<Blueprint> getAllBlueprints(){
        return null;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
    	Blueprint bp=bpp.getBlueprint(author, name);
    	filter.filter(bp.getPoints());
        return bp;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
    	Set<Blueprint>bps=bpp.getBlueprintsByAuthor(author);
    	for(Blueprint bp:bps) {
    		bp.setPoints(filter.filter(bp.getPoints()));
    	}
    	
        return bps;
    }
    
    
    /*public static void main(String[]args) {
    	Point[] pts4=new Point[]{new Point(200, 200),new Point(200, 200), new Point(200, 200), new Point(400, 200)};
    	
        Blueprint bp4 =new Blueprint("pruebastina", "pintura",pts4);
    	try {
            ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
            bps.addNewBlueprint(bp4);
            int e=0;
            for(Blueprint b:bps.getBlueprintsByAuthor("pruebastina")) {
            	List<Point>ps=b.getPoints();
            	for(int i=0; i<ps.size();i++) {
            		if(ps.get(i)!=null) {
            			e++;
            		}
            	}
            }
            System.out.println(e);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }*/
    
}
