package es.us.isa.icomot.deployer.domain;

import java.util.ArrayList;
import java.util.List;

public class Component {
	
	private String id;
	private List<String> packages;
	private String script;
	private String artifact;
	private Boolean elasticity;
	private Boolean exposes;
	private List<String> requires;
	private Boolean undeployScript;
	private Integer instances;
	
	public Component() {
	}

	public Component(String id, String script, String artifact,
			Boolean elasticity, Boolean exposes) {
		this.id = id;
		this.script = script;
		this.artifact = artifact;
		this.elasticity = elasticity;
		this.exposes = exposes;
		this.packages = new ArrayList<String>();
		this.requires = new ArrayList<String>();
	}


	public Integer getInstances() {
		return instances;
	}

	public void setInstances(Integer instances) {
		this.instances = instances;
	}

	public Boolean getUndeployScript() {
		return undeployScript;
	}

	public void setUndeployScript(Boolean undeployScript) {
		this.undeployScript = undeployScript;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<String> getPackages() {
		return packages;
	}
	
	public void setPackages(List<String> packages) {
		this.packages = packages;
	}
	
	public void addPackage(String s){
		this.packages.add(s);
	}
	
	public String getScript() {
		return script;
	}
	
	public void setScript(String script) {
		this.script = script;
	}
	
	public String getArtifact() {
		return artifact;
	}
	
	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}
	
	public Boolean getElasticity() {
		return elasticity;
	}
	
	public void setElasticity(Boolean elasticity) {
		this.elasticity = elasticity;
	}
	
	public Boolean getExposes() {
		return exposes;
	}
	
	public void setExposes(Boolean exposes) {
		this.exposes = exposes;
	}
	
	public List<String> getRequires() {
		return requires;
	}
	
	public void setRequires(List<String> requires) {
		this.requires = requires;
	}
	
	public void addRequire(String s){
		this.requires.add(s);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artifact == null) ? 0 : artifact.hashCode());
		result = prime * result
				+ ((elasticity == null) ? 0 : elasticity.hashCode());
		result = prime * result + ((exposes == null) ? 0 : exposes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((packages == null) ? 0 : packages.hashCode());
		result = prime * result
				+ ((requires == null) ? 0 : requires.hashCode());
		result = prime * result + ((script == null) ? 0 : script.hashCode());
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
		Component other = (Component) obj;
		if (artifact == null) {
			if (other.artifact != null)
				return false;
		} else if (!artifact.equals(other.artifact))
			return false;
		if (elasticity == null) {
			if (other.elasticity != null)
				return false;
		} else if (!elasticity.equals(other.elasticity))
			return false;
		if (exposes == null) {
			if (other.exposes != null)
				return false;
		} else if (!exposes.equals(other.exposes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (packages == null) {
			if (other.packages != null)
				return false;
		} else if (!packages.equals(other.packages))
			return false;
		if (requires == null) {
			if (other.requires != null)
				return false;
		} else if (!requires.equals(other.requires))
			return false;
		if (script == null) {
			if (other.script != null)
				return false;
		} else if (!script.equals(other.script))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Component [id=" + id + ", packages=" + packages.toString() + ", script="
				+ script + ", artifact=" + artifact + ", elasticity="
				+ elasticity + ", exposes=" + exposes + ", requires="
				+ requires.toString() + "]";
	}
	
	
}
