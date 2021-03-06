package plateprocessor2;

import java.text.NumberFormat;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class CashEntry {
	private Composite cashComposite;
	private Text pennies,nickels,dimes,quarters,fiftyCents,dollarCoins,dollars,twos,fives,tens,twenties,fifties,hundreds;
    private Label penniesLbl,nickelsLbl,dimesLbl,quartersLbl,fiftyCentsLbl,dollarCoinsLbl,dollarsLbl,twosLbl,fivesLbl,tensLbl,twentiesLbl,fiftiesLbl,hundredsLbl;
	private Table enteredAmounts;
	
	ScrolledComposite scrollComposite = null;
	
	private boolean firstCashPopulate = true;
	
	private Cash cash;
	private Shell shell;
	
	public CashEntry(Composite parent, Cash cash, Shell shell) {
		this.cash = cash;
		this.shell = shell;
		create(parent);
	}

	public void create(Composite parent) {
		cashComposite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		
		cashComposite.setLayout(gridLayout);

		
		Label instrLbl = new Label(cashComposite, SWT.NONE);
		instrLbl.setFont(new Font(instrLbl.getDisplay(), Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		instrLbl.setText("Enter number of each below. (Not cash value)");

		
		Composite dataEntry = new Composite(cashComposite, SWT.NONE);
		GridLayout gridLayoutDE = new GridLayout();
		gridLayoutDE.numColumns = 3;
		dataEntry.setLayout(gridLayoutDE);
		
		
		Composite coins = new Composite(dataEntry, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 2;
		coins.setLayout(gridLayout2);
		coins.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));;
		
		penniesLbl = new Label(coins, SWT.NONE);
		penniesLbl.setText("Pennies:");
        pennies = new Text(coins, SWT.BORDER);

        nickelsLbl = new Label(coins, SWT.NONE);
		nickelsLbl.setText("Nickels:");
        nickels = new Text(coins, SWT.BORDER);

        dimesLbl = new Label(coins, SWT.NONE);
		dimesLbl.setText("Dimes:");
        dimes = new Text(coins, SWT.BORDER);
        
        quartersLbl = new Label(coins, SWT.NONE);
        quartersLbl.setText("Quarters:");
        quarters = new Text(coins, SWT.BORDER);
        
        fiftyCentsLbl = new Label(coins, SWT.NONE);
        fiftyCentsLbl.setText("Fifty Cents:");
        fiftyCents = new Text(coins, SWT.BORDER);
        
        dollarCoinsLbl = new Label(coins, SWT.NONE);
        dollarCoinsLbl.setText("Dollar Coins:");
        dollarCoins = new Text(coins, SWT.BORDER);
        
        
        Composite bills = new Composite(dataEntry, SWT.NONE);
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 2;
		bills.setLayout(gridLayout3);
		bills.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));;
		
		dollarsLbl = new Label(bills, SWT.NONE);
		dollarsLbl.setText("Dollars:");
        dollars = new Text(bills, SWT.BORDER);

        twosLbl = new Label(bills, SWT.NONE);
        twosLbl.setText("Twos:");
        twos = new Text(bills, SWT.BORDER);

        fivesLbl= new Label(bills, SWT.NONE);
        fivesLbl.setText("Fives:");
        fives = new Text(bills, SWT.BORDER);
        
        tensLbl = new Label(bills, SWT.NONE);
        tensLbl.setText("Tens:");
        tens = new Text(bills, SWT.BORDER);
        
        twentiesLbl = new Label(bills, SWT.NONE);
        twentiesLbl.setText("Twenties:");
        twenties = new Text(bills, SWT.BORDER);
        
        fiftiesLbl = new Label(bills, SWT.NONE);
        fiftiesLbl.setText("Fifties:");
        fifties = new Text(bills, SWT.BORDER);
        
        hundredsLbl = new Label(bills, SWT.NONE);
        hundredsLbl.setText("Hundreds:");
        hundreds = new Text(bills, SWT.BORDER);
        
        Label [] labels = {penniesLbl,nickelsLbl,dimesLbl,quartersLbl,fiftyCentsLbl,dollarCoinsLbl,dollarsLbl,twosLbl,fivesLbl,tensLbl,twentiesLbl,fiftiesLbl,hundredsLbl};
        for(Label label : labels) {
        	label.setFont(new Font(label.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        }
        
        Text [] texts = {pennies,nickels,dimes,quarters,fiftyCents,dollarCoins,dollars,twos,fives,tens,twenties,fifties,hundreds};
        for(Text text :texts) {
        	text.setFont(new Font(text.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        }
        
        
        Composite addButtonComposite = new Composite(dataEntry, SWT.NONE);
		addButtonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true));
        Button btnAdd = new Button(addButtonComposite, SWT.NONE);
        btnAdd.setFont(new Font(btnAdd.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        btnAdd.addSelectionListener(new SelectionAdapter() {
        	
            @Override
            public void widgetSelected(SelectionEvent e) {
            	

            	try {
            		if(pennies.getText().isEmpty()) {
            			cash.setNumPennies(0);
            		}else {
            			cash.setNumPennies(Integer.valueOf(pennies.getText()));
            		}
            		
            		if(nickels.getText().isEmpty()) {
            			cash.setNumNickels(0);
            		}else {
            			cash.setNumNickels(Integer.valueOf(nickels.getText()));
            		}
            		
            		if(dimes.getText().isEmpty()) {
            			cash.setNumDimes(0);
            		}else {
            			cash.setNumDimes(Integer.valueOf(dimes.getText()));
            		}
            		
            		if(quarters.getText().isEmpty()) {
            			cash.setNumQuarters(0);
            		}else {
            			cash.setNumQuarters(Integer.valueOf(quarters.getText()));
            		}
            		
            		if(fiftyCents.getText().isEmpty()) {
            			cash.setNumFiftyCents(0);
            		}else {
            			cash.setNumFiftyCents(Integer.valueOf(fiftyCents.getText()));
            		}
            		
            		if(dollarCoins.getText().isEmpty()) {
            			cash.setNumDollarCoins(0);
            		}else {
            			cash.setNumDollarCoins(Integer.valueOf(dollarCoins.getText()));
            		}
            		
            		if(dollars.getText().isEmpty()) {
            			cash.setNumOnes(0);
            		}else {
            			cash.setNumOnes(Integer.valueOf(dollars.getText()));
            		}
            		
            		if(twos.getText().isEmpty()) {
            			cash.setNumTwos(0);
            		}else {
            			cash.setNumTwos(Integer.valueOf(twos.getText()));
            		}
            		
            		if(fives.getText().isEmpty()) {
            			cash.setNumFives(0);
            		}else {
            			cash.setNumFives(Integer.valueOf(fives.getText()));
            		}
            		
            		if(tens.getText().isEmpty()) {
            			cash.setNumTens(0);
            		}else {
            			cash.setNumTens(Integer.valueOf(tens.getText()));
            		}

            		if(twenties.getText().isEmpty()) {
            			cash.setNumTwenties(0);
            		}else {
            			cash.setNumTwenties(Integer.valueOf(twenties.getText()));
            		}
            		
            		if(fifties.getText().isEmpty()) {
            			cash.setNumFifties(0);
            		}else {
            			cash.setNumFifties(Integer.valueOf(fifties.getText()));
            		}
            		
            		if(hundreds.getText().isEmpty()) {
            			cash.setNumHundreds(0);
            		}else {
            			cash.setNumHundreds(Integer.valueOf(hundreds.getText()));
            		}
            	}catch(NumberFormatException exception){
    	    		MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    	    		failedFormatting.setText("Error");
    	    		failedFormatting.setMessage("Value input is not a number!"); 
    	    		failedFormatting.open();
    	    		return;
    	    	}
            	
            	populateEnteredAmounts();
            }
        });
        
        btnAdd.setBounds(SWT.DEFAULT, SWT.DEFAULT, 100, 50);
        btnAdd.setText("Add");
        dataEntry.pack();
        
        TableViewer cashTableViewer = new TableViewer(cashComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL| SWT.MULTI);
     
        
        //enteredAmounts = new Table(cashComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);
        enteredAmounts = cashTableViewer.getTable();
        enteredAmounts.setFont(new Font(enteredAmounts.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        
        enteredAmounts.setHeaderVisible(true);
        enteredAmounts.setLinesVisible(true);
        GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd_table.heightHint = 500;
        enteredAmounts.setLayoutData(gd_table);

	}

	protected void populateEnteredAmounts() {
		enteredAmounts.removeAll();
		if(firstCashPopulate) {
			TableColumn column1 = new TableColumn(enteredAmounts, SWT.LEFT);
			TableColumn column2 = new TableColumn(enteredAmounts, SWT.RIGHT);
			TableColumn column3 = new TableColumn(enteredAmounts, SWT.RIGHT);
			column1.setText("Denomination");
			column2.setText("Number of Each");
			column3.setText("CashValue");
			
			column1.setWidth(30);
			column2.setWidth(10);
			column1.setWidth(20);

			enteredAmounts.setHeaderVisible(true);
			
			firstCashPopulate = false;
		}
		

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		TableItem pennyItem = new TableItem(enteredAmounts, SWT.NONE);
		pennyItem.setText(new String[] {"Pennies", String.valueOf(cash.getNumPennies()), formatter.format(cash.getPenniesTotal())});
		
		TableItem nickelItem = new TableItem(enteredAmounts, SWT.NONE);
		nickelItem.setText(new String[] {"Nickels", String.valueOf(cash.getNumNickels()), formatter.format(cash.getNickelsTotal())});
		
		TableItem dimeItem = new TableItem(enteredAmounts, SWT.NONE);
		dimeItem.setText(new String[] {"Dimes", String.valueOf(cash.getNumDimes()), formatter.format(cash.getDimesTotal())});
		
		TableItem quarterItem = new TableItem(enteredAmounts, SWT.NONE);
		quarterItem.setText(new String[] {"Quarters", String.valueOf(cash.getNumQuarters()), formatter.format(cash.getQuartersTotal())});
		
		TableItem fiftyCentItem = new TableItem(enteredAmounts, SWT.NONE);
		fiftyCentItem.setText(new String[] {"Fifty Cent Coins", String.valueOf(cash.getNumFiftyCents()), formatter.format(cash.getFiftyCentTotal())});
		
		TableItem dollarCoinItem = new TableItem(enteredAmounts, SWT.NONE);
		dollarCoinItem.setText(new String[] {"Dollar Coins", String.valueOf(cash.getNumDollarCoins()), formatter.format(cash.getDollarCoinTotal())});
		
		TableItem dollarItem = new TableItem(enteredAmounts, SWT.NONE);
		dollarItem.setText(new String[] {"Dollars", String.valueOf(cash.getNumOnes()), formatter.format(cash.getOnesTotal())});
		
		TableItem twoItem = new TableItem(enteredAmounts, SWT.NONE);
		twoItem.setText(new String[] {"Twos", String.valueOf(cash.getNumTwos()), formatter.format(cash.getTwosTotal())});
		
		TableItem fivesItem = new TableItem(enteredAmounts, SWT.NONE);
		fivesItem.setText(new String[] {"Fives", String.valueOf(cash.getNumFives()), formatter.format(cash.getFivesTotal())});
		
		TableItem tensItem = new TableItem(enteredAmounts, SWT.NONE);
		tensItem.setText(new String[] {"Tens", String.valueOf(cash.getNumTens()), formatter.format(cash.getTensTotal())});
		
		TableItem twentiesItem = new TableItem(enteredAmounts, SWT.NONE);
		twentiesItem.setText(new String[] {"Twenties", String.valueOf(cash.getNumTwenties()), formatter.format(cash.getTwentiesTotal())});
		
		TableItem fiftiesItem = new TableItem(enteredAmounts, SWT.NONE);
		fiftiesItem.setText(new String[] {"Fifties", String.valueOf(cash.getNumFifties()), formatter.format(cash.getFiftiesTotal())});
		
		TableItem hundredsItem = new TableItem(enteredAmounts, SWT.NONE);
		hundredsItem.setText(new String[] {"Hundreds", String.valueOf(cash.getNumHundreds()), formatter.format(cash.getHundredsTotal())});
		
		enteredAmounts.update();
		
		enteredAmounts.getColumn(0).pack();
		enteredAmounts.getColumn(1).pack();
		enteredAmounts.getColumn(2).pack();
		
		enteredAmounts.pack();
		enteredAmounts.setVisible(true);
		//cashComposite.pack();
	}

	public Control getCashComposite() {
		return cashComposite;
	}
}
