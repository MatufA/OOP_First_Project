package main.java.filtersPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.databasePack.Network;

public class FilterByNumOfMAC extends Filter{
	/** The file. The final database*/
	protected List<List<Network>> file, filteredFile;
	private int size=0;
	/*Network object*/
	protected Network wifiSpot;
	/**/
	private List<Network> lineOfNetwork;
	/**/
	HashMap<String, Integer> mac = new HashMap<>(); 
	/**
	 * Instantiates a new filter by Three MACs.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param 
	 */
	public FilterByNumOfMAC(List<List<Network>> csvList ,List<Network> lineOfNetwork) {
		this.file = new ArrayList<>();
		file.addAll(csvList);
		this.filteredFile = new ArrayList<>();
		this.lineOfNetwork.addAll(lineOfNetwork);
		for (Network network : lineOfNetwork) {
			mac.put(network.getMac(), 1);
		}
	}
	/*
	 *After constract Filter by Three MACs, run filter function 
	 *@return String (Succed of Fail)
	 */
	@Override
	public boolean filter() {
		List<Network> tempList = new ArrayList<>();
		if(!this.file.isEmpty()) {
			for (List<Network> runList: this.file) {
				if(runList.size() >= 1) {
					tempList = new ArrayList<>();
					for (Network network : runList) {
						wifiSpot = new Network(network);
						if(comperable()) {
							tempList.add(network);
						}
					}
					if(tempList.size() >=1) {
						size += tempList.size();
						this.filteredFile.add(tempList);
					}
				}	
			}
			return true;
		}else {
			return false;
			}
	}
	/*
	 *After constract Filter by Three MACs, run filter function 
	 *@return String (Succed of Fail)
	 */
	@Override
	public boolean filterNOT() {
		List<Network> tempList = new ArrayList<>();
		if(!this.file.isEmpty()) {
			for (List<Network> runList: this.file) {
				if(runList.size() >= 1) {
					tempList = new ArrayList<>();
					for (Network network : runList) {
						wifiSpot = new Network(network);
						if(!comperable()) {
							tempList.add(network);
						}
					}
					if(tempList.size() >=1) {
						size += tempList.size();
						this.filteredFile.add(tempList);
					}
				}	
			}
			return true;
		}else {
			return false;
			}
	}
	/*
	 *Comperable type
	 *If the MAC of the object is equal
	 *to the given MACs (String) 
	 *@return TRUE 
	 *else
	 *@return FALSE
	 */
	@Override
	public boolean comperable() {
		return mac.containsKey(wifiSpot.getMac());
	}
	/*
	 * Filtered database
	 * @return List<Network>
	 */
	@Override
	public List<List<Network>> getFilteredFile() {
		return this.filteredFile;
	}
	/*Gets number of elements after filter*/
	@Override
	public int getSize() {
		return size;
	}

}
