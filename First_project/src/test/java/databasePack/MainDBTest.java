package test.java.databasePack;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import main.java.databasePack.MainDB;
import main.java.databasePack.Network;

public class MainDBTest extends TestCase {


	public void testAdd() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
		List <Network> test = new ArrayList <Network>();
		test.add(a);
		MainDB temp = new MainDB();
		temp.addList(test);

		if (temp.getdatabase().get(0).get(0) instanceof Network == false){
			fail("Not yet implemented");
		}
	}

	public void testAddList() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
		List <Network> test = new ArrayList <Network>();
		test.add(a);
		MainDB temp = new MainDB();
		temp.addList(test);

		if (temp.getdatabase().get(0).get(0) instanceof Network == false){
			fail("Not yet implemented");
		}
	}

	public void testRemoveAll() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);

		MainDB temp = new MainDB();
		temp.addList(test);

		temp.removeAll();

		if (!temp.isEmpty()){
			fail("Not yet implemented");

		}
	}

	public void testRemove() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);

		MainDB temp = new MainDB();
		temp.addList(test);

		temp.remove(0);

		if (!temp.isEmpty()){
			fail("Not yet implemented");

		}
	}

	public void testHasMAC() {

		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);

		MainDB temp = new MainDB();
		temp.addList(test);

		String name = "cc:b2:55:e9:cb:fc";

		assertEquals(name, a.getMac());

	}

	public void testHasID() {

		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);

		List <Network> test = new ArrayList <Network>();
		test.add(a);

		MainDB temp = new MainDB();
		temp.addList(test);

		String name = "ONEPLUS A3003";

		assertEquals(name, a.getId());	}

	public void testIsEmpty() {

		Network a = new Network();

		List <Network> test = new ArrayList <Network>();
		test.add(a);

		MainDB temp = new MainDB();
		//temp.addList(test);

		if (!temp.isEmpty()){
			fail("Not yet implemented");
		}
	}

}
