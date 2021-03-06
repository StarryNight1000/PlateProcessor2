package plateprocessor2;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SaveEntry{
	public static Composite saveComposite;
	
	private Text firstCounter, secondCounter;
	private StyledText preview, depositInfo;
	private Label firstCounterLbl, secondCounterLbl;
	
	private Button finalizeBtn,saveBtn;
	private Composite outputComposite;
	
	private Plate plate = null;
	Shell shell = null;
	
	public SaveEntry(Composite parent, Shell shell, Plate plate) {
		
		this.shell = shell;
		this.plate = plate;
		
		saveComposite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		saveComposite.setLayout(gridLayout);
		saveComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
		
		Composite dataEntry = new Composite(saveComposite, SWT.NONE);
		dataEntry.setLayout(new RowLayout());
		
		
		firstCounterLbl = new Label(dataEntry, SWT.NONE);
		firstCounterLbl.setText("Initials of first counter:");
		firstCounterLbl.setFont(new Font(firstCounterLbl.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		
		firstCounter = new Text(dataEntry, SWT.BORDER);
		firstCounter.setBounds(0, 0, 100, 20);
		firstCounter.setFont(new Font(firstCounter.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		
		secondCounterLbl = new Label(dataEntry, SWT.NONE);
		secondCounterLbl.setText("Initials of second counter:");
		secondCounterLbl.setFont(new Font(secondCounterLbl.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		
		secondCounter = new Text(dataEntry, SWT.BORDER);
		secondCounter.setBounds(0, 0, 100, 20);
		secondCounter.setFont(new Font(secondCounter.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		
		finalizeBtn = new Button(saveComposite,SWT.NONE);
		finalizeBtn.setFont(new Font(finalizeBtn.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		finalizeBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				plate.setCounter1(firstCounter.getText());
				plate.setCounter2(secondCounter.getText());
				
				String previewText = plate.getPreviewText();
				preview.setText(previewText);
				
				
				String depositInfoTxt = plate.getDepositInfoString();
				depositInfo.setText(depositInfoTxt);
				
				preview.pack();
				preview.redraw();
				depositInfo.pack();
				depositInfo.redraw();
				    
				outputComposite.pack();
			}
		});

		finalizeBtn.setBounds(500, 10, 68, 23);
		finalizeBtn.setText("Add Counter Initials and Show Deposit Info");
		
		
		
		saveBtn = new Button(saveComposite,SWT.NONE);
		saveBtn.setFont(new Font(saveBtn.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		saveBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//save data to spreadsheet
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
		    	fd.setText("Save Output File");
		    	fd.setFilterPath("C:/");
		    	String[] filterExt = { "*.xlsx"};
		    	fd.setFilterExtensions(filterExt);
		    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		    	Date date = new Date();
		    	String dateString = dateFormatter.format(date);
		    	fd.setFileName("Total_Income_ " + dateString + ".xlsx");
		    	String selected = fd.open();
		    	
	
		    	
		    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
		    	XSSFWorkbook workbook = new XSSFWorkbook();

		    	XSSFSheet sheet = workbook.createSheet("Income Summary");
		    	
		    	XSSFRow firstRow = sheet.createRow(0);
		    	XSSFCell totalIncome = firstRow.createCell(0);
		    	totalIncome.setCellValue("Total Income");
		    	XSSFCell dateCell = firstRow.createCell(1);
		    	dateCell.setCellValue(dateString);
		    	
		    	XSSFRow genFundRow = sheet.createRow(2);
		    	XSSFCell genFund = genFundRow.createCell(0);
		    	genFund.setCellValue("General Fund");
		    	XSSFCell genFundAmount = genFundRow.createCell(1);
		    	genFundAmount.setCellValue(formatter.format(plate.getGeneralFundTotal()));
		    	
		    	XSSFRow reimbRow = sheet.createRow(3);
		    	XSSFCell reimburse = reimbRow.createCell(0);
		    	reimburse.setCellValue("Reimbursements");
		    	XSSFCell reimbAmount = reimbRow.createCell(1);
		    	reimbAmount.setCellValue(formatter.format(plate.getReimburseTotal()));
		    	
		    	XSSFRow giftRow = sheet.createRow(4);
		    	XSSFCell gift = giftRow.createCell(0);
		    	gift.setCellValue("Special Gifts");
		    	XSSFCell giftAmount = giftRow.createCell(1);
		    	giftAmount.setCellValue(formatter.format(plate.getGiftTotal()));
		    	
		    	XSSFRow currencyRow = sheet.createRow(6);
		    	XSSFCell currency = currencyRow.createCell(0);
		    	currency.setCellValue("Currency");
		    	XSSFCell currencyAmount = currencyRow.createCell(1);
		    	currencyAmount.setCellValue(formatter.format(plate.getCash().getCashTotal()));
		    	
		    	XSSFRow checksRow = sheet.createRow(7);
		    	XSSFCell checks = checksRow.createCell(0);
		    	checks.setCellValue("Checks");
		    	XSSFCell checksAmount = checksRow.createCell(1);
		    	checksAmount.setCellValue(formatter.format(plate.getCheckTotal()));
		    	
		    	XSSFRow totalRow = sheet.createRow(9);
		    	XSSFCell total = totalRow.createCell(0);
		    	total.setCellValue("Total Income");
		    	XSSFCell totalAmount = totalRow.createCell(1);
		    	totalAmount.setCellValue(formatter.format(plate.getTotal()));
		    	
		    	XSSFRow counterRow = sheet.createRow(11);
		    	XSSFCell counters = counterRow.createCell(0);
		    	counters.setCellValue("Counted By");
		    	XSSFCell counter1 = counterRow.createCell(1);
		    	counter1.setCellValue(plate.getCounter1());
		    	XSSFCell counter2 = counterRow.createCell(2);
		    	counter2.setCellValue(plate.getCounter2());
		    	
		    	XSSFCell reimburseCell = genFundRow.createCell(4);
		    	reimburseCell.setCellValue("Reimbursements");
		    	
		    	
		    	//Add reimbursements
		    	HashMap<String, Double> reimbursements = plate.getSummarizedReimbursements();
		    	int i = 1; 
		    	for(String name : reimbursements.keySet()) {
		    		XSSFRow curRow = sheet.getRow(i + 3);
		    		if( curRow == null){
		    			curRow = sheet.createRow(i + 3);
		    		}
		    		XSSFCell nameCell = curRow.createCell(4);
			    	nameCell.setCellValue(name);
			    	XSSFCell amtCell = curRow.createCell(5);
			    	amtCell.setCellValue(formatter.format(reimbursements.get(name)));
			    	i ++;
		    	}
		    	
		    	XSSFCell giftCell = genFundRow.createCell(7);
		    	giftCell.setCellValue("Special Gifts");
		    	
		    	//Add Special Gifts
		    	HashMap<String, Double> gifts = plate.getSummarizedGifts();
		    	i = 1; 
		    	for(String name : gifts.keySet()) {
		    		XSSFRow curRow = sheet.getRow(i + 3);
		    		if( curRow == null){
		    			curRow = sheet.createRow(i + 3);
		    		}
		    		XSSFCell nameCell = curRow.createCell(7);
			    	nameCell.setCellValue(name);
			    	XSSFCell amtCell = curRow.createCell(8);
			    	amtCell.setCellValue(formatter.format(gifts.get(name)));
			    	i ++;
		    	}
		    	
		    	
		    	//Check summary sheet
		    	XSSFSheet checkSheet = workbook.createSheet("Envelopes Recieved Summary");
		    	XSSFRow checkHeaderRow = checkSheet.createRow(0);
		    	XSSFCell envNumHeader = checkHeaderRow.createCell(0);
		    	envNumHeader.setCellValue("Envelope Number");
		    	
		    	XSSFCell amtHeader = checkHeaderRow.createCell(1);
		    	amtHeader.setCellValue("Check Amount");
		    	XSSFCell checkNumHeader = checkHeaderRow.createCell(2);
		    	checkNumHeader.setCellValue("Check Number");
		    	
		    	XSSFCell cashHeader = checkHeaderRow.createCell(3);
		    	cashHeader.setCellValue("Cash Amount");
		    	
		    	XSSFCell fundHeader = checkHeaderRow.createCell(4);
		    	fundHeader.setCellValue("Designated Funds");
		    	
		    	XSSFCell nameHeader = checkHeaderRow.createCell(5);
		    	nameHeader.setCellValue("Name");
		    	XSSFCell addressHeader = checkHeaderRow.createCell(6);
		    	addressHeader.setCellValue("Address");
		    	XSSFCell commentHeader = checkHeaderRow.createCell(7);
		    	commentHeader.setCellValue("Comment");
		    	
		    	i = 1;
		    	for(Envelope env : plate.getEnvelopes()) {
		    		
	    			XSSFRow checkRow = checkSheet.createRow(i);
		    		XSSFCell envNum = checkRow.createCell(0);
		    		envNum.setCellValue(env.getEnvelopeNumber());
		    		
		    		if(env.hasCheck) {
		    			Check check = env.getCheck();
			    		XSSFCell amt = checkRow.createCell(1);
				    	amt.setCellValue(formatter.format(check.getAmount()));
				    	XSSFCell checkNum= checkRow.createCell(2);
				    	checkNum.setCellValue(check.getCheckNumber());
		    		}
		    		
		    		XSSFCell cash= checkRow.createCell(3);
			    	cash.setCellValue(formatter.format(env.getCash().getCashTotal()));
			    	
			    	XSSFCell funds= checkRow.createCell(4);
			    	funds.setCellValue(EnvelopeTable.getDesignatedFundText(env.getDesignatedFunds()));
		    		
		    		XSSFCell name= checkRow.createCell(5);
			    	name.setCellValue(env.getName());
			    	XSSFCell address = checkRow.createCell(6);
			    	address.setCellValue(env.getAddress());
			    	XSSFCell comment = checkRow.createCell(7);
			    	comment.setCellValue(env.getComment());
		    		
		    		i ++;
		    	}
		    	
		    	XSSFSheet cashSheet = workbook.createSheet("Cash Recieved Summary");
		    	XSSFRow cashHeaderRow = cashSheet.createRow(0);
		    	XSSFCell denomHeader = cashHeaderRow.createCell(0);
		    	denomHeader.setCellValue("Denomination");
		    	XSSFCell numEachHeader = cashHeaderRow.createCell(1);
		    	numEachHeader.setCellValue("Count");
		    	XSSFCell cashAmtHeader = cashHeaderRow.createCell(2);
		    	cashAmtHeader.setCellValue("Amount");
		    	
		    	XSSFRow penniesRow = cashSheet.createRow(1);
		    	XSSFCell penniesDenom = penniesRow.createCell(0);
		    	penniesDenom.setCellValue("Pennies");
		    	XSSFCell penniesCount = penniesRow.createCell(1);
		    	penniesCount.setCellValue(plate.getCash().getNumPennies());
		    	XSSFCell penniesAmt = penniesRow.createCell(2);
		    	penniesAmt.setCellValue(formatter.format(plate.getCash().getPenniesTotal()));
		    	
		    	XSSFRow nickelsRow = cashSheet.createRow(2);
		    	XSSFCell nickelsDenom = nickelsRow.createCell(0);
		    	nickelsDenom.setCellValue("Nickels");
		    	XSSFCell nickelsCount = nickelsRow.createCell(1);
		    	nickelsCount.setCellValue(plate.getCash().getNumNickels());
		    	XSSFCell nickelsAmt = nickelsRow.createCell(2);
		    	nickelsAmt.setCellValue(formatter.format(plate.getCash().getNickelsTotal()));
		    	
		    	XSSFRow dimesRow = cashSheet.createRow(3);
		    	XSSFCell dimesDenom = dimesRow.createCell(0);
		    	dimesDenom.setCellValue("Dimes");
		    	XSSFCell dimesCount = dimesRow.createCell(1);
		    	dimesCount.setCellValue(plate.getCash().getNumDimes());
		    	XSSFCell dimesAmt = dimesRow.createCell(2);
		    	dimesAmt.setCellValue(formatter.format(plate.getCash().getDimesTotal()));
		    	
		    	XSSFRow quartersRow = cashSheet.createRow(4);
		    	XSSFCell quartersDenom = quartersRow.createCell(0);
		    	quartersDenom.setCellValue("Quarters");
		    	XSSFCell quartersCount = quartersRow.createCell(1);
		    	quartersCount.setCellValue(plate.getCash().getNumQuarters());
		    	XSSFCell quartersAmt = quartersRow.createCell(2);
		    	quartersAmt.setCellValue(formatter.format(plate.getCash().getQuartersTotal()));
		    	
		    	XSSFRow fiftyCentsRow = cashSheet.createRow(5);
		    	XSSFCell fiftyCentsDenom = fiftyCentsRow.createCell(0);
		    	fiftyCentsDenom.setCellValue("Fifty Cents");
		    	XSSFCell fiftyCentsCount = fiftyCentsRow.createCell(1);
		    	fiftyCentsCount.setCellValue(plate.getCash().getNumFiftyCents());
		    	XSSFCell fiftyCentsAmt = fiftyCentsRow.createCell(2);
		    	fiftyCentsAmt.setCellValue(formatter.format(plate.getCash().getFiftyCentTotal()));
		    	
		    	XSSFRow dollarCoinRow = cashSheet.createRow(5);
		    	XSSFCell dollarCoinDenom = dollarCoinRow.createCell(0);
		    	dollarCoinDenom.setCellValue("Dollar Coins");
		    	XSSFCell dollarCoinCount = dollarCoinRow.createCell(1);
		    	dollarCoinCount.setCellValue(plate.getCash().getNumDollarCoins());
		    	XSSFCell dollarCoinAmt = dollarCoinRow.createCell(2);
		    	dollarCoinAmt.setCellValue(formatter.format(plate.getCash().getDollarCoinTotal()));
		    	
		    	XSSFRow dollarRow = cashSheet.createRow(6);
		    	XSSFCell dollarDenom = dollarRow.createCell(0);
		    	dollarDenom.setCellValue("Dollars");
		    	XSSFCell dollarCount = dollarRow.createCell(1);
		    	dollarCount.setCellValue(plate.getCash().getNumOnes());
		    	XSSFCell dollarAmt = dollarRow.createCell(2);
		    	dollarAmt.setCellValue(formatter.format(plate.getCash().getOnesTotal()));
		    	
		    	XSSFRow twosRow = cashSheet.createRow(7);
		    	XSSFCell twosDenom = twosRow.createCell(0);
		    	twosDenom.setCellValue("Twos");
		    	XSSFCell twosCount = twosRow.createCell(1);
		    	twosCount.setCellValue(plate.getCash().getNumTwos());
		    	XSSFCell twosAmt = twosRow.createCell(2);
		    	twosAmt.setCellValue(formatter.format(plate.getCash().getTwosTotal()));
		    	
		    	XSSFRow fivesRow = cashSheet.createRow(8);
		    	XSSFCell fivesDenom = fivesRow.createCell(0);
		    	fivesDenom.setCellValue("Fives");
		    	XSSFCell fivesCount = fivesRow.createCell(1);
		    	fivesCount.setCellValue(plate.getCash().getNumFives());
		    	XSSFCell fivesAmt = fivesRow.createCell(2);
		    	fivesAmt.setCellValue(formatter.format(plate.getCash().getFivesTotal()));
		    	
		    	XSSFRow tensRow = cashSheet.createRow(9);
		    	XSSFCell tensDenom = tensRow.createCell(0);
		    	tensDenom.setCellValue("Tens");
		    	XSSFCell tensCount = tensRow.createCell(1);
		    	tensCount.setCellValue(plate.getCash().getNumTens());
		    	XSSFCell tensAmt = tensRow.createCell(2);
		    	tensAmt.setCellValue(formatter.format(plate.getCash().getTensTotal()));
		    	
		    	XSSFRow twentiesRow = cashSheet.createRow(10);
		    	XSSFCell twentiesDenom = twentiesRow.createCell(0);
		    	twentiesDenom.setCellValue("Twenties");
		    	XSSFCell twentiesCount = twentiesRow.createCell(1);
		    	twentiesCount.setCellValue(plate.getCash().getNumTwenties());
		    	XSSFCell twentiesAmt = twentiesRow.createCell(2);
		    	twentiesAmt.setCellValue(formatter.format(plate.getCash().getTwentiesTotal()));
		    	
		    	XSSFRow fiftiesRow = cashSheet.createRow(11);
		    	XSSFCell fiftiesDenom = fiftiesRow.createCell(0);
		    	fiftiesDenom.setCellValue("Fifties");
		    	XSSFCell fiftiesCount = fiftiesRow.createCell(1);
		    	fiftiesCount.setCellValue(plate.getCash().getNumFifties());
		    	XSSFCell fiftiesAmt = fiftiesRow.createCell(2);
		    	fiftiesAmt.setCellValue(formatter.format(plate.getCash().getFiftiesTotal()));
		    	
		    	XSSFRow hundredsRow = cashSheet.createRow(12);
		    	XSSFCell hundredsDenom = hundredsRow.createCell(0);
		    	hundredsDenom.setCellValue("Hundreds");
		    	XSSFCell hundredsCount = hundredsRow.createCell(1);
		    	hundredsCount.setCellValue(plate.getCash().getNumHundreds());
		    	XSSFCell hundredsAmt = hundredsRow.createCell(2);
		    	hundredsAmt.setCellValue(formatter.format(plate.getCash().getHundredsTotal()));
		    	
		    	FileOutputStream out;
				try {
					if(selected != null) {
						out = new FileOutputStream(new File(selected));
					workbook.write(out);
					workbook.close();
					out.close();
					}
					
				} catch (FileNotFoundException e1) {
					MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    	    		failedFormatting.setText("Error");
    	    		failedFormatting.setMessage("Could not Save!\n You may need to close the file if it is already open."); 
    	    		failedFormatting.open();

					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		    	   
				try {
					if(selected != null) {
						Desktop.getDesktop().open(new File(selected));
					}
					
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
		    	
		    
				
				             
			}
		});

		saveBtn.setBounds(500, 10, 68, 23);
		saveBtn.setText("Save Data to Spreadsheet");
		
		
		outputComposite = new Composite(saveComposite, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 2;
		outputComposite.setLayout(gridLayout2);
		
		preview = new StyledText(outputComposite, SWT.MULTI);
		preview.setFont(new Font(preview.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		
		depositInfo = new StyledText(outputComposite, SWT.MULTI);
		//FontDescriptor boldDescriptor = FontDescriptor.createFrom(depositInfo.getFont()).setStyle(SWT.BOLD);
		//Font boldFont = boldDescriptor.createFont(depositInfo.getDisplay());
		//depositInfo.setFont( boldFont );
		depositInfo.setFont(new Font(depositInfo.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.BOLD));
		
		
	}
	
	
}
