package com.epam.lab.app.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.epam.lab.app.enums.BeerType;
import com.epam.lab.app.model.BeerSort;
import com.epam.lab.app.model.Chars;
import com.epam.lab.app.model.SpillMethod;

public class BeerStaxParser {
	private List<BeerSort> beerList;
	private BeerSort beerSort;
	private List<String> ingredientList;
	private Chars chars;
	private SpillMethod spillMethod;

	public List<BeerSort> parseXML(String fileName) {
		beerList = new ArrayList<>();
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();
				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();
					if (startElement.getName().getLocalPart().equals("beerSort")) {
						beerSort = new BeerSort();
						ingredientList = new ArrayList<>();
						chars = new Chars();
						spillMethod = new SpillMethod();
					} else if (startElement.getName().getLocalPart().equals("name")) {
						xmlEvent = xmlEventReader.nextEvent();
						beerSort.setName(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("type")) {
						xmlEvent = xmlEventReader.nextEvent();
						beerSort.setType(BeerType.valueOf(xmlEvent.asCharacters().getData().toUpperCase()));
					} else if (startElement.getName().getLocalPart().equals("al")) {
						xmlEvent = xmlEventReader.nextEvent();
						beerSort.setAlcoholic(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
					} else if (startElement.getName().getLocalPart().equals("manufacturer")) {
						xmlEvent = xmlEventReader.nextEvent();
						beerSort.setManufacturer(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("ingredient")) {
						xmlEvent = xmlEventReader.nextEvent();
						ingredientList.add(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("alcoholFraction")) {
						xmlEvent = xmlEventReader.nextEvent();
						chars.setAlcoholFraction(Float.parseFloat(xmlEvent.asCharacters().getData()));
					} else if (startElement.getName().getLocalPart().equals("filtered")) {
						xmlEvent = xmlEventReader.nextEvent();
						chars.setFiltered(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
					} else if (startElement.getName().getLocalPart().equals("nutritionalValue")) {
						xmlEvent = xmlEventReader.nextEvent();
						chars.setNutritionalValue(Integer.parseInt(xmlEvent.asCharacters().getData()));
					} else if (startElement.getName().getLocalPart().equals("transparency")) {
						xmlEvent = xmlEventReader.nextEvent();
						chars.setTransparency(Float.parseFloat(xmlEvent.asCharacters().getData()));
					} else if (startElement.getName().getLocalPart().equals("material")) {
						xmlEvent = xmlEventReader.nextEvent();
						spillMethod.setMaterial(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("volume")) {
						xmlEvent = xmlEventReader.nextEvent();
						spillMethod.setVolume(Float.parseFloat(xmlEvent.asCharacters().getData()));
					}
				}
				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();
					if (endElement.getName().getLocalPart().equals("beerSort")) {
						chars.setSpillMethod(spillMethod);
						beerSort.setChars(chars);
						beerSort.setIngredientList(ingredientList);
						beerList.add(beerSort);
					}
				}
			}

		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
		return beerList;
	}
}
