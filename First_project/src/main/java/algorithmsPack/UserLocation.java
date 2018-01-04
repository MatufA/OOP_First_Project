package main.java.algorithmsPack;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import main.java.databasePack.Network;
/*User location revaluation -
 * Get from the user list which filtered by three MACs addresses,
 * and calculate Weighted Center of every MAC,
 * then calculate the position of the user according
 * to Weighted Center of each one
 */
public class UserLocation {
	/*Database - from user*/
	private List<List<Network>> database;
	/*Filtered database by strongest signal*/
	private List<Network> filteredDatbase;
	/*List of filtered wifi network*/
	private List<Network> data = new ArrayList<>();
	/*Database of PI for each Network point*/
	HashMap<Network, Double> piMap;
	/* Constructor
	 * @param List<Network>  database (filtered by three MACs)
	 * */
	public UserLocation(List<List<Network>> file) {
		this.database.addAll(file);
		this.filteredDatbase = new ArrayList<>();
		this.piMap = new HashMap<>();
	}
	/*Calculate Weighted Center of every MAC
	 * then calculate the position of the user according
	 * to MACs addresses and the signel of each one
	 * @param String MAC_1 
	 * @param Integer signal_1 
	 * @param String MAC_2 
	 * @param Integer signal_2 
	 * @param String MAC_3
	 * @param Integer signal_3
	 * @return Network (weighted center point)
	 * */
	public Network WeightedCenter(List<Network> listOfwifi) {
		/*Hsa map of given MAC and signal øespectively*/
		HashMap<String, Integer> userMAC = new HashMap<>();
		for (Network network : listOfwifi) {
			userMAC.put(network.getMac(), network.getSignal());
		}
		sort(database);
		/*List of filtered by given MACs*/
		List<List<Network>> listOfMAC = getOrder();
		listOfMAC = threeNetwork(listOfMAC,listOfwifi);
		Network temp;
		
		for (List<Network> list : listOfMAC) {
			if(!list.isEmpty()) {
				double pi = 1, weightOfeachTest = 1;
				temp = list.get(0);
				for (Network net : list) {
					weightOfeachTest *= weight( diff(net, userMAC.get(net.getMac())), userMAC.get(net.getMac()) );
				}
				pi *= weightOfeachTest;
				filteredDatbase.add(temp);
				piMap.put(temp, pi);
			}
		}
		if(filteredDatbase.size()>3) filteredDatbase = strongestSignal(3);
		List<List<Network>> templist = new ArrayList<>();
		templist.add(filteredDatbase);
		WifiSpotLocation totalWeightCenter = new WifiSpotLocation(templist);
		return totalWeightCenter.WeightedAverage();
	}
	/*Calculate difference between point signal to input signal*/
	private double diff(Network point , double input) {
		return (point.getSignal()== 0)? 100 : Math.max(Math.abs(input - point.getSignal()), 3);
	}
	/*Calculate wieght of each Network point*/
	private double weight(double diff, double input) {
		return 10_000/(Math.pow(diff, 0.4) * Math.pow(input, 0.4));
	}

	/**
	 * Returns the k rows with the highest signal values.
	 * Implemented using Streams.
	 * https://github.com/erelsgl/ariel-oop-course/blob/master/YomHamishi/src/lesson7/WifiDatabase.java
	 */
	private List<Network> strongestSignal(int k) {
		return filteredDatbase
			.stream()
			.parallel()
			.sorted(Comparator.comparing(net -> piMap.get(net)))
			.limit(k)
			.collect(Collectors.toList());
	} 
	/*Sort List<Network> by date*/
	private List<Network> sort(List<List<Network>> file){
		for (List<Network> list : database) {
			for (Network network : list) {
				data.add(network);
			}
		}
		//Sort by Date
			Collections.sort(data,new Comparator<Network>() {
				public int compare(Network wifiLine1, Network wifiLine2) {
					try {
						DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date wifi1Time =  df1.parse(wifiLine1.getTime().trim());
						Date wifi2Time =  df1.parse(wifiLine2.getTime().trim());
						if(wifi2Time.before(wifi1Time))
							return -1;
						if(wifi1Time.equals(wifi2Time))
							return 0;
						return 1;
					}catch (ParseException e) {
						e.getCause();
						return 0;
					}
				}
			});
			return data;
	}
	/*Sort List<List<Network>> by date*/
	private List<List<Network>> getOrder(){
		List<List<Network>> listOfMAC = new ArrayList<>();
		List<Network> tempList= new ArrayList<>();
		String time = data.get(0).getTime();
		for (Network network : data) {
			if(network.getTime().equals(time)) {
				tempList.add(network);
			}else {
				time = network.getTime();
				listOfMAC.add(tempList);
				tempList.clear();
				tempList.add(network);
			}
		}
		return listOfMAC;
	}
	
	private List<List<Network>> threeNetwork(List<List<Network>> listOfMAC, List<Network> mac){
		List<List<Network>> checklist = new ArrayList<>();
		List<Network> tempList = new ArrayList<>();
		for (List<Network> list : listOfMAC) {
			tempList.addAll(list);
			for (Network network : list) {
				if(!mac.contains(network)) tempList.add(new Network(network.getMac(), network.getLat(), network.getLon(), network.getAlt()));
			}
			
		}
		return checklist;
	}
}
