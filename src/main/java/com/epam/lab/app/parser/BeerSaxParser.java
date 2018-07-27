package com.epam.lab.app.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.lab.app.enums.BeerType;
import com.epam.lab.app.model.BeerSort;
import com.epam.lab.app.model.Chars;
import com.epam.lab.app.model.SpillMethod;

public class BeerSaxParser extends DefaultHandler {
	private List<BeerSort> beerList;
	private BeerSort beerSort;
	private Chars chars;
	private SpillMethod spillMethod;
	private List<String> ingredientList;
	private boolean name;
	private boolean type;
	private boolean alcoholic;
	private boolean manufacturer;
	private boolean ingredient;
	private boolean alcoholFraction;
	private boolean transparency;
	private boolean filtered;
	private boolean nutritionalValue;
	private boolean volume;
	private boolean material;

	public List<BeerSort> getResult() {
		return beerList;
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
			throws SAXException {
		if (qName.equalsIgnoreCase("beersort")) {
			beerSort = new BeerSort();
			if (beerList == null)
				beerList = new ArrayList<>();
		} else if (qName.equalsIgnoreCase("chars")) {
			chars = new Chars();
		} else if (qName.equalsIgnoreCase("spillMethod")) {
			spillMethod = new SpillMethod();
		} else if (qName.equalsIgnoreCase("ingredients")) {
			ingredientList = new ArrayList<>();
		} else if (qName.equalsIgnoreCase("name")) {
			name = true;
		} else if (qName.equalsIgnoreCase("type")) {
			type = true;
		} else if (qName.equalsIgnoreCase("alcoholic")) {
			alcoholic = true;
		} else if (qName.equalsIgnoreCase("manufacturer")) {
			manufacturer = true;
		} else if (qName.equalsIgnoreCase("ingredient")) {
			ingredient = true;
		} else if (qName.equalsIgnoreCase("alcoholFraction")) {
			alcoholFraction = true;
		} else if (qName.equalsIgnoreCase("transparency")) {
			transparency = true;
		} else if (qName.equalsIgnoreCase("filtered")) {
			filtered = true;
		} else if (qName.equalsIgnoreCase("nutritionalValue")) {
			nutritionalValue = true;
		} else if (qName.equalsIgnoreCase("volume")) {
			volume = true;
		} else if (qName.equalsIgnoreCase("material")) {
			material = true;
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("spillMethod")) {
			chars.setSpillMethod(spillMethod);
		} else if (qName.equalsIgnoreCase("chars")) {
			beerSort.setChars(chars);
		} else if (qName.equalsIgnoreCase("beerSort")) {
			beerList.add(beerSort);
		} else if (qName.equalsIgnoreCase("ingredients")) {
			beerSort.setIngredientList(ingredientList);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String currentString = new String(ch, start, length);
		if (name) {
			beerSort.setName(currentString);
			name = false;
		} else if (type) {
			beerSort.setType(BeerType.valueOf(currentString.trim().toUpperCase()));
			type = false;
		} else if (alcoholic) {
			beerSort.setAlcoholic(new Boolean(currentString));
			alcoholic = false;
		} else if (manufacturer) {
			beerSort.setManufacturer(currentString);
			manufacturer = false;
		} else if (ingredient) {
			ingredientList.add(currentString);
			ingredient = false;
		} else if (alcoholFraction) {
			chars.setAlcoholFraction(new Float(currentString));
			alcoholFraction = false;
		} else if (transparency) {
			chars.setTransparency(new Float(currentString));
			transparency = false;
		} else if (filtered) {
			chars.setFiltered(new Boolean(currentString));
			filtered = false;
		} else if (nutritionalValue) {
			chars.setNutritionalValue(new Integer(currentString));
			nutritionalValue = false;
		} else if (volume) {
			spillMethod.setVolume(new Float(currentString));
			volume = false;
		} else if (material) {
			spillMethod.setMaterial(currentString);
			material = false;
		}

	}

}