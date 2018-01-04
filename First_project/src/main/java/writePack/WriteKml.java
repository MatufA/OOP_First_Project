package main.java.writePack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import main.java.databasePack.Network;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * The Class WriteKml.
 * The class gets List<String []> ,HashMap<String, Integer> which provided by Filter
 * return KML file for Google Earth
 */
public class WriteKml implements Write{
	List<List<Network>> listOfNet;
	/**
	 * Instantiates a new write kml.
	 */
	public WriteKml() {

	}
	/**
	 * Instantiates a new write kml.
	 *
	 * @author adiel ,adi and yuda
	 * @param kmlList the kml list
	 * @param keyIndex 
	 * {@link https://labs.micromata.de/projects/jak/kml-in-the-java-world.html}
	 */
	public WriteKml(List<List<Network>> kmlList){
		this.listOfNet = kmlList;
	}
	/*Convert date from String to TimeStame Signature
	 * @param String time
	 * @return String 
	 * */
	public String timeStampFormate(String time) {
		String[] getTime = time.split("\\D");
		String newTimeFormate = getTime[2] + "-" + getTime[1] + "-" +getTime[0] + "T" + 
		getTime[3] + ":" + getTime[4] + ":" + getTime[5] + "Z";
		
		return newTimeFormate;
	}
	/*Implement Write class
	 * */
	@Override
	public String write() {
		try {
			Date timeDate = new Date();
			final Kml writekml = new Kml();
			Document document = writekml.createAndSetDocument();
			for (List<Network> list : listOfNet) {	
				for (Network wifiSpot : list) {
					Placemark placemark = document.createAndAddPlacemark()
						.withName(wifiSpot.getSsid()).withOpen(Boolean.TRUE);
					placemark.withId(wifiSpot.getId()).withDescription("MAC: " + wifiSpot.getMac() +
								"\nFrequncy: " + wifiSpot.getFrequncy() + 
								"\nSignal: " + wifiSpot.getSignal())
					.createAndSetPoint().addToCoordinates(wifiSpot.getLon()
														, wifiSpot.getLat() 
														,wifiSpot.getAlt());
					placemark.createAndSetTimeStamp().withWhen(timeStampFormate(wifiSpot.getTime()));			
	
				}
			}
			writekml.marshal(new File("KmlFile - " + timeDate.getTime() + ".kml"));
			return "Kml created!!";
		} catch (FileNotFoundException | NullPointerException e) {
			return e.getMessage();
		}
	}
}
