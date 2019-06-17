package org.zerock.data;

import java.util.LinkedHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class RatioHour.
 */
public class RatioHour {
	
	/** The ratio hour. */
	private LinkedHashMap<String, Float> ratioHour;
	
	/**
	 * Instantiates a new ratio hour.
	 */
	public RatioHour()
	{
		ratioHour = new LinkedHashMap<String, Float>();
		
		ratioHour.put("00", (float)3.2);
		ratioHour.put("01", (float)2.2);
		ratioHour.put("02", (float)1.52);
		ratioHour.put("03", (float)1.02);
		ratioHour.put("04", (float)0.78);
		ratioHour.put("05", (float)0.72);
		ratioHour.put("06", (float)0.85);
		ratioHour.put("07", (float)1.44);
		ratioHour.put("08", (float)3.05);
		ratioHour.put("09", (float)5.02);
		ratioHour.put("10", (float)6.75);
		ratioHour.put("11", (float)7.02);
		ratioHour.put("12", (float)6.2);
		ratioHour.put("13", (float)6.5);
		ratioHour.put("14", (float)6.88);
		ratioHour.put("15", (float)6.82);
		ratioHour.put("16", (float)6.75);
		ratioHour.put("17", (float)6.22);
		ratioHour.put("18", (float)5.54);
		ratioHour.put("19", (float)4.23);
		ratioHour.put("20", (float)4.33);
		ratioHour.put("21", (float)4.55);
		ratioHour.put("22", (float)4.24);
		ratioHour.put("23", (float)4.17);
		
	}
	
	/**
	 * Gets the ratio hour.
	 *
	 * @return the ratio hour
	 */
	public LinkedHashMap<String, Float> getRatioHour() {
		return ratioHour;
	}
	
	/**
	 * Sets the ratio hour.
	 *
	 * @param ratioHour the ratio hour
	 */
	public void setRatioHour(LinkedHashMap<String, Float> ratioHour) {
		this.ratioHour = ratioHour;
	}
	

}
