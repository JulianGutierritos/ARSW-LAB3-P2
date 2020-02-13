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
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import edu.eci.arsw.blueprints.model.Point;

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
    
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException{
        return this.bpp.getAllBlueprints();
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
    	//filter.filter(bp.getPoints());
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
    	//for(Blueprint bp:bps) {
    		//bp.setPoints(filter.filter(bp.getPoints()));
    	//}
        return bps;
    }
	
	public List<Point> getPoints () throws BlueprintNotFoundException{
		Set<Blueprint> bps = getAllBlueprints();
		List<Point> res = new ArrayList<Point>();
		List<Point> p = new ArrayList<Point>();
		int i;
		for (Blueprint bp : bps){
			p = bp.getPoints();
			for (i = 0 ; i < p.size() ; i++){
				res.add(p.get(i));
			}
		}
		return filter.filter(res);
	}
	
}
