package plateprocessor2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CashShell {
	
	private Shell shell;

    public CashShell(Cash cash)
    {
        shell = new Shell(Display.getCurrent());
        shell.setLayout(new GridLayout(1,false));
		shell.setText("Envelope Cash Entry");
		
		Composite composite = new Composite(shell, SWT.NONE);
	    composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    composite.setLayout(new GridLayout(1, true));
		//composite.setLayout(new FillLayout());
	    CashEntry cashEntry = new CashEntry(composite, cash, shell);
    }

    public void open()
    {
        shell.open();
    }

    public void close()
    {
          shell.close();
    }
    
    public Shell getShell() {
    	return shell;
    }

}
