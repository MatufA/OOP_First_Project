package test.java.filtersPack;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import main.java.databasePack.Network;
import main.java.filtersPack.FilterAll;

public class FilterAllTest extends TestCase {

	public void testFilter() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);

		List <List<Network>> temp = new ArrayList<>();
		temp.add(test);

		FilterAll filter = new FilterAll(temp);
		filter.filter();

		if (temp.isEmpty()){
			fail("Not yet implemented");

		}

	}

	public void testFilterNOT() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);

		List <List<Network>> temp = new ArrayList<>();
		temp.add(test);

		FilterAll filter = new FilterAll(temp);
		filter.filterNOT();

		if (temp.isEmpty()){
			fail("Not yet implemented");

		}	
	}
}
