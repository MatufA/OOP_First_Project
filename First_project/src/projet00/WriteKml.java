package projet00;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

// TODO: Auto-generated Javadoc
/**
 * The Class WriteKml.
 * The class gets List<String []> ,HashMap<String, Integer> which provided by Filter
 */
public class WriteKml implements Write{
	List<Network> listOfNet;
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
	 * @param keyIndex {@link https://labs.micromata.de/projects/jak/kml-in-the-java-world.html}
	 * @return Kml file
	 */

	public WriteKml(List<Network> kmlList){
		this.listOfNet = kmlList;
		write();
	}
	
	public String timeStampFormate(String time) {
		String[] getTime = time.split("\\D");
		String newTimeFormate = getTime[2] + "-" + getTime[1] + "-" +getTime[0] + "T" + 
		getTime[3] + ":" + getTime[4] + ":" + getTime[5] + "Z";
		
		return newTimeFormate;
	}

	@Override
	public String write() {
		try {
			final Kml writekml = new Kml();
			Document document = writekml.createAndSetDocument();
			for (Network wifiSpot : listOfNet) {
				Placemark placemark = document.createAndAddPlacemark()
					.withName(wifiSpot.getSsid()).withOpen(Boolean.TRUE);
				placemark.withId(wifiSpot.getId()).withDescription("MAC: " + wifiSpot.getMac() +
							"\nFrequncy: " + wifiSpot.getFrequncy() + 
							"\nSignal: " + wifiSpot.getSignal())
				.createAndSetPoint().addToCoordinates(Double.parseDouble(wifiSpot.getLon())
													, Double.parseDouble(wifiSpot.getLat()) 
													, Double.parseDouble(wifiSpot.getAlt()));
				placemark.createAndSetTimeStamp().withWhen(timeStampFormate(wifiSpot.getTime()));			
			}
			writekml.marshal(new File("KmlFile.kml"));
			return "Kml created!!";
		} catch (FileNotFoundException | NullPointerException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		
	}
}
