package plateprocessor2;

public class Cash {
	
	private int numOnes = 0;
	private int numTwos = 0;
	private int numFives = 0;
	private int numTens = 0;
	private int numTwenties = 0;
	private int numFifties = 0;
	private int numHundreds = 0;
	
	
	private int numPennies = 0;
	private int numNickels = 0;
	private int numDimes = 0;
	private int numQuarters = 0;
	private int numFiftyCents = 0;
	private int numDollarCoins = 0;
	
	public int getNumOnes() {
		return numOnes;
	}
	public void setNumOnes(int numOnes) {
		this.numOnes = numOnes;
	}
	public int getNumTwos() {
		return numTwos;
	}
	public void setNumTwos(int numTwos) {
		this.numTwos = numTwos;
	}
	public int getNumFives() {
		return numFives;
	}
	public void setNumFives(int numFives) {
		this.numFives = numFives;
	}
	public int getNumTens() {
		return numTens;
	}
	public void setNumTens(int numTens) {
		this.numTens = numTens;
	}
	public int getNumTwenties() {
		return numTwenties;
	}
	public void setNumTwenties(int numTwenties) {
		this.numTwenties = numTwenties;
	}
	public int getNumFifties() {
		return numFifties;
	}
	public void setNumFifties(int numFifties) {
		this.numFifties = numFifties;
	}
	public int getNumHundreds() {
		return numHundreds;
	}
	public void setNumHundreds(int numHundreds) {
		this.numHundreds = numHundreds;
	}
	public int getNumPennies() {
		return numPennies;
	}
	public void setNumPennies(int numPennies) {
		this.numPennies = numPennies;
	}
	public int getNumNickels() {
		return numNickels;
	}
	public void setNumNickels(int numNickels) {
		this.numNickels = numNickels;
	}
	public int getNumDimes() {
		return numDimes;
	}
	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}
	public int getNumQuarters() {
		return numQuarters;
	}
	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}
	public int getNumFiftyCents() {
		return numFiftyCents;
	}
	public void setNumFiftyCents(int numFiftyCents) {
		this.numFiftyCents = numFiftyCents;
	}
	public int getNumDollarCoins() {
		return numDollarCoins;
	}
	public void setNumDollarCoins(int numDollarCoins) {
		this.numDollarCoins = numDollarCoins;
	}
	
	public int getOnesTotal() {
		return numOnes;
	}
	
	public int getTwosTotal() {
		return 2 * numTwos;
	}
	
	public int getFivesTotal() {
		return 5 * numFives;
	}
	
	public int getTensTotal() {
		return 10 * numTens;
	}
	
	public int getTwentiesTotal() {
		return 20 * numTwenties;
	}
	
	public int getFiftiesTotal() {
		return 50 * numFifties;
	}
	
	public int getHundredsTotal() {
		return 100 * numHundreds;
	}
	
	public double getPenniesTotal() {
		return .01 * numPennies;
	}
	
	public double getNickelsTotal() {
		return .05 * numNickels;
	}
	
	public double getDimesTotal() {
		return .1 * numDimes;
	}
	
	public double getQuartersTotal() {
		return .25 * numQuarters;
	}
	
	public double getFiftyCentTotal() {
		return .5 * numFiftyCents;
	}
	
	public int getDollarCoinTotal() {
		return numDollarCoins;
	}
	
	public double getCoinTotal() {
		double total = 0;
		
		total += getPenniesTotal();
		total += getNickelsTotal();
		total += getDimesTotal();
		total += getQuartersTotal();
		total += getFiftyCentTotal();
		total += getDollarCoinTotal();
		
		return total;
	}
	
	
	public double getCashTotal() {
		double total = 0;
		
		total += getOnesTotal();
		total += getTwosTotal();
		total += getFivesTotal();
		total += getTensTotal();
		total += getTwentiesTotal();
		total += getFiftiesTotal();
		total += getHundredsTotal();
		
		total += getCoinTotal();
		
		return total;
	}

}
