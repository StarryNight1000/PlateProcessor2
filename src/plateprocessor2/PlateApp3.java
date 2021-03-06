package plateprocessor2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class PlateApp3 {

	private static Plate plate = new Plate();
	private static Shell shell;
	
	/**
	 * Launch the application. This is the main application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setLayout(new GridLayout(1,false));
		shell.setText("Plate Processor");
		
		
		shell.addListener(SWT.Close, new Listener()
	    {
	        public void handleEvent(Event event)
	        {
	            int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
	            MessageBox messageBox = new MessageBox(shell, style);
	            messageBox.setText("Exit?");
	            messageBox.setMessage("Do you really want to exit?\nBe sure to save before exiting!");
	            event.doit = messageBox.open() == SWT.YES;
	        }
	    });

	  
	    
	    Composite composite = new Composite(shell, SWT.NONE);
	    composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    composite.setLayout(new GridLayout(1, true));
	    
	    TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
	    tabFolder.setLayout(new GridLayout(1,true));
	    tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    tabFolder.setFont(new Font(display,"Arial", Constants.FONT_SIZE, SWT.NORMAL));

	    
	    TabItem envelopeTab = new TabItem(tabFolder, SWT.NONE);
	    envelopeTab.setText("1. Enter Envelopes");
	    
	    EnvelopeTable envelopeTable = new EnvelopeTable(tabFolder, plate, plate.getEnvelopes(), shell);
	    envelopeTab.setControl(envelopeTable.getComposite());
	    
	    
	    TabItem cashTab = new TabItem(tabFolder, SWT.NONE);
	    cashTab.setText("2. Enter Total Cash");
  
	    CashEntry cashEntry = new CashEntry(tabFolder, plate.getCash(), shell);
	    cashTab.setControl(cashEntry.getCashComposite());
	    
	    
	    TabItem saveTab = new TabItem(tabFolder, SWT.NONE);
	    saveTab.setText("3. Finalize");
	    
	    SaveEntry saveEntry = new SaveEntry(tabFolder,shell, plate);
	    saveTab.setControl(saveEntry.saveComposite);
	    
	    
	    TabItem helpTab = new TabItem(tabFolder, SWT.NONE);
	    helpTab.setText("Help");
	    HelpPage helpPage = new HelpPage(tabFolder);
	    helpTab.setControl(helpPage.helpComposite);
		
		//press enter will enter checks if on checks tab
		display.addFilter(SWT.KeyUp, new Listener()
	    {
	        @Override
	        public void handleEvent(Event e)
	        {
	        	//check if we are on the checks tab
	            if (e.widget instanceof Control && tabFolder.getSelectionIndex() == 0)
	            {
	                if(e.keyCode == SWT.CR)//press the enter button
	                {
	                    envelopeTable.addValue();
	                }
	            }
	        }
	    });
	    
	    shell.pack();
	    shell.setMaximized(true);
		shell.open();
		shell.layout();
		
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}
}
