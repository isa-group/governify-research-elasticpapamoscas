/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.papamoscas.repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import es.us.isa.papamoscas.dto.Bird;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 *
 * @author isa-tecnico
 */
@Component
public class CassandraDriverBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8253783955107552125L;
	
	private final Cluster cluster = Cluster.builder().addContactPoint(getIP()).build();
//    private final Cluster cluster = Cluster.builder().addContactPoint("150.214.188.130").build();
    
	public String getIP(){
		File f = new File("/etc/environment");
		Path path = Paths.get(f.getAbsolutePath());
		String ret = "";
		try{
			List<String> files = Files.readAllLines(path, StandardCharsets.UTF_8);
			for (String file : files){
				if(file.contains("cassandra")){
					String[] aux = file.split(";");
					for (String s : aux){
						if(s.contains("database"))
							ret = s.split("=")[1];
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}		
		return ret;
	}
   
    public void insert(Bird b) {
        try (Session session = cluster.connect("papamoscaskeyspace")) {
            session.execute("INSERT INTO Bird (id, specie, place, legDiameter, wingSize, eggs, hatches) VALUES (" + UUID.randomUUID() + ",'" + b.getSpecie() + "','" + b.getPlace() + "'," + b.getLegDiameter() + "," + b.getWingSize() + "," + b.getEggs() + "," + b.getHatches() + ");");
            session.closeAsync();
        }
    }

    public Bird select(UUID id) {
        try (Session session = cluster.connect("papamoscaskeyspace")) {
            ResultSet results = session.execute("SELECT * FROM Bird WHERE id=" + id);
            if (results != null) {
                Row row = results.all().get(0);
                Bird b = new Bird(row.getUUID("id"), row.getString("specie"), row.getString("place"), row.getFloat("legDiameter"), row.getFloat("wingSize"), row.getInt("eggs"), row.getInt("hatches"));
                return b;
            }
        }
        return null;
    }

    public void update(UUID id, Bird b) {
        try (Session session = cluster.connect("papamoscaskeyspace")) {
            session.execute("UPDATE Bird SET specie='" + b.getSpecie() + "', place='" + b.getPlace() + "', legDiameter=" + b.getLegDiameter() + ", wingSize=" + b.getWingSize() + ", eggs=" + b.getEggs() + ", hatches=" + b.getHatches() + " WHERE id=" + id);
            session.closeAsync();
        }
    }

    public void delete(UUID id) {
        try (Session session = cluster.connect("papamoscaskeyspace")) {
            session.execute("DELETE from Bird WHERE id = " + id);
            session.closeAsync();
        }
    }

    public List<Bird> selectAll() {
        Session session = cluster.connect("papamoscaskeyspace");
        ResultSet results = session.execute("SELECT * FROM Bird");
        List<Bird> BirdList = new ArrayList<>();
        for (Row row : results.all()) {
            Bird b = new Bird(row.getUUID("id"), row.getString("specie"), row.getString("place"), row.getFloat("legDiameter"), row.getFloat("wingSize"), row.getInt("eggs"), row.getInt("hatches"));
            BirdList.add(b);
        }
        session.closeAsync();
        return BirdList;
    }

    public String count() {
        Session session = cluster.connect("papamoscaskeyspace");
        ResultSet results = session.execute("SELECT * FROM Bird");
        session.closeAsync();
        return Integer.toString(results.all().size());
    }

}
