package main.java.filtersPack;

import java.util.ArrayList;
import java.util.List;

import main.java.databasePack.Network;

public class FilterByID extends Filter{
	/** The file. The final database*/
	protected List<List<Network>> file ,filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String parameter;
	private int size=0;
	/**
	 * Instantiates a new filter by ID.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param parameter
	 */
	public FilterByID(List<List<Network>> csvList ,String parameter) {
		this.file = csvList;
		this.filteredFile = new ArrayList<>();
		this.parameter = parameter;
	}
	/*
	 *After constract Filter by ID, run filter function 
	 *@return String (Succed of Fail)
	 */
	@Override
	public boolean filter() {
		List<Network> tempList;
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
	 *After constract Filter by ID, run filter function 
	 *@return String (Succed of Fail)
	 */
	@Override
	public boolean filterNOT() {
		List<Network> tempList;
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
	 *If the ID of the object is equal to
	 *the given ID (String) 
	 *@return TRUE 
	 *else
	 *@return FALSE
	 */
	@Override
	public boolean comperable() {
		return wifiSpot.getSsid().equals(parameter.trim());
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
