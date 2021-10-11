package plateprocessor2;

public class DesignatedFund {
	
	public enum Type{GIFT,REIMBURSEMENT};
	
	private String name;
	private Type type;
	private double amount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	

}
