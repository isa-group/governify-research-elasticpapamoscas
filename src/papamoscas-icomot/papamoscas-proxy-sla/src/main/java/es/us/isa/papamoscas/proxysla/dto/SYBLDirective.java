package es.us.isa.papamoscas.proxysla.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class SYBLDirective {
	
	@XmlAttribute(name="Constraints")
	private String constraints;
	
	@XmlAttribute(name="Priorities")
	private String priorities;
	
	@XmlAttribute(name="Strategies")
	private String strategies;
	
	@XmlAttribute(name="Notifications")
	private String notifications;
	
	public SYBLDirective(){
		
	}

	public SYBLDirective(String constraints, String priorities,
			String strategies, String notifications) {
		super();
		this.constraints = constraints;
		this.priorities = priorities;
		this.strategies = strategies;
		this.notifications = notifications;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	public String getPriorities() {
		return priorities;
	}

	public void setPriorities(String priorities) {
		this.priorities = priorities;
	}

	public String getStrategies() {
		return strategies;
	}

	public void setStrategies(String strategies) {
		this.strategies = strategies;
	}

	public String getNotifications() {
		return notifications;
	}

	public void setNotifications(String notifications) {
		this.notifications = notifications;
	}

	@Override
	public String toString() {
		return "SYBLDirective [constraints=" + constraints + ", priorities="
				+ priorities + ", strategies=" + strategies
				+ ", notifications=" + notifications + "]";
	}
	
	
	
}
