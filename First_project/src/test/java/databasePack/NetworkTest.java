package test.java.databasePack;


import junit.framework.TestCase;
import main.java.databasePack.Network;

public class NetworkTest extends TestCase {

		public void testNetworkStringStringIntIntStringStringDoubleDoubleDouble() {
			Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
					"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
			String output = a.getSsid();
			assertEquals("Bezeq-NGN_E9CBFB", output);
			output = a.getMac();
			assertEquals("cc:b2:55:e9:cb:fc", output);
			int Ioutput = a.getFrequncy();
			assertEquals(6, Ioutput);
			Ioutput = a.getSignal();
			assertEquals(-68, Ioutput);
			String output1 = a.getTime();
			assertEquals("28/10/2017 20:10:00", output1);
			output1 = a.getId();
			assertEquals("ONEPLUS A3003", output1);
			double doutput = a.getLat();
			assertEquals(32.09038727, doutput);
			doutput = a.getLon();
			assertEquals(34.87862948, doutput);
			doutput = a.getAlt();
			assertEquals(56.0, doutput);
		}

		public void testNetworkStringDoubleDoubleDouble() {
			
			Network a = new Network("cc:b2:55:e9:cb:fc", 32.09038727, 34.87862948, 56.0);
			
			String output = a.getMac();
			assertEquals("cc:b2:55:e9:cb:fc", output);
			double doutput = a.getLat();
			assertEquals(32.09038727, doutput);
			doutput = a.getLon();
			assertEquals(34.87862948, doutput);
			doutput = a.getAlt();
			assertEquals(56.0, doutput);
		}

		public void testNetworkNetwork() {
			Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
					"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
			String output = a.getSsid();
			assertEquals("Bezeq-NGN_E9CBFB", output);
			output = a.getMac();
			assertEquals("cc:b2:55:e9:cb:fc", output);
			int Ioutput = a.getFrequncy();
			assertEquals(6, Ioutput);
			Ioutput = a.getSignal();
			assertEquals(-68, Ioutput);
			String output1 = a.getTime();
			assertEquals("28/10/2017 20:10:00", output1);
			output1 = a.getId();
			assertEquals("ONEPLUS A3003", output1);
			double doutput = a.getLat();
			assertEquals(32.09038727, doutput);
			doutput = a.getLon();
			assertEquals(34.87862948, doutput);
			doutput = a.getAlt();
			assertEquals(56.0, doutput);
		}

		public void testEqualsObject() {
			Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
					"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
			Network b = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
					"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56.0);
			
			if (!a.equals(b)){
				fail("Not yet implemented");
			}

		}

	}
