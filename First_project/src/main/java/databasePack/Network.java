package main.java.databasePack;

/**
 * The Class Network.
 */
public class Network {
	
	private String ssid, mac ,id , time;
	double lat, lon ,alt;
	private int frequncy, signal;
	
	/**
	 * Instantiates a new network.
	 * Null Network
	 */
	public Network() {
		this.mac = "NaN";
		this.ssid = "NaN";
		this.frequncy = 0;
		this.signal = 0;
		// dd/MM/yyyy HH:mm:ss
		this.time = "01//01//1000 00:00:00";
		this.id = "NaN";
		this.lat = 0;
		this.lon = 0;
		this.alt = 0;
	}
	/**
	 * Instantiates a new network.
	 * @author adiel ,adi and yuda
	 * @param String ssid
	 * @param String n mac
	 * @param Int frequncy
	 * @param Int signal
	 * @param String time
	 * @param String id
	 * @param String lat
	 * @param String lon
	 * @param String alt
	 */
	public Network(String nSsid, String nMac, int nFrequncy, int nSignal, 
			String nTime,String nId, double nLat, double nLon, double nAlt) {
		this.mac = (!nMac.equals(null)) ? nMac: "NaN";
		this.ssid = (!nSsid.equals(null)) ? nSsid:"NaN";
		this.frequncy = (nFrequncy != 0) ? nFrequncy:0;
		this.signal = (nSignal != 0) ? nSignal:0;
		// dd/MM/yyyy HH:mm:ss
		this.time = (!nTime.equals(null)) ? dateFix(nTime):"01//01//1000 00:00:00";
		this.id = (!nId.equals(null)) ? nId:"NaN";
		this.lat = (nLat != 0) ? nLat:0;
		this.lon = (nLon != 0) ? nLon:0;
		this.alt = (nAlt != 0) ? nAlt:0;
	}
	/**
	 * Instantiates a new network.
	 * @author adiel ,adi and yuda
	 * @param nmac
	 * @param nsignal
	 * @param nlat
	 * @param nlon
	 * @param nalt
	 */
	public Network(String nMac, double nLat, double nLon, double nAlt) {
		this.mac = (!nMac.equals(null)) ? nMac: "NaN";
		this.lat = (nLat != 0) ? nLat:0;
		this.lon = (nLon != 0) ? nLon:0;
		this.alt = (nAlt != 0) ? nAlt:0;
		this.ssid = "NaN";
		this.id = "NaN";
		this.frequncy = 0;
		this.signal = 0;
		this.time = "dd/MM/yyyy HH:mm:ss";
	}
	/*
	 * Copy network
	 * */
	public Network(Network other) {
		this.mac = (!other.getMac().equals(null)) ? other.getMac(): "NaN";
		this.ssid = (!other.getSsid().equals(null)) ? other.getSsid():"NaN";
		this.frequncy = (other.getFrequncy() != 0) ? other.getFrequncy():0;
		this.signal = (other.getSignal() != 0) ? other.getSignal():0;
		// dd/MM/yyyy HH:mm:ss
		this.time = (!other.getTime().equals(null)) ? other.getTime():"01//01//1000 00:00:00";
		this.id = (!other.getId().equals(null)) ? other.getId():"NaN";
		this.lat = (other.getLat() != 0) ? other.getLat():0;
		this.lon = (other.getLon() != 0) ? other.getLon():0;
		this.alt = (other.getAlt() != 0) ? other.getAlt():0;
	}
	/**/
	private String dateFix(String date) {
		String [] fix = date.split("/D");
		String second = (fix.length<6)? "00":fix[5] ;
		return fix[0]+"/"+fix[1]+"/"+fix[2]+" "+ fix[3]+":"+fix[4]+":"+second;
	}
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Gets the lat.
	 *
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}
	
	/**
	 * Gets the lon.
	 *
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}
	
	/**
	 * Gets the alt.
	 *
	 * @return the alt
	 */
	public double getAlt() {
		return alt;
	}
	
	/**
	 * Gets the mac.
	 *
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Gets the ssid.
	 *
	 * @return the ssid
	 */
	public String getSsid() {
		return ssid;
	}

	/**
	 * Gets the frequncy.
	 *
	 * @return the frequncy
	 */
	public int getFrequncy() {
		return frequncy;
	}

	/**
	 * Gets the signal.
	 *
	 * @return the signal
	 */
	public int getSignal() {
		return signal;
	}
	@Override
	public String toString() {
		return "Network [SSID=" + ssid + ", MAC=" + mac + ", ID=" + id + ", Time=" + time + ", Latitude=" + lat + ", Lontitude="
				+ lon + ", Altitude=" + alt + ", Frequncy=" + frequncy + ", Signal=" + signal + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Network))
			return false;
		Network other = (Network) obj;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		return true;
	}

}
