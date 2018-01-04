package main.java.filtersPack;

import java.util.ArrayList;
import java.util.List;

import main.java.databasePack.Network;

public class FilterByLocation extends Filter{
	/** The file. */
	protected List<List<Network>> file , filteredFile;
	/*The final database*/
	/*protected List<Network> filteredFile;*/
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private double radius , latitude , longitude;
	private int size = 0;
	/**
	 * Instantiates a new filter by location.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param radius
	 * @param latitude
	 * @param longitude
	 */
	public FilterByLocation(List<List<Network>> csvList ,double radius ,double latitude, double longitude) {
		this.file = new ArrayList<>(csvList);
		this.filteredFile = new ArrayList<>();
		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/*
	 *After constract Filter by location, run filter function 
	 *@return String (Succed of Fail)
	 * */
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
	 *After constract Filter by location, run filter function 
	 *@return String (Succed of Fail)
	 * */
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
	 *If the distance between te object to
	 *the given point is smaller then radius 
	 *@return TRUE 
	 *else
	 *@return FALSE
	 * */
	@Override
	public boolean comperable() {
		return radius >= CoordDistance(latitude, longitude ,wifiSpot);
	}
	/*Calculate distance between two points
	 * @return double
	 * */
	double CoordDistance(double latitude, double longitude, Network point)
	{
	    return 6371 * Math.acos(
	        Math.sin(latitude) * Math.sin(point.getLat())
	        + Math.cos(latitude) * Math.cos(point.getLat()) * Math.cos(point.getLon() - longitude));
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
