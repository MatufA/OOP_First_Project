package test.java.databasePack;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.databasePack.Network;

// TODO: Auto-generated Javadoc
/**
 * The Class NetworkTest.
 */
public class NetworkTest {
	
	/**
	 * Test network string string int int string string string string string.
	 */
	@Test
	public void testNetworkStringStringIntIntStringStringStringStringString() {
		Network a = new Network("Bezeq-NGN_E9CBFB", "cc:b2:55:e9:cb:fc", 6, -68, 
				"28/10/2017 20:10", "ONEPLUS A3003" , 32.09038727, 34.87862948, 56);
		String output = a.getSsid();
		assertEquals("Bezeq-NGN_E9CBFB", output);
		output = a.getMac();
		assertEquals("cc:b2:55:e9:cb:fc", output);
		int Ioutput = a.getFrequncy();
		assertEquals(6, Ioutput);
		Ioutput = a.getSignal();
		assertEquals(-68, Ioutput);
		output = a.getTime();
		assertEquals("28/10/2017 20:10", output);
		output = a.getId();
		assertEquals("ONEPLUS A3003", output);
		double doutput = a.getLat();
		assertEquals("32.09038727", doutput);
		doutput = a.getLon();
		assertEquals("34.87862948", doutput);
		doutput = a.getAlt();
		assertEquals("56", doutput);
	}



}
