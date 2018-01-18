package main.java.databasePack;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainDB {
	private List<List<Network>> _database;
	private int _size;
/**
 * Initilize
 * @author adiel, adi and yuda
 * */
	public MainDB() {
		_database = new ArrayList<>();
		_size=0;
	}
	/**
	 * Initilize
	 * @author adiel, adi and yuda
	 * @param database list
	 * */
	public MainDB(List<List<Network>> database) {
		this._database = new ArrayList<>();
		this._database.addAll(database);
		_size=0;
		removeEmpty();
		sort();
		removeDuplicate();
	}
	/**
	 * Initilize
	 * @author adiel, adi and yuda
	 * @param database list
	 * */
	public MainDB(MainDB other) {
		this._database = new ArrayList<>();
		this._database.addAll(other.getdatabase());
		_size=0;
		removeEmpty();
		sort();
		removeDuplicate();
	}
	/**
	 * Add another full database
	 * @param add list of  data 
	 * @return True if secceed, else False 
	 */
		public boolean add(MainDB other) {
			if(!other.isEmpty()) {
					_database.addAll(other.getdatabase());
					removeEmpty();
					sort();
					removeDuplicate();
					return true;
			}
			return false;
		}
/**
 * Add another database
 * @param add list of  data 
 * @return True if secceed, else False 
 */
	public boolean add(List<List<Network>> data) {
		if(!data.isEmpty()) {
				_database.addAll(data);
				removeEmpty();
				sort();
				removeDuplicate();
				return true;
		}
		return false;
	}
/**
 * Add line to database
 * @param add one line of data
 * @return True if secceed, else False
 */
	public boolean addList(List<Network> data) {
		if(data.get(0) instanceof Network) {
			_database.add(data);
			removeEmpty();
			sort();
			removeDuplicate();
			return true;
		}else return false;
	}
	/**
	 * Remove all database
	 */
	public void removeAll() {
		if(!isEmpty()) {
			_database.removeAll(_database);
		}
	}	
	/**
	 * Remove line
	 * @param place of line
	 */
	public void remove(int i) {
		if(!isEmpty()) {
			_database.remove(i);
		}
	}
	/**
	 * Sort by date
	 */
	private void sort() {
		if(_database.size()>1) {
			//Sort by Date
				Collections.sort(_database,new Comparator<List<Network>>() {
					public int compare(List<Network> wifiLine1, List<Network> wifiLine2) {
						try {
							DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							Date wifi1Time =  df1.parse(wifiLine1.get(0).getTime().trim());
							Date wifi2Time =  df1.parse(wifiLine2.get(0).getTime().trim());
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
		}
	}
	/**
	 * Remove if there is an empty line
	 */
	private void removeEmpty() {
		if(!isEmpty()) {
			for (List<Network> list : _database) {
				if(list.isEmpty()) _database.remove(list);
				else _size += list.size();
			}
		}
	}
/*
 * Remove duplicate if exists
 */
	private void removeDuplicate() {
		if(_database.size()>1) {
			for (int i =0; i < _database.size()-1; i++) {
				if( _database.get(i).get(0).getId().equalsIgnoreCase(_database.get(i+1).get(0).getId()) 
						&&_database.get(i).get(0).getTime().equalsIgnoreCase(_database.get(i+1).get(0).getTime()) ) {
					_database.remove(i+1);
					_size--;
				}
				i--;
			}
		}
		
	}
	/**
	 * Check if the MAC address exists in the database
	 * @param MAC address
	 * @return True if exists, else False
	 */
	public boolean hasMAC(String name) {
		if(!isEmpty()) {
			for (List<Network> list : _database) {
				for (Network network : list) {
					if (network.getMac().equalsIgnoreCase(name.trim())) return true;
				}
			}
		}
		return false;
		
	}
	/**
	 * Check if the ID exists in the database
	 * @param ID address
	 * @return True if exists, else False
	 */
	public boolean hasID(String name) {
		if(!isEmpty()) {
			for (List<Network> list : _database) {
				for (Network network : list) {
					if (network.getId().equalsIgnoreCase(name.trim())) return true;
				}
			}
		}
		return false;
	}
	/**
	 * Check if database is empty
	 * @return True if empty, else False
	 */
	public boolean isEmpty() {
		return _database.isEmpty();
	}
	/**
	 * Get current size
	 * @return size 
	 */
	public int get_size() {
		return _size;
	}
	/**
	 * Get all database
	 * @return database as list
	 */
	public List<List<Network>> getdatabase() {
		return this._database;
	}
	
}
