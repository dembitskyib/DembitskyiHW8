package com.epam.lab.app.converter;

import java.util.Comparator;

public class BeerNameComparator implements Comparator<String> {
	private final int TAG_LENGTH = 4;
	private final String OPEN_TAG = "<td>";
	private final String CLOSE_TAG = "</td>";

	@Override
	public int compare(String firstBeerSort, String secondBeerSort) {
		int firstNameStart = firstBeerSort.indexOf(OPEN_TAG) + TAG_LENGTH;
		int firstNameEnd = firstBeerSort.indexOf(CLOSE_TAG);
		int secondNameStart = secondBeerSort.indexOf(OPEN_TAG) + TAG_LENGTH;
		int secondNameEnd = secondBeerSort.indexOf(CLOSE_TAG);
		String firstName = firstBeerSort.substring(firstNameStart, firstNameEnd);
		String secondName = secondBeerSort.substring(secondNameStart, secondNameEnd);
		return secondName.compareTo(firstName);
	}

}
