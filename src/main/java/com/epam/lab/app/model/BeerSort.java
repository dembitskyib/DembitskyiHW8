package com.epam.lab.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.epam.lab.app.enums.BeerType;

@XmlType(name = "beerSort")
public class BeerSort {
	private String name;
	private BeerType type;
	private boolean alcoholic;
	private String manufacturer;
	private List<String> ingredientList;
	private Chars chars;

	public BeerSort() {
		ingredientList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public BeerType getType() {
		return type;
	}

	@XmlElement
	public void setType(BeerType type) {
		this.type = type;
	}

	public boolean isAlcoholic() {
		return alcoholic;
	}

	@XmlElement(name = "al")
	public void setAlcoholic(boolean alcoholic) {
		this.alcoholic = alcoholic;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	@XmlElement
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<String> getIngredientList() {
		return ingredientList;
	}

	@XmlElementWrapper(name = "ingredients")
	@XmlElement(name = "ingredient")
	public void setIngredientList(List<String> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public Chars getChars() {
		return chars;
	}

	public void setChars(Chars chars) {
		this.chars = chars;
	}
}
