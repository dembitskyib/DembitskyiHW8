package com.epam.lab.app.converter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class BeerHtmlConverter {

	private final String OPEN_TAG = "<tr>\r";
	private final String NEW_OPEN_ROOT_TAG = "<not_beer>";
	private final String NEW_CLOSE_ROOT_TAG = "</not_beer>";
	private final String HTML_PATH = "src/main/resources/beer.html";

	@SuppressWarnings("finally")
	public boolean convert(String xmlFile, String xslFile) {
		InputStream xml;
		boolean created = false;
		try {
			xml = new FileInputStream(xmlFile);
			InputStream xsl = new FileInputStream(xslFile);
			StreamSource xmlSource = new StreamSource(xml);
			StreamSource styleSource = new StreamSource(xsl);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			StreamResult xmlOutput = new StreamResult(byteStream);
			Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
			transformer.transform(xmlSource, xmlOutput);
			created = writeFile(sort(byteStream.toString()), HTML_PATH);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} finally {
			return created;
		}
	}

	public boolean changeRoot(String xmlFile, String newXmlFile) {
		StringBuilder xmlString = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(xmlFile))) {
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				xmlString.append(currentLine + "\n");
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String[] stringArray = xmlString.toString().split("\n");
		String lastRow = stringArray[stringArray.length - 1];
		int startLastTag = lastRow.indexOf("</") + 2;
		int endLastTag = lastRow.indexOf(">");
		String rootName = lastRow.substring(startLastTag, endLastTag);
		String result = xmlString.toString().replaceAll("<" + rootName + ">", NEW_OPEN_ROOT_TAG);
		result = result.replaceAll("</" + rootName + ">", NEW_CLOSE_ROOT_TAG);
		return writeFile(result, newXmlFile);
	}

	private String sort(String unsortedString) {
		String[] stringArray = unsortedString.split("\n");
		List<String> rowList = new ArrayList<>();
		for (int i = 0; i < stringArray.length; i++) {
			if (stringArray[i].equals(OPEN_TAG)) {
				rowList.add(stringArray[i + 1]);
			}
		}
		Collections.sort(rowList.subList(1, rowList.size()), new BeerNameComparator());
		for (int i = 0, rowIndex = 0; i < stringArray.length; i++) {
			if (stringArray[i].equals(OPEN_TAG)) {
				stringArray[i + 1] = rowList.get(rowIndex);
				rowIndex++;
			}
		}
		return String.join("\n", stringArray);
	}

	private boolean writeFile(String dataString, String path) {
		try (PrintWriter writeFile = new PrintWriter(path)) {
			writeFile.println(dataString);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

	}

}
