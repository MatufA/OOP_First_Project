package projet00;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FilterByDate extends Filter{
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String start , end;
	
	public FilterByDate(List<List<Network>> csvList , String start , String end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean comperable() {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date wifiDate = df.parse(wifiSpot.getTime().trim());
			Date startTime =  df.parse(start);
			Date endTime = df.parse(end);
			return wifiDate.after(startTime) && wifiDate.before(endTime);
		} catch (ParseException e) {
			System.out.println("Problem occurred while data format!");
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Network> getFilteredFile() {
		return filteredFile;
	}
}