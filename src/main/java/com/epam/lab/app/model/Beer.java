package com.epam.lab.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "beer")
public class Beer {
	private List<BeerSort> beerTypes = new ArrayList<BeerSort>();

	public List<BeerSort> getBeerTypes() {
		return beerTypes;
	}

	@XmlElement(name = "beerSort")
	public void setBeerTypes(List<BeerSort> beerTypes) {
		this.beerTypes = beerTypes;
	}

}
