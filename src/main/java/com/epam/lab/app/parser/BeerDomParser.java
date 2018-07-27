package com.epam.lab.app.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epam.lab.app.enums.BeerType;
import com.epam.lab.app.model.BeerSort;
import com.epam.lab.app.model.Chars;
import com.epam.lab.app.model.SpillMethod;

public class BeerDomParser {
	private List<BeerSort> beerList;

	public BeerDomParser() {
		beerList = new ArrayList<>();
	}

	public List<BeerSort> runDOM(String xmlPath) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(xmlPath);
			NodeList beers = document.getElementsByTagName("beerSort");
			for (int temp = 0; temp < beers.getLength(); temp++) {
				Node beerProp = beers.item(temp);
				if (beerProp.getNodeType() == Node.ELEMENT_NODE) {
					BeerSort tempBeer = new BeerSort();
					Element tempElement = (Element) beerProp;
					tempBeer.setName(getTagValue(tempElement, "name"));
					tempBeer.setType(BeerType.valueOf(getTagValue(tempElement, "type").toUpperCase()));
					tempBeer.setAlcoholic(Boolean.getBoolean(getTagValue(tempElement, "al")));
					tempBeer.setManufacturer(getTagValue(tempElement, "manufacturer"));
					tempBeer.setIngredientList(getTagValues(tempElement, "ingredient"));
					Chars chars = new Chars();
					chars.setAlcoholFraction(Float.parseFloat(getTagValue(tempElement, "alcoholFraction")));
					chars.setFiltered(Boolean.parseBoolean(getTagValue(tempElement, "filtered")));
					chars.setNutritionalValue(Integer.parseInt(getTagValue(tempElement, "nutritionalValue")));
					chars.setTransparency(Float.parseFloat(getTagValue(tempElement, "transparency")));
					SpillMethod spillMethod = new SpillMethod();
					spillMethod.setMaterial(getTagValue(tempElement, "material"));
					spillMethod.setVolume(Float.parseFloat(getTagValue(tempElement, "volume")));
					chars.setSpillMethod(spillMethod);
					tempBeer.setChars(chars);
					beerList.add(tempBeer);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beerList;
	}

	public void getElements(Node node) {
		NodeList currentNodes = node.getChildNodes();
		for (int i = 0; i < currentNodes.getLength(); i++) {
			if (currentNodes.item(i).getNodeType() != Node.TEXT_NODE) {
				System.out.print(currentNodes.item(i).getNodeName());
			} else if (!currentNodes.item(i).getTextContent().trim().equals("")) {
				System.out.println(": " + currentNodes.item(i).getTextContent());
			}
			if (!Objects.isNull(currentNodes.item(i))) {
				getElements(currentNodes.item(i));
			}
		}
	}

	public String getTagValue(Element currentElement, String tagName) {
		return currentElement.getElementsByTagName(tagName).item(0).getTextContent();
	}

	public List<String> getTagValues(Element currentElement, String tagName) {
		List<String> itemsList = new ArrayList<>();
		int itemsLength = currentElement.getElementsByTagName(tagName).getLength();
		for (int i = 0; i < itemsLength; i++) {
			itemsList.add(currentElement.getElementsByTagName(tagName).item(i).getTextContent());
		}
		return itemsList;
	}
}
