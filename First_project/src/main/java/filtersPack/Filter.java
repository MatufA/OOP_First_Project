package main.java.filtersPack;

import java.util.List;

import main.java.databasePack.Network;

/**
 * The abstract class Filter.
 */
public abstract class Filter {
/*
 * Filter function
 * After Building Filter By - object
 * run this function
 * @return String
 * */
public abstract boolean filter();
/*
 * Exclude filter function
 * After Building Filter By - object
 * run this function
 * @return String
 * */
public abstract boolean filterNOT();
/*
 * comperable function
 * implement by each filter 
 * */
public abstract boolean comperable();
/*
 * get database after filter
 * implement by each filter 
 * */
public abstract List<List<Network>> getFilteredFile();
/**
 * get size of database after filter
 * @return
 */
public abstract int getSize();
}


