package test.java.filtersPack;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import main.java.databasePack.Network;
import main.java.filtersPack.FilterAll;
import main.java.filtersPack.FilterByDate;

public class FilterByDateTest extends TestCase {

	public void testFilter() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
		
		Network b = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"29/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);
		test.add(b);

		List <List<Network>> temp = new ArrayList<>();
		temp.add(test);
		
		String start = "28/10/2017 20:10";
		String end = "29/10/2017 20:10";
		
		FilterByDate filter = new FilterByDate(temp,start ,end);
		filter.filter();

		if (temp.isEmpty()){
			fail("Not yet implemented");

		}
	}

	public void testFilterNOT() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
		
		Network b = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"29/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);
		test.add(b);

		List <List<Network>> temp = new ArrayList<>();
		temp.add(test);
		
		String start = "28/10/2017 20:10";
		String end = "29/10/2017 20:10";
		
		FilterByDate filter = new FilterByDate(temp,start ,end);
		filter.filterNOT();

		if (temp.isEmpty()){
			fail("Not yet implemented");

		}	}

	public void testComperable() {
		
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:11", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
		
		Network b = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"29/10/2017 20:00", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);
		test.add(b);

		List <List<Network>> temp = new ArrayList<>();
		temp.add(test);
		
		String start = "28/10/2017 20:10";
		String end = "29/10/2017 20:10";
		
		FilterByDate filter = new FilterByDate(temp,start ,end);
		
		if (!filter.comperable()){
			fail("Not yet implemented");
		}
	}

	public void testFilterByDate() {
		fail("Not yet implemented");
	}

}
