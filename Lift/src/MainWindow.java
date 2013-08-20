import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JToggleButton.ToggleButtonModel;

public class MainWindow extends JFrame {
	
	private LiftView liftView;
	
	private JMenuBar menu;
	private JMenu files;
	private JMenuItem exit;
	private JToolBar toolBar;
	
	private ArrayList<ToggleButtonModel> models;
	
	public MainWindow() {
		
		this.liftView = new LiftView();
		this.liftView.setController(new LiftController(this.liftView));
		this.add(this.liftView, BorderLayout.CENTER);
		
		this.createMenuBar();
		this.createButtons();
		this.createToolBar();
		
		this.pack();
	}

	private void createButtons() {
		this.models = new ArrayList<ToggleButtonModel>();
		for(int i = 0 ; i < 3 ; i++) {
			final int j = i;
			this.models.add(new ToggleButtonModel());
			this.models.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(models.get(j).isSelected()) liftView.getController().addFloor(j);
				}
				
			});
			JToggleButton b = new JToggleButton();
			b.setModel(this.models.get(i));
			b.setBounds(200, 50*i, 50, 30);
			this.liftView.add(b);
		}
		
	}

	private void createToolBar() {
		this.toolBar = new JToolBar(JToolBar.HORIZONTAL);
		for(int i = 0 ; i < 3 ; i++) {
			JToggleButton b = new JToggleButton();
			b.setModel(this.models.get(i));
			this.toolBar.add(b);
		}
		this.add(this.toolBar, BorderLayout.PAGE_START);
	}

	private void createMenuBar() {
		
		this.exit = new JMenuItem("Quitter");
		this.exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});	
		
		this.menu = new JMenuBar();
		this.files = new JMenu("Fichiers");
		this.files.add(this.exit);
		this.menu.add(this.files);
		this.setJMenuBar(this.menu);
	}

}
