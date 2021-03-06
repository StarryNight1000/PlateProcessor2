package plateprocessor2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class HelpPage{
	public static Composite helpComposite;
	
	String instructionsString = "1. Enter Envelopes:\n"
			+ "1. Enter Amount Given:\n"
			+ "Enter envelope number (if present), and any cash or check information."
			+ "If an envelope contains cash, click \"Enter Cash\", enter values in the dialog box, click \"Add\", and close the dialog to return to the main page.\n\n"
			+ "2. Enter Any Designated Funds:\n"
			+ "If an envelope is marked with a designated fund, select \"Special Gift\" or \"Reimbursement\". "
			+ "Then enter the name of the designated fund (e.g., AWANA or special missionary gift) and the amount. Click \"Enter Designated Fund\" to add the fund."
			+ " If you have multiple gifts for the same designated fund, be sure to select previously entered fund names from the drop-down menu.\n\n"
			+ "3. Enter Donor Info if Needed:"
			+ "If a check is not in an envelope, please include the donor name and address, and an optional comment.\n\n"
			+ "4. Add Envelope Data:\n"
			+ "Click \"Add Envelope Data\" to add the entered data into the form. Alternativley, you can press the enter button on the keyboard.\n"
			
			+ "If envelope information has been entered in error, select it in the table and click \"Delete Selected Row\"\n"
			+ "When finished entering checks, click on \"Save Check Amounts to Print Receipt for Bank\"."
			+ "This will bring up a dialog to save off a word file which will contain the check information for the bank.\n"
			+ "Save the file into the shared directory, and then print it and include the print out in the bank bag.\n"
			+ "With the assistance of another counter, verify the checks and compare the stack of checks to the printout.\n"
			+ "If the check print out is in error, simply delete the check in error, add another check, and re-print the receipt using the directions above.\n\n\n\n"
			
			+ "2. Enter Cash:\n"
			+ "Enter number of each type of currency in the appropriate box. Be sure to enter all cash recieved, including cash from envelope giving."
			+ "IMPORTANT: Do not put the cash value in the boxes, as the application will compute the cash value automatically based on the number of each entered."
			+ "If cash is entered in error, simply re-enter the values in the appropriate boxes and press \"Add\" again, and the cash values will update."
			+ "Be sure to double-check the cash counts by having the other counter verify the counts.\n\n\n\n"
			
			+ "3. Finalize:\n"
			+ "Enter the initials of the two counters. Then press \"Add counter initials and show deposit info\". "
			+ "Use the data that appears to populate the bank slip with cash and check totals. "
			+ "Finally, click \"Save data to Spreadsheet\" to save a copy of the data to an Excel file in the shared directory. "
			+ "This step is important because once you close the application, the data will be lost unless you complete this step. "
			+ "When finished counting, insert the following into the bank bag: check printout, checks, cash, completed bank slip. "
			+ "Seal the bank bag and deposit at the bank.";
	
	
	
	public HelpPage(Composite parent) {
		helpComposite = new Composite(parent, SWT.NONE);
		helpComposite.setLayout(new FillLayout(SWT.VERTICAL));
		StyledText instructions = new StyledText(helpComposite, SWT.MULTI |SWT.V_SCROLL);
		instructions.setText(instructionsString);
		instructions.setWordWrap(true);
		instructions.setFont(new Font(instructions.getDisplay(),Constants.FONT_NAME, Constants.FONT_SIZE, SWT.NORMAL));
		helpComposite.pack();
	}
}
