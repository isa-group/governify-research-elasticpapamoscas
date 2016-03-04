package es.us.isa.papamoscas.proxy.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Bird implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7200919598315122975L;
	
	@Id
	private UUID id;
    private String specie;
    private String place;
    private float legDiameter;
    private float wingSize;
    private int eggs;
    private int hatches;
	
	public Bird(){
		
	}
	
	public Bird(UUID id, String specie, String place, float legDiameter,
			float wingSize, int eggs, int hatches) {
		super();
		this.id = id;
		this.specie = specie;
		this.place = place;
		this.legDiameter = legDiameter;
		this.wingSize = wingSize;
		this.eggs = eggs;
		this.hatches = hatches;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public float getLegDiameter() {
		return legDiameter;
	}

	public void setLegDiameter(float legDiameter) {
		this.legDiameter = legDiameter;
	}

	public float getWingSize() {
		return wingSize;
	}

	public void setWingSize(float wingSize) {
		this.wingSize = wingSize;
	}

	public int getEggs() {
		return eggs;
	}

	public void setEggs(int eggs) {
		this.eggs = eggs;
	}

	public int getHatches() {
		return hatches;
	}

	public void setHatches(int hatches) {
		this.hatches = hatches;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bird other = (Bird) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BirdDTO [id=" + id + ", specie=" + specie + "]";
	}
	
	

	
}
