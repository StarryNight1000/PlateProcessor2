package plateprocessor2;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import plateprocessor2.DesignatedFund.Type;

public class EnvelopeTable {
	
	public static Table envelopeTable;
    public static TableViewer envelopeTableViewer;
    public static Composite envelopeTableComposite;
    public static TableItem std_item;
    
    private Text envNum, checkAmt, checkNum, specialGiftAmt, name, address, comment;
    private Label envNumLbl, cashLbl, checkAmtLbl, checkNumLbl, giftLbl,specialGiftAmtLbl, 
    			nameLbl, addressLbl, commentLbl, alreadyEnteredLbl;
    
    private Button enterCash, specialGiftButton, reimburseButton, enterGift, delete;
    
    private Combo alreadyEntered;
    
    private ArrayList<Envelope> envelopes;
    private Plate plate;
    
    private Shell shell; 
    
    private Cash cash = new Cash();
    
    private ArrayList<DesignatedFund> designatedFundsForEnvelope = new ArrayList<DesignatedFund>();
    
    private int numDesGifts = 0;
    
    public EnvelopeTable(Composite parent, Plate plate, ArrayList<Envelope> envelopes, Shell shell){
    	this.envelopes = envelopes;
    	this.shell = shell;
    	this.plate = plate;
       createPartControl(parent);
    }
    
    public Composite getComposite() {
    	return envelopeTableComposite;
    }
    
    public void createPartControl(Composite parent) {
		
		envelopeTableComposite = new Composite(parent, SWT.NONE);
		envelopeTableComposite.setLayout(new GridLayout());
		
		Composite firstRowComposite = new Composite(envelopeTableComposite, SWT.BORDER);
		firstRowComposite.setLayout(new GridLayout(2, false));
		
		Composite dataEntry1Wrapper = new Composite(firstRowComposite, SWT.BORDER);
		dataEntry1Wrapper.setLayout(new GridLayout(1, false));
		Label instr1Lbl= new Label(dataEntry1Wrapper, SWT.NONE);
        instr1Lbl.setText("1. Enter Amount Given");
        instr1Lbl.setFont(new Font(instr1Lbl.getDisplay(),Constants.FONT_NAME, Constants.LARGER_FONT_SIZE, SWT.BOLD));
		Composite dataEntry1 = new Composite(dataEntry1Wrapper, SWT.NONE);
		dataEntry1.setLayout(new GridLayout(2, false));
		
		Composite giftCompositeWrapper = new Composite(firstRowComposite, SWT.BORDER);
		giftCompositeWrapper.setLayout(new GridLayout(1, false));
		Label instr2Lbl= new Label(giftCompositeWrapper, SWT.NONE);
        instr2Lbl.setText("2. Enter any Designated Funds");
        instr2Lbl.setFont(new Font(instr1Lbl.getDisplay(),Constants.FONT_NAME, Constants.LARGER_FONT_SIZE, SWT.BOLD));
		Composite giftComposite = new Composite(giftCompositeWrapper, SWT.NONE);
		giftComposite.setLayout(new GridLayout(2, false));
		
		
		Composite addlDataWrapper = new Composite(firstRowComposite, SWT.BORDER);
		addlDataWrapper.setLayout(new GridLayout(1, false));
		Label instr3Lbl= new Label(addlDataWrapper, SWT.NONE);
        instr3Lbl.setText("3. Enter Donor Info If Needed");
        instr3Lbl.setFont(new Font(instr1Lbl.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.BOLD));
		Composite additionalDataComposite = new Composite(addlDataWrapper, SWT.NONE);
		additionalDataComposite.setLayout(new GridLayout(2, false));
		
		Composite dataEntry2 = new Composite(envelopeTableComposite, SWT.BORDER);
		dataEntry2.setLayout(new GridLayout(3, false));
		
		GridData gridData2 = new GridData(GridData.FILL_HORIZONTAL);
        gridData2.widthHint = 200;
		
        
        
		envNumLbl = new Label(dataEntry1, SWT.NONE);
		envNumLbl.setText("Envelope Number:");
        envNum = new Text(dataEntry1, SWT.BORDER);
        envNum.setLayoutData(gridData2);

        checkAmtLbl = new Label(dataEntry1, SWT.NONE);
		checkAmtLbl.setText("Check Amount:");
        checkAmt = new Text(dataEntry1, SWT.BORDER);
        checkAmt.setLayoutData(gridData2);

        checkNumLbl = new Label(dataEntry1, SWT.NONE);
        checkNumLbl.setText("Check Number:");
        checkNum = new Text(dataEntry1, SWT.BORDER);
        checkNum.setLayoutData(gridData2);
        
        enterCash = new Button(dataEntry1, SWT.NONE);
        enterCash.setText("Enter Cash");
        enterCash.setFont(new Font(enterCash.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        
        cashLbl = new Label(dataEntry1, SWT.NONE);
        
        enterCash.addSelectionListener(new SelectionAdapter() {
        	
            @Override
            public void widgetSelected(SelectionEvent e) {

            	cash = new Cash();
            	CashShell cashShell = new CashShell(cash);
            	cashShell.open();
            	
            	cashShell.getShell().addListener(SWT.Close, new Listener() {
            	      public void handleEvent(Event event) {
            	    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
            	        cashLbl.setText("Cash Amount:" + formatter.format(cash.getCashTotal()));
            	        cashLbl.pack();
            	        dataEntry1.pack();
            	        firstRowComposite.pack();
            	        
            	      }
            	    });             
            }
        });
        
        
        specialGiftButton = new Button(giftComposite, SWT.RADIO);
        specialGiftButton.setText("Special Gift");
        specialGiftButton.setFont(new Font(specialGiftButton.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
       
        reimburseButton = new Button(giftComposite, SWT.RADIO);
        reimburseButton.setText("Reimbursement");
        reimburseButton.setFont(new Font(reimburseButton.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        
        
        alreadyEnteredLbl = new Label(giftComposite, SWT.NONE);
		alreadyEnteredLbl.setFont(new Font(alreadyEnteredLbl.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		alreadyEnteredLbl.setText("Fund name:");
		
		alreadyEntered = new Combo(giftComposite, SWT.DROP_DOWN);
		alreadyEntered.setFont(new Font(alreadyEntered.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		
		String [] items = {"Enter fund name"};
		alreadyEntered.setItems(items);
		alreadyEntered.select(0);
		alreadyEntered.redraw();
		alreadyEntered.pack();
        
        specialGiftAmtLbl = new Label(giftComposite, SWT.NONE);
        specialGiftAmtLbl.setText("Amount:");
        specialGiftAmt = new Text(giftComposite, SWT.BORDER);
        specialGiftAmt.setLayoutData(gridData2);
        
        enterGift = new Button(giftComposite, SWT.NONE);
        enterGift.setText("Enter Designated Fund");
        enterGift.setFont(new Font(enterCash.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        
        giftLbl = new Label(giftComposite, SWT.NONE);
        
        enterGift.addSelectionListener(new SelectionAdapter() {
        	
            @Override
            public void widgetSelected(SelectionEvent se) {

            	
            	try {
    	    		double giftAmount = Double.valueOf(specialGiftAmt.getText());
    	    	}catch (NumberFormatException e){
    	    		MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    	    		failedFormatting.setText("Error");
    	    		failedFormatting.setMessage("Amount input is not a number!"); 
    	    		failedFormatting.open();
    	    		return;
    	    	}
    			
    			if(!specialGiftButton.getSelection() && !reimburseButton.getSelection()) {
    				MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    	    		failedFormatting.setText("Error");
    	    		failedFormatting.setMessage("Please select a fund type!"); 
    	    		failedFormatting.open();
    	    		return;
    			}
    			
    			
    			if(alreadyEntered.getSelectionIndex() == 0 || alreadyEntered.getText().isBlank()) {
    				MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    	    		failedFormatting.setText("Error");
    	    		failedFormatting.setMessage("Please add a fund description!"); 
    	    		failedFormatting.open();
    	    		return;
    			}
    			
    	    	DesignatedFund fund = new DesignatedFund();
            	fund.setAmount(Double.valueOf(specialGiftAmt.getText().toString()));
            	fund.setName(alreadyEntered.getText());
            	
            	
            	if(specialGiftButton.getSelection()) {
            		fund.setType(DesignatedFund.Type.GIFT);
            	}
            	
            	if(reimburseButton.getSelection()) {
            		fund.setType(DesignatedFund.Type.REIMBURSEMENT);
            	}
            	
            	designatedFundsForEnvelope.add(fund);
            	
            	giftLbl.setText(getDesignatedFundText(designatedFundsForEnvelope));
            	giftLbl.pack();
            	giftComposite.pack();
            	firstRowComposite.pack();
                
                specialGiftAmt.setText("");
                
                specialGiftButton.setSelection(false);
                reimburseButton.setSelection(false);
    		
                boolean nameInList = false;
                for(String name : alreadyEntered.getItems()) {
                	if(name.equals(fund.getName())) {
                		nameInList = true;
                	}
                }
                
                if(!nameInList) {
                	numDesGifts ++;
                	String[] items = new String[numDesGifts + 1];
                	for(int i = 0; i < numDesGifts; i++){
                		items[i] = alreadyEntered.getItems()[i];
                	}
                	items[numDesGifts] = fund.getName();
                	alreadyEntered.setItems(items);
                	alreadyEntered.select(0);
                	alreadyEntered.redraw();
                	alreadyEntered.pack();
                }else {
                	alreadyEntered.select(0);
                }
            	
            }
        });
        
        Button clearFunds = new Button(giftComposite, SWT.NONE);
        clearFunds.setText("Clear Designated Funds");
        clearFunds.setFont(new Font(enterCash.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        
        clearFunds.addSelectionListener(new SelectionAdapter() {
        	
            @Override
            public void widgetSelected(SelectionEvent se) {
            	designatedFundsForEnvelope.clear();
            	
            	giftLbl.setText("");
            	giftLbl.pack();
            	giftComposite.pack();
            	firstRowComposite.pack();
            }
            
        });
        
        nameLbl = new Label(additionalDataComposite, SWT.NONE);
		nameLbl.setText("Donor Name:");
        name = new Text(additionalDataComposite, SWT.BORDER);
        name.setLayoutData(gridData2);
        
        addressLbl = new Label(additionalDataComposite, SWT.NONE);
		addressLbl.setText("Donor Address:");
        address = new Text(additionalDataComposite, SWT.BORDER);
        address.setLayoutData(gridData2);
        
        commentLbl = new Label(additionalDataComposite, SWT.NONE);
        commentLbl.setText("Comment:");
        comment = new Text(additionalDataComposite, SWT.BORDER);
        comment.setLayoutData(gridData2);
        
        Text [] texts1 = {envNum, checkAmt, checkNum, specialGiftAmt};
        for(Text text : texts1) {
        	text.setFont(new Font(text.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        }
	    Label [] labels1 = {envNumLbl, cashLbl, checkAmtLbl, checkNumLbl, specialGiftAmtLbl, giftLbl};
	    for(Label label : labels1) {
	    	label.setFont(new Font(label.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
	    }
        
	    //make the optional fields have a smaller font size
	    Text [] texts2 = {name,address,comment};
        for(Text text : texts2) {
        	text.setFont(new Font(text.getDisplay(),Constants.FONT_NAME, Constants.SMALLER_FONT_SIZE, SWT.NORMAL));
        }
	    Label [] labels2 = {nameLbl,addressLbl,commentLbl};
	    for(Label label : labels2) {
	    	label.setFont(new Font(label.getDisplay(),Constants.FONT_NAME, Constants.SMALLER_FONT_SIZE, SWT.NORMAL));
	    }
	    

        envelopeTableViewer = new TableViewer(envelopeTableComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL| SWT.MULTI);
        envelopeTable = envelopeTableViewer.getTable();
        envelopeTable.setFont(new Font(envelopeTable.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));

      
        envelopeTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        envelopeTable.setHeaderVisible(true);
        envelopeTable.setLinesVisible(true);

        //ADD Button
        Button btnAdd = new Button(firstRowComposite, SWT.NONE);
        btnAdd.setFont(new Font(btnAdd.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        btnAdd.addSelectionListener(new SelectionAdapter() {
        	
            @Override
            public void widgetSelected(SelectionEvent e) {

            	addValue(); 
            	
            }
        });
        
        btnAdd.setBounds(500, 10, 68, 23);
        btnAdd.setText("4. Add Envelope Data");
        btnAdd.setFont(new Font(btnAdd.getDisplay(),Constants.FONT_NAME, Constants.LARGER_FONT_SIZE, SWT.BOLD));
        

       
      
        
      //print checks Button
        Button btnPrint = new Button(dataEntry2, SWT.NONE);
        btnPrint.setFont(new Font(btnPrint.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        btnPrint.addSelectionListener(new SelectionAdapter() {
        	      public void widgetSelected(SelectionEvent e) {
        	       print();
        	      }
        });
        
        btnPrint.setBounds(500, 10, 68, 23);
        btnPrint.setText("Save Check Amounts to Print for Reciept for Bank");
        
        
        //delete Button
        Button btnDelete = new Button(dataEntry2, SWT.NONE);
        btnDelete.setFont(new Font(btnDelete.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
        btnDelete.addSelectionListener(new SelectionAdapter() {
        	
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
            	int selectedIndex = envelopeTable.getSelectionIndex();
            	
            	
            	//do nothing if nothing selected
            	if(selectedIndex < 0) {
            		return;
            	}
            	
            	envelopeTable.remove(selectedIndex);
            	envelopes.remove(selectedIndex); 
            	
            	checkNum.setText("");
                checkAmt.setText("");
                envNum.setText("");
                specialGiftAmt.setText("");
                name.setText("");
                address.setText("");
                comment.setText("");
                
                cashLbl.setText("");
            }
        });
        
        btnDelete.setBounds(500, 10, 68, 23);
        btnDelete.setText("Delete Selected Row");
        
        
       //Dynamic add column Name using TableViewerColumn 
        //blank first column for alignment -- can't change alignment on first column
        String[] Col_names={"","Envelope Number","Check Number", "Check Amount", "Cash Amount", "Designated Funds", "Name", "Address", "Comment"};
        for(int i=0; i<Col_names.length; i++)
        {
        	TableViewerColumn tableViewerColumn = new TableViewerColumn(envelopeTableViewer, SWT.NONE);
        	TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
        	
        	tblclmnNewColumn.setAlignment(SWT.RIGHT);
        	
        	if(i == 0) {
        		tblclmnNewColumn.setWidth(0);
        	}else {
        		tblclmnNewColumn.setWidth(200);
        	}
        	
        	tblclmnNewColumn.setResizable(true);
        	tblclmnNewColumn.setText(Col_names[i]);
        	tblclmnNewColumn.pack();
        }


        //numbers shifted by 1 to account for blank column
        for(Envelope e : envelopes)
        {
        	TableItem std_item=new TableItem(envelopeTable, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        	std_item.setText(1,e.getEnvelopeNumber());
        	NumberFormat formatter = NumberFormat.getCurrencyInstance();
        	std_item.setText(2,e.getCheck().getCheckNumber());//formatter.format(c.getAmount())
        	std_item.setText(3,formatter.format(e.getCheck().getAmount()));
        	std_item.setText(4,formatter.format(e.getCash().getCashTotal()));
        	if(e.hasDesignatedFund()) {
        		std_item.setText(5, getDesignatedFundText(e.getDesignatedFunds()));
        	}
        	std_item.setText(6, e.getName());
        	std_item.setText(7,e.getAddress());
        	std_item.setText(8, e.getComment());
        	
        }

        envelopeTableComposite.pack();
    }
    
    public static String getDesignatedFundText(ArrayList<DesignatedFund> funds) {
    	
		String result = "";
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		for(DesignatedFund fund : funds) {
			
			String type = fund.getType() == DesignatedFund.Type.GIFT? "Special Gift:" : "Reimbursement:";
			
			result += type + " " + fund.getName() + " " + formatter.format(fund.getAmount()) + "\n";
		}
				
		return result;
	}

	public void addValue() {
    	
    	if(checkAmt.getText().isEmpty() && cashLbl.getText().isEmpty()) {
    		MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    		failedFormatting.setText("Error");
    		failedFormatting.setMessage("Please enter either cash or check amount."); 
    		failedFormatting.open();
    		return;
    	}
    	
    	if((!checkAmt.getText().isEmpty() && checkNum.getText().isEmpty()) || (checkAmt.getText().isEmpty() && !checkNum.getText().isEmpty())) {
    		MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    		failedFormatting.setText("Error");
    		failedFormatting.setMessage("Need both check amount and check number to enter check data."); 
    		failedFormatting.open();
    		return;
    	}
    	
    	if(!checkAmt.getText().isEmpty()) {
    		try {
        		double checkAmount = Double.valueOf(checkAmt.getText());
        	}catch (NumberFormatException e){
        		MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
        		failedFormatting.setText("Error");
        		failedFormatting.setMessage("Check amount input is not a number!"); 
        		failedFormatting.open();
        		return;
        	}
    	}
    	
    	
    	//need either envelope number or name for donor
    	if(envNum.getText().isBlank() && name.getText().isBlank()) {
    		MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    		failedFormatting.setText("Error");
    		failedFormatting.setMessage("Please enter either a envelope number or donor information."); 
    		failedFormatting.open();
    		return;
    	}
    	
    	
    	//TODO if envelope has cash only, what to do here?
    	if(checkNum.getText().isBlank() && cash.getCashTotal() == 0) {
    		MessageBox failedFormatting = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    		failedFormatting.setText("Error");
    		failedFormatting.setMessage("Please enter a check number or cash amount!"); 
    		failedFormatting.open();
    		return;
    	}
    	
    	Envelope envelope = new Envelope();
    	
    	if(!checkAmt.getText().isEmpty()) {
    		Check newCheck = new Check();
        	newCheck.setCheckNumber(checkNum.getText());
        	newCheck.setAmount(Double.valueOf(checkAmt.getText().toString()));
        	newCheck.setEnvelopeNumber(envNum.getText());
        	
        	
        	envelope.setCheck(newCheck);
        	
    	}
    	
    	envelope.setEnvelopeNumber(envNum.getText());
    	
    	if (cash.getCashTotal() != 0){
    		envelope.setCash(cash);
    	}
    	
    	
    	for(DesignatedFund fund : designatedFundsForEnvelope) {
    		envelope.addDesignatedFund(fund);
    	}
    	
    	designatedFundsForEnvelope.clear();
    	
    	giftLbl.setText("");
    	giftLbl.pack();
    	
    	envelope.setName(name.getText());
    	envelope.setAddress(address.getText());
    	envelope.setComment(comment.getText());
    	
    	envelopes.add(envelope);
    	
    	
    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
    	
    	TableItem std_item=new TableItem(envelopeTable, SWT.WRAP);
    	
        std_item.setText(1,envNum.getText());
        std_item.setText(2, checkNum.getText());
        if(!checkAmt.getText().isEmpty()) {
        	std_item.setText(3,formatter.format(Double.valueOf(checkAmt.getText())));
        }
        if(!cashLbl.getText().isEmpty()) {
        	 std_item.setText(4,formatter.format(Double.valueOf(cash.getCashTotal())));
        }
        if(!envelope.getDesignatedFunds().isEmpty()) {
        	std_item.setText(5, getDesignatedFundText(envelope.getDesignatedFunds()));
        }
        std_item.setText(6,name.getText());
        std_item.setText(7, address.getText());
        std_item.setText(8, comment.getText());
		
        envNum.setText("");
        checkNum.setText("");
        checkAmt.setText("");
        cashLbl.setText("");
        cash = new Cash();
        specialGiftAmt.setText("");
        name.setText("");
        address.setText("");
        comment.setText("");
        
        specialGiftButton.setSelection(false);
    	reimburseButton.setSelection(false);
        
        //return focus to the first text box
        envNum.setFocus();
        
	}
    
    private void print() {
    	
    	if(envelopes.isEmpty()) {
    		MessageBox failedNoChecks = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
    		failedNoChecks.setText("Error");
    		failedNoChecks.setMessage("No envelopes have been input!"); 
    		failedNoChecks.open();
    		return;
    	}
    	
    	FileDialog fd = new FileDialog(shell, SWT.SAVE);
    	fd.setText("Save Check Receipt File");
    	fd.setFilterPath("C:/");
    	String[] filterExt = { "*.docx"};
    	fd.setFilterExtensions(filterExt);
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	String dateString = formatter.format(date);
    	fd.setFileName("Checks_Receipt_" + dateString + ".docx");
    	String selected = fd.open();
    	
    	XWPFDocument wordDoc = new XWPFDocument(); 

    	// create a paragraph in docx2
    	XWPFParagraph paragraph1 = wordDoc.createParagraph();
    	XWPFRun run = paragraph1.createRun();
    	run.setText("Checks Received " + dateString + ":");
    	
    	
    	//create table
        XWPFTable table = wordDoc.createTable();
        table.setCellMargins(0, 500, 0, 500);
        
        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("Check Number");
        tableRowOne.addNewTableCell().setText("Amount");
    	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		
    	//adds checks to table
    	for(Envelope env : envelopes) {
    		if(env.getCheck() != null) {
    			XWPFTableRow tableRow = table.createRow();
    	        tableRow.getCell(0).setText(env.getCheck().getCheckNumber());
    	        tableRow.getCell(1).setText(currencyFormatter.format(env.getCheck().getAmount()));
    		}	
    	}
		
    	XWPFParagraph paragraph3 = wordDoc.createParagraph();
    	XWPFRun totalRun = paragraph3.createRun();
		totalRun.setText("Total:");
		totalRun.addCarriageReturn();
		
		XWPFRun totalValueRun = paragraph3.createRun();
		totalValueRun.setText(currencyFormatter.format(plate.getCheckTotal()));
		totalValueRun.addCarriageReturn();

    	FileOutputStream out;
		try {
			if(selected != null) {
				out = new FileOutputStream(new File(selected));
				wordDoc.write(out);
				wordDoc.close();
				out.close();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
		try {
			if(selected != null) {
				Desktop.getDesktop().open(new File(selected));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
