package plateprocessor2;

import java.util.ArrayList;

public class Envelope implements HasCash{
	
	private Cash cash = new Cash();
	private Check check;
	boolean hasCheck = false;
	private ArrayList<DesignatedFund> designatedFunds = new ArrayList<DesignatedFund>();
	private String envelopeNumber;
	private String comment;
	private String name;
	private String address;
	
	public Cash getCash() {
		return cash;
	}
	public void setCash(Cash cash) {
		this.cash = cash;
	}
	public Check getCheck() {
		return check;
	}
	public void setCheck(Check check) {
		this.check = check;
		hasCheck = true;
	}
	public String getEnvelopeNumber() {
		return envelopeNumber;
	}
	public void setEnvelopeNumber(String envelopeNumber) {
		this.envelopeNumber = envelopeNumber;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<DesignatedFund> getDesignatedFunds() {
		return designatedFunds;
	}
	
	public void addDesignatedFund(DesignatedFund fund) {
		designatedFunds.add(fund);
	}
	
	public boolean hasDesignatedFund() {
		return designatedFunds.size() > 0;
	}
	
}
