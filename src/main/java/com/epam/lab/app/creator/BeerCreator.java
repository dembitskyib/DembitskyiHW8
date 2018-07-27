package com.epam.lab.app.creator;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.app.enums.BeerType;
import com.epam.lab.app.model.Beer;
import com.epam.lab.app.model.BeerSort;
import com.epam.lab.app.model.Chars;
import com.epam.lab.app.model.SpillMethod;

public class BeerCreator {
	public Beer create(){
		Beer beer = new Beer();
		List<BeerSort> beerSortList = new ArrayList<>();
		List<String> ingredients = new ArrayList<>();
		BeerSort beerSort = new BeerSort();
		beerSort.setName("Toppling Goliath Kentucky Brunch");
		beerSort.setType(BeerType.ALE);
		beerSort.setAlcoholic(true);
		beerSort.setManufacturer("Toppling Goliath Brewing Company");
		ingredients.add("water");
		ingredients.add("grain");
		ingredients.add("hop");
		ingredients.add("sugar");
		beerSort.setIngredientList(ingredients);
		beerSort.setChars(new Chars(12, 73, true, 500, new SpillMethod(0.7f,"glass")));
		beerSortList.add(beerSort);
		beerSort = new BeerSort();
		ingredients = new ArrayList<>();
		beerSort.setName("Westvleteren 12 (XII)");
		beerSort.setType(BeerType.DARK);
		beerSort.setAlcoholic(true);
		beerSort.setManufacturer("Westvleteren Abdij St. Sixtus");
		ingredients.add("water");
		ingredients.add("westmalle yeast");
		ingredients.add("malt");
		ingredients.add("local hops");
		ingredients.add("sugar");
		beerSort.setIngredientList(ingredients);
		beerSort.setChars(new Chars(10.2f, 30, false, 600, new SpillMethod(0.33f,"glass")));
		beerSortList.add(beerSort);
		beerSort = new BeerSort();
		ingredients = new ArrayList<>();
		beerSort.setName("Bell's Black Note Stout");
		beerSort.setType(BeerType.DARK);
		beerSort.setAlcoholic(true);
		beerSort.setManufacturer("Bell's Brewery");
		ingredients.add("two-row malt");
		ingredients.add("black malt");
		ingredients.add("roasted barley");
		ingredients.add("centennial hops");
		ingredients.add("calcium chloride");
		beerSort.setIngredientList(ingredients);
		beerSort.setChars(new Chars(11.2f, 32, true, 354, new SpillMethod(0.7f,"glass")));
		beerSortList.add(beerSort);
		beer.setBeerTypes(beerSortList);
		return beer;
	}
}
