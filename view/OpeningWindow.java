package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.LatexEditorController;
import controller.commands.CommandSuperclass;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class OpeningWindow {

	private JFrame frame;
	private VersionsStrategy versionsStrategy;
	private VersionsManager versionsManager;
	private LatexEditorController controller ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningWindow window = new OpeningWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpeningWindow() {
		versionsStrategy = new VolatileVersionsStrategy();
		versionsManager = new VersionsManager(versionsStrategy);
		controller = new LatexEditorController(versionsManager);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCreateNewDocument = new JButton("Create New Document");
		btnCreateNewDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseTemplate chooseTemplate = new ChooseTemplate("opening", versionsManager, controller);
				frame.dispose();
			}
		});
		btnCreateNewDocument.setBounds(89, 26, 278, 36);
		frame.getContentPane().add(btnCreateNewDocument);
		
		JButton btnOpenExistingDocument = new JButton("Open Existing Document");
		btnOpenExistingDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					ArrayList<String> commandInfo = new ArrayList<String>();
					String filename = chooser.getSelectedFile().toString();
					commandInfo.add("load");
					commandInfo.add(filename);
					controller.enact(commandInfo);
					MainWindow main = new MainWindow(controller, versionsManager);
					frame.dispose();
				}
			}
		});
		btnOpenExistingDocument.setBounds(89, 92, 278, 36);
		frame.getContentPane().add(btnOpenExistingDocument);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(99, 169, 268, 25);
		frame.getContentPane().add(btnExit);
	}
}
