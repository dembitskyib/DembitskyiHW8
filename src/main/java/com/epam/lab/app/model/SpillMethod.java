package com.epam.lab.app.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "spillMethod")
public class SpillMethod {
	private float volume;
	private String material;

	public SpillMethod(){
		
	}
	public SpillMethod(float volume, String material) {
		this.volume = volume;
		this.material = material;
	}

	public float getVolume() {
		return volume;
	}

	@XmlElement
	public void setVolume(float f) {
		this.volume = f;
	}

	public String getMaterial() {
		return material;
	}

	@XmlElement
	public void setMaterial(String material) {
		this.material = material;
	}

}
