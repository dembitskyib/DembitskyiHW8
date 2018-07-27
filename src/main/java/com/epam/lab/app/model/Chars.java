package com.epam.lab.app.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "chars")
public class Chars {
	private float alcoholFraction;
	private float transparency;
	private boolean filtered;
	private int nutritionalValue;
	private SpillMethod spillMethod;

	public Chars() {

	}

	public Chars(float alcoholFraction, float transparency, boolean filtered, int nutritionalValue,
			SpillMethod spillMethod) {
		this.alcoholFraction = alcoholFraction;
		this.transparency = transparency;
		this.filtered = filtered;
		this.nutritionalValue = nutritionalValue;
		this.spillMethod = spillMethod;
	}

	public float getAlcoholFraction() {
		return alcoholFraction;
	}

	@XmlElement
	public void setAlcoholFraction(float alcoholFraction) {
		this.alcoholFraction = alcoholFraction;
	}

	public float getTransparency() {
		return transparency;
	}

	@XmlElement
	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}

	public boolean isFiltered() {
		return filtered;
	}

	@XmlElement
	public void setFiltered(boolean filtered) {
		this.filtered = filtered;
	}

	public int getNutritionalValue() {
		return nutritionalValue;
	}

	@XmlElement
	public void setNutritionalValue(int nutritionalValue) {
		this.nutritionalValue = nutritionalValue;
	}

	public SpillMethod getSpillMethod() {
		return spillMethod;
	}

	@XmlElement
	public void setSpillMethod(SpillMethod spillMethod) {
		this.spillMethod = spillMethod;
	}

}
