package main.java.algorithmsPack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import main.java.databasePack.Network;
/*Location revaluation -
 * Get from the user list of filtererd by MAC addresses 
 * and calculate Weighted Center of given MAC,
 */
public class WifiSpotLocation {
	/*Database - from user*/
	private List<List<Network>> database;
	/*List of filtered wifi network*/
	private List<Network> data = new ArrayList<>();
	Network wcenter;

	/*
	 * Constructor
	 * @param List<Network> database (filtered by MAC)
	 * */
	public WifiSpotLocation(List<List<Network>> macFiltered) {
		this.database = macFiltered;
		System.out.println(database.get(0).toString());
		
	}
	/*Get weighted average cordinate of specific Network*/
	public Network WeightedAverage() {
		try {
			if(database.isEmpty()) return new Network("NaN", 0, 0, 0);
			for (List<Network> list : database) {
				for (Network network : list) {
					data.add(network);
				}
			}
			if(data.size() == 1) return new Network(database.get(0).get(0).getMac(), 
					database.get(0).get(0).getLat(), database.get(0).get(0).getLon(), database.get(0).get(0).getAlt());
			/*List of 3 sorted database by signal*/
			data = strongestSignal(3);
			double avgAlt = 0, avgLon = 0, avgLat = 0,wieght = 0;;
			/*Sum the Alt, Lon and Lat from the list*/
			for (Network net : data) {
				double wAlt = net.getAlt() * (1/Math.pow(net.getSignal(), 2));
				double wLon = net.getLon() * (1/Math.pow(net.getSignal(), 2));
				double wLat = net.getLat() * (1/Math.pow(net.getSignal(), 2));
				wieght += 1/Math.pow(net.getSignal(), 2);
				avgAlt += wAlt;
				avgLon += wLon;
				avgLat += wLat;
			}
			/*Get the average*/
			avgAlt /= wieght;
			avgLon /= wieght;
			avgLat /= wieght;
			/*Get Network element*/
			wcenter = new Network(data.get(0).getMac(), avgLat, avgLon, avgAlt);
			return wcenter;
		}catch (ArithmeticException ex) {
			ex.printStackTrace();
			return new Network();
		}
		}
	/*Get average Network*/
	public Network Average() {
		/*List of 3 sorted database by signal*/
		List<Network> data = strongestSignal(3);
		double avgAlt = 0, avgLon = 0, avgLat = 0;
		/*Sum the Alt, Lon and Lat from the list*/
		for (Network net : data) {
			avgAlt += net.getAlt();
			avgLon += net.getLon();
			avgLat += net.getLat();
		}
		/*Get the average*/
		avgAlt /= data.size();
		avgLon /= data.size();
		avgLat /= data.size();
		/*Get Network element*/
		return new Network(data.get(0).getMac(), avgLat, avgLon, avgAlt);
	}
	/**
	 * Returns the k rows with the highest signal values.
	 * Implemented using Streams.
	 * https://github.com/erelsgl/ariel-oop-course/blob/master/YomHamishi/src/lesson7/WifiDatabase.java
	 */
	public List<Network> strongestSignal(int k) {
		return data
			.stream()
			.parallel()
			.sorted(Comparator.comparing(net -> -net.getSignal()))
			.limit(k)
			.collect(Collectors.toList());
}
	@Override
	public String toString() {
		return "Wifi Spot Location [Weighted Average = " + wcenter.toString() + "]";
	}
}
