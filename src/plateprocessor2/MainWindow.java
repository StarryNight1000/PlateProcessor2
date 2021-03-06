package plateprocessor2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWindow extends Dialog {

	protected Object result;
	protected Shell shlPlateProcessor;
	private Text numPennies;
	private Text numNickels;
	private Text numDimes;
	private Text numQuarters;
	private Text numFiftyCents;
	private Text numDollarCoins;
	private Text numDollars;
	private Text numFiveDollars;
	private Text numTenDollars;
	private Text numTwentyDollars;
	private Text numTwoDollars;
	private Text numFiftyDollars;
	private Text numHundredDollars;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MainWindow(Shell parent, int style) {
		super(parent, style);
		setText("Plate Processor");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlPlateProcessor.open();
		shlPlateProcessor.layout();
		Display display = getParent().getDisplay();
		while (!shlPlateProcessor.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlPlateProcessor = new Shell(getParent(), getStyle());
		shlPlateProcessor.setSize(645, 569);
		shlPlateProcessor.setText("Plate Processor");
		shlPlateProcessor.setLayout(new StackLayout());
		
		TabFolder tabFolder = new TabFolder(shlPlateProcessor, SWT.NONE);
		
		TabItem tbtmEnterCash = new TabItem(tabFolder, SWT.NONE);
		tbtmEnterCash.setText("1. Enter Cash");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmEnterCash.setControl(composite);
		composite.setLayout(new GridLayout(2, false));
		new Label(composite, SWT.NONE);
		
		Label lblEnterTheNumber = new Label(composite, SWT.NONE);
		lblEnterTheNumber.setText("Enter the number of each (not cash value):");
		
		Label lblPennies = new Label(composite, SWT.NONE);
		lblPennies.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPennies.setText("Pennies");
		
		numPennies = new Text(composite, SWT.BORDER);
		numPennies.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNickels = new Label(composite, SWT.NONE);
		lblNickels.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNickels.setText("Nickels");
		
		numNickels = new Text(composite, SWT.BORDER);
		numNickels.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDimes = new Label(composite, SWT.NONE);
		lblDimes.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDimes.setText("Dimes");
		
		numDimes = new Text(composite, SWT.BORDER);
		numDimes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblQuarters = new Label(composite, SWT.NONE);
		lblQuarters.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQuarters.setText("Quarters");
		
		numQuarters = new Text(composite, SWT.BORDER);
		numQuarters.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblFiftyCentCoins = new Label(composite, SWT.NONE);
		lblFiftyCentCoins.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiftyCentCoins.setText("Fifty Cent Coins");
		
		numFiftyCents = new Text(composite, SWT.BORDER);
		numFiftyCents.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDollarCoins = new Label(composite, SWT.NONE);
		lblDollarCoins.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDollarCoins.setText("Dollar Coins");
		
		numDollarCoins = new Text(composite, SWT.BORDER);
		numDollarCoins.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblDollars = new Label(composite, SWT.NONE);
		lblDollars.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDollars.setText("Dollar Bills");
		
		numDollars = new Text(composite, SWT.BORDER);
		numDollars.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblTwoDollarBills = new Label(composite, SWT.NONE);
		lblTwoDollarBills.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTwoDollarBills.setText("Two Dollar Bills");
		
		numTwoDollars = new Text(composite, SWT.BORDER);
		numTwoDollars.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblFiveDollarBills = new Label(composite, SWT.NONE);
		lblFiveDollarBills.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiveDollarBills.setText("Five Dollar Bills");
		
		numFiveDollars = new Text(composite, SWT.BORDER);
		numFiveDollars.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblTenDollarBills = new Label(composite, SWT.NONE);
		lblTenDollarBills.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTenDollarBills.setText("Ten Dollar Bills");
		
		numTenDollars = new Text(composite, SWT.BORDER);
		numTenDollars.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblTwentyDollarBills = new Label(composite, SWT.NONE);
		lblTwentyDollarBills.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTwentyDollarBills.setText("Twenty Dollar Bills");
		
		numTwentyDollars = new Text(composite, SWT.BORDER);
		numTwentyDollars.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblFiftyDollarBills = new Label(composite, SWT.NONE);
		lblFiftyDollarBills.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiftyDollarBills.setText("Fifty Dollar Bills");
		
		numFiftyDollars = new Text(composite, SWT.BORDER);
		numFiftyDollars.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblHundredDollarBills = new Label(composite, SWT.NONE);
		lblHundredDollarBills.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHundredDollarBills.setText("Hundred Dollar Bills");
		
		numHundredDollars = new Text(composite, SWT.BORDER);
		numHundredDollars.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnSubmit = new Button(composite, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSubmit.setText("Submit");
		
		TabItem tbtmEnterChecks_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmEnterChecks_1.setText("2. Enter Checks");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmEnterChecks_1.setControl(composite_1);

	}
}
