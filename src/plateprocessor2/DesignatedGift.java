package plateprocessor2;

import java.util.ArrayList;

public class DesignatedGift {
	private ArrayList<Double> amounts = new ArrayList<Double>();
	private String name;

	public void addAmount(Double value) {
		amounts.add(value);
	}
	
	public double getTotal() {
		double total = 0;
		
		for(double amount : amounts) {
			total += amount;
		}
		
		return total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
