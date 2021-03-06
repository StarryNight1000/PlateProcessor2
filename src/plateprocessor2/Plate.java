package plateprocessor2;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import plateprocessor2.DesignatedFund.Type;

public class Plate implements HasCash{
	
	private Cash cash = new Cash();
	
	private ArrayList<Envelope> envelopes = new ArrayList<Envelope>();
	
	private String counter1 = "", counter2 = "";
	

	public String getCounter1() {
		return counter1;
	}

	public void setCounter1(String counter1) {
		this.counter1 = counter1;
	}

	public String getCounter2() {
		return counter2;
	}

	public void setCounter2(String counter2) {
		this.counter2 = counter2;
	}
	
	public Cash getCash() {
		return cash;
	}
	
	
	public double getCheckTotal() {
		double total = 0;
		for(Envelope envelope : envelopes) {
			if(envelope.hasCheck) {
				total += envelope.getCheck().getAmount();
			}	
		}
		
		return total;
	}
	
	public double getTotal() {
		double total = 0;
		total += cash.getCashTotal();
		total += getCheckTotal();
		return total;
	}
	
	public double getReimburseTotal() {
		double total = 0;
		for(Envelope envelope : envelopes) {
			if(envelope.hasDesignatedFund()) {
				for(DesignatedFund fund : envelope.getDesignatedFunds()) {
					if(fund.getType() == DesignatedFund.Type.REIMBURSEMENT) {
						total += fund.getAmount();
					}
				}
			}
			
		}
		return total;
	}
	
	HashMap<String,Double> getSummarizedGifts(){
		HashMap<String,Double> summary = new HashMap<String,Double>();
		for(Envelope envelope : envelopes) {
			for(DesignatedFund fund : envelope.getDesignatedFunds()) {
				if(fund.getType() == DesignatedFund.Type.GIFT) {
					if(summary.containsKey(fund.getName())) {
						summary.put(fund.getName(), summary.get(fund.getName()) + fund.getAmount());
					}else {
						summary.put(fund.getName(), fund.getAmount());
					}
				}
			}
		}
		
		return summary;
		
	}
	
	HashMap<String,Double> getSummarizedReimbursements(){
		HashMap<String,Double> summary = new HashMap<String,Double>();
		for(Envelope envelope : envelopes) {
			for(DesignatedFund fund : envelope.getDesignatedFunds()) {
				if(fund.getType() == DesignatedFund.Type.REIMBURSEMENT) {
					if(summary.containsKey(fund.getName())) {
						summary.put(fund.getName(), summary.get(fund.getName()) + fund.getAmount());
					}else {
						summary.put(fund.getName(), fund.getAmount());
					}
				}
			}
		}
		
		return summary;
		
	}
	
	public double getGiftTotal() {
		double total = 0;
		for(Envelope envelope : envelopes) {
			if(envelope.hasDesignatedFund()) {
				for(DesignatedFund fund : envelope.getDesignatedFunds()) {
					if(fund.getType() == DesignatedFund.Type.GIFT) {
						total += fund.getAmount();
					}
				}
			}
			
		}
		return total;
	}
	
	public double getGeneralFundTotal() {
		double total = getTotal();
		total -= getReimburseTotal();
		total -= getGiftTotal();
		return total;
	}

	public String generateCheckPrintout() {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		String output  = "Checks Recieved:\n";
		
		int width = 0;
		for(Envelope envelope : envelopes) {
			if(envelope.hasCheck) {
				String amount = String.valueOf(envelope.getCheck().getAmount());
				width = amount.length() > width? amount.length() : width;
			}
		}
		
		width += 8; //add 7 chars for the word Total: and the $ sign
		for(Envelope envelope : envelopes) {
			if(envelope.hasCheck) {
				String dollarAmount = formatter.format(envelope.getCheck().getAmount());
				output += String.format("%1$" + width + "s", dollarAmount) + "\n";
			}	
		}
		
		output += "-".repeat(width) + "\n";
		output += "Total:\n";
		output += String.format("%1$" + width + "s", formatter.format(getCheckTotal())) + "\n";
		return output;
	}

	public String getPreviewText() {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String preview = "";
		
		preview += "General Fund: " + formatter.format(getGeneralFundTotal()) + "\n";
		preview += "Reimbursements: " + formatter.format(getReimburseTotal()) + "\n";
		preview += "Special Gifts: " + formatter.format(getGiftTotal()) + "\n\n";
		preview += "Currency: " + formatter.format(cash.getCashTotal()) + "\n";
		preview += "Checks: " + formatter.format(getCheckTotal()) + "\n\n";
		preview += "Total Income: " + formatter.format(getTotal()) + "\n\n";
		
		preview += "Reimbursements:\n";
		HashMap<String,Double> reimbursements = getSummarizedReimbursements();
		for(String name : reimbursements.keySet()) {
			preview += name +  " " + formatter.format(reimbursements.get(name))  + "\n";
		}
		
		preview += "\nSpecialGifts:\n";
		HashMap<String,Double> gifts = getSummarizedGifts();
		for(String name : gifts.keySet()) {
			preview += name +  " " + formatter.format(gifts.get(name))  + "\n";
		}
		
		preview += "\nCounted by: " + counter1 + "," + counter2;
		
		return preview;
		
	}

	public String getDepositInfoString() {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String depositInfo = "Deposit Info for Bank Slip:\n";
		
		depositInfo += "Coin: " + formatter.format(cash.getCoinTotal()) + "\n";
		depositInfo += "$1:  " + formatter.format(cash.getOnesTotal()) + "\n";
		depositInfo += "$2: " + formatter.format(cash.getTwosTotal()) + "\n";
		depositInfo += "$5: " + formatter.format(cash.getFivesTotal()) + "\n";
		depositInfo += "$10: " + formatter.format(cash.getTensTotal()) + "\n";
		depositInfo += "$20: " + formatter.format(cash.getTwentiesTotal()) + "\n";
		depositInfo += "$50: " + formatter.format(cash.getFiftiesTotal()) + "\n";
		depositInfo += "$100: " + formatter.format(cash.getHundredsTotal()) + "\n\n";
		depositInfo += "Subtotal Cash: " + formatter.format(cash.getCashTotal()) + "\n";
		depositInfo += "Total Checks: " + formatter.format(getCheckTotal()) + "\n\n";
		depositInfo += "Total Deposit: " + formatter.format(getTotal()) + "\n";
		return depositInfo;
	}
	
	ArrayList<Envelope> getEnvelopes(){
		return envelopes;
	}

}
