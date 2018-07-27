package com.epam.lab.app.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import com.epam.lab.app.converter.BeerHtmlConverter;
import com.epam.lab.app.creator.BeerCreator;
import com.epam.lab.app.model.Beer;
import com.epam.lab.app.model.BeerSort;
import com.epam.lab.app.parser.BeerDomParser;
import com.epam.lab.app.parser.BeerSaxParser;
import com.epam.lab.app.parser.BeerStaxParser;
import com.epam.lab.app.parser.JaxbParser;
import com.epam.lab.app.validator.SchemaValidator;

public class MainController {
	private static final Logger logger = LogManager.getLogger(MainController.class);
	private final String RESOURCES_PATH = "src/main/resources/";
	private final String XML_PATH = RESOURCES_PATH + "beer.xml";
	private final String XSD_PATH = RESOURCES_PATH + "beerSchema.xsd";
	private final String XSL_PATH = RESOURCES_PATH + "beerTransform.xsl";
	private final String HTML_PATH = RESOURCES_PATH + "beer.html";
	private final String NEW_XML_PATH = RESOURCES_PATH + "not_beer.xml";
	private final String JAXB_XML_PATH = RESOURCES_PATH + "jaxbBeer.xml";
	private BeerHtmlConverter beerHtmlConverter;
	private JaxbParser jaxbParser;

	public MainController() {
		beerHtmlConverter = new BeerHtmlConverter();
		jaxbParser = new JaxbParser();
	}

	public void parseSax() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		BeerSaxParser beerSaxParser = new BeerSaxParser();
		try {
			parser = factory.newSAXParser();
			parser.parse(new File(XML_PATH), beerSaxParser);
		} catch (ParserConfigurationException | SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<BeerSort> beers = beerSaxParser.getResult();
		printBeerList(beers);
	}

	public void parseDom() {
		BeerDomParser beerDomParser = new BeerDomParser();
		printBeerList(beerDomParser.runDOM(XML_PATH));
	}

	public void parseStax() {
		BeerStaxParser beerStaxParser = new BeerStaxParser();
		printBeerList(beerStaxParser.parseXML(XML_PATH));
	}

	public void validate() {
		SchemaValidator schemaValidator = new SchemaValidator();
		logger.info("Validation: " + schemaValidator.validate(XSD_PATH, XML_PATH));
	}

	public void convertToHtml() {
		logger.info("Creating HTML, path: " + HTML_PATH);
		logger.info("Created: " + beerHtmlConverter.convert(XML_PATH, XSL_PATH));
	}

	public void changeRoot() {
		logger.info("Creating new XML, path: " + NEW_XML_PATH);
		logger.info("Created: " + beerHtmlConverter.changeRoot(XML_PATH, NEW_XML_PATH));
	}

	public void marshall() {
		BeerCreator beerCreator = new BeerCreator();
		boolean created = false;
		logger.info("Creating new XML, path: " + JAXB_XML_PATH);
		try {
			jaxbParser.saveObject(new File(JAXB_XML_PATH), beerCreator.create());
			created = true;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		logger.info("Created: " + created);
	}

	public void unMarshall() {
		try {
			Beer beer = (Beer) jaxbParser.getObject(new File(XML_PATH), Beer.class);
			printBeerList(beer.getBeerTypes());
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private void printBeerList(List<BeerSort> beerList) {
		for (BeerSort beer : beerList) {
			logger.info("Name: " + beer.getName());
			logger.info("Type: " + beer.getType());
			logger.info("Alcoholic: " + beer.isAlcoholic());
			logger.info("Manufacturer: " + beer.getManufacturer());
			logger.info("Ingredients:");
			beer.getIngredientList().forEach(ingredient -> logger.info(ingredient));
			logger.info("AlcoholFraction: " + beer.getChars().getAlcoholFraction());
			logger.info("Transparency: " + beer.getChars().getTransparency());
			logger.info("Filtered: " + beer.getChars().isFiltered());
			logger.info("NutritionalValue: " + beer.getChars().getNutritionalValue());
			logger.info("Volume: " + beer.getChars().getSpillMethod().getVolume());
			logger.info("Material: " + beer.getChars().getSpillMethod().getMaterial() + "\n");

		}
	}
}
