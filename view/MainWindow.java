package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;

public class MainWindow {

	private JFrame frame;
	private JEditorPane editorPane = new JEditorPane();
	private ArrayList<String> commandInfo = new ArrayList<String>();
	private VersionsManager versionsManager;
	private LatexEditorController controller;
	
	public void editContents(String type) {
		String contents = editorPane.getText();
		String before = contents.substring(0, editorPane.getCaretPosition());
		String after = contents.substring(editorPane.getCaretPosition());
		String commandToExec = "addLatex";
		commandInfo.clear();
		commandInfo.add(commandToExec);
		commandInfo.add(type);
		commandInfo.add(before);
		commandInfo.add(after);
		controller.enact(commandInfo);
		editorPane.setText(controller.getCurrentDocument().getContents());
	}
	
	public void setText(String contents) {
		editorPane.setText(contents);
	}
	
	/**
	 * Launch the application.
	 */

	public MainWindow(LatexEditorController controller, VersionsManager versionsManager) {
		this.controller = controller;
		this.versionsManager = versionsManager;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 823, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 805, 26);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewFile = new JMenuItem("New file");
		mntmNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChooseTemplate chooseTemplate = new ChooseTemplate( "main", versionsManager, controller);
				frame.dispose();
			}
		});
		mnFile.add(mntmNewFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contents = editorPane.getText();
				commandInfo.clear();
				commandInfo.add("edit");
				commandInfo.add(contents);
				controller.enact(commandInfo);
			}
		});
		mnFile.add(mntmSave);
		JMenuItem addChapter = new JMenuItem("Add chapter");
		JMenu mnCommands = new JMenu("Commands");
		JMenuItem mntmLoadFile = new JMenuItem("Load file");
		mntmLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					commandInfo.clear();
					commandInfo.add("load");
					commandInfo.add(filename);
					controller.enact(commandInfo);
					mnCommands.setEnabled(true);
					addChapter.setEnabled(true);
					if(controller.getCurrentDocument().getType().equals("letterTemplate")) {
						mnCommands.setEnabled(false);
					}
					if(controller.getCurrentDocument().getType().equals("articleTemplate")) {
						addChapter.setEnabled(false);
					}
					editorPane.setText(controller.getCurrentDocument().getContents());
				}
			}
		});
		mnFile.add(mntmLoadFile);
		
		JMenuItem mntmSaveFile = new JMenuItem("Save file");
		mntmSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					if(filename.endsWith(".tex") == false) {
						filename = filename+".tex";
					}
					commandInfo.clear();
					commandInfo.add("save");
					commandInfo.add(filename);
					controller.enact(commandInfo);
				}
				
			}
		});
		mnFile.add(mntmSaveFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		menuBar.add(mnCommands);
		if(controller.getCurrentDocument().getType().equals("letterTemplate")) {
			mnCommands.setEnabled(false);
		}
		
		addChapter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editContents("chapter");
			}
		});
		mnCommands.add(addChapter);
		if(controller.getCurrentDocument().getType().equals("articleTemplate")) {
			addChapter.setEnabled(false);
		}
		
		JMenu addSection = new JMenu("Add Section");
		mnCommands.add(addSection);
		
		JMenuItem mntmAddSection = new JMenuItem("Add section");
		mntmAddSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editContents("section");
			}
		});
		addSection.add(mntmAddSection);
		
		JMenuItem mntmAddSubsection = new JMenuItem("Add subsection");
		mntmAddSubsection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editContents("subsection");
			}
		});
		addSection.add(mntmAddSubsection);
		
		JMenuItem mntmAddSubsubsection = new JMenuItem("Add subsubsection");
		mntmAddSubsubsection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editContents("subsubsection");
			}
		});
		addSection.add(mntmAddSubsubsection);
		
		JMenu addEnumerationList = new JMenu("Add enumeration list");
		mnCommands.add(addEnumerationList);
		
		JMenuItem mntmItemize = new JMenuItem("Itemize");
		mntmItemize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editContents("itemize");
			}
		});
		addEnumerationList.add(mntmItemize);
		
		JMenuItem mntmEnumerate = new JMenuItem("Enumerate");
		mntmEnumerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editContents("enumerate");
			}
		});
		addEnumerationList.add(mntmEnumerate);
		
		JMenuItem addTable = new JMenuItem("Add table");
		addTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editContents("table");
			}
		});
		mnCommands.add(addTable);
		
		JMenuItem addFigure = new JMenuItem("Add figure");
		addFigure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editContents("figure");
			}
		});
		mnCommands.add(addFigure);
		
		JMenu mnStrategy = new JMenu("Strategy");
		menuBar.add(mnStrategy);
		
		JMenu mnEnable = new JMenu("Enable");
		mnStrategy.add(mnEnable);
		
		JCheckBoxMenuItem menuVolatile = new JCheckBoxMenuItem("Volatile");
		JCheckBoxMenuItem menuStable = new JCheckBoxMenuItem("Stable");
		menuStable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				versionsManager.setStrategyType("stable");
				if(versionsManager.isEnabled() == false) {
					commandInfo.clear();
					commandInfo.add("enableVersionsManagement");
					commandInfo.add("stable");
					controller.enact(commandInfo);
				}
				else {
					commandInfo.clear();
					commandInfo.add("changeVersionsStrategy");
					commandInfo.add("stable");
					controller.enact(commandInfo);
				}
				menuVolatile.setSelected(false);
				menuStable.setEnabled(false);
				menuVolatile.setEnabled(true);
			}
		});

		menuVolatile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				versionsManager.setStrategyType("volatile");
				if(versionsManager.isEnabled() == false) {
					commandInfo.clear();
					commandInfo.add("enableVersionsManagement");
					controller.enact(commandInfo);
				}
				else {
					commandInfo.clear();
					commandInfo.add("changeVersionsStrategy");
					controller.enact(commandInfo);
				}
				menuStable.setSelected(false);
				menuVolatile.setEnabled(false);
				menuStable.setEnabled(true);
			}
		});
		mnEnable.add(menuVolatile);
		
		mnEnable.add(menuStable);
		
		JMenuItem mntmDisable = new JMenuItem("Disable");
		mntmDisable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandInfo.clear();
				commandInfo.add("disableVersionsManagement");
				controller.enact(commandInfo);
			}
		});
		mnStrategy.add(mntmDisable);
		
		JMenuItem mntmRollback = new JMenuItem("Rollback");
		mntmRollback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				commandInfo.clear();
				commandInfo.add("rollbackToPreviousVersion");
				controller.enact(commandInfo);
				Document doc = controller.getCurrentDocument();
				editorPane.setText(doc.getContents());
			}
		});
		mnStrategy.add(mntmRollback);
		
		JMenu mnEncyprt = new JMenu("Encryption");;
		menuBar.add(mnEncyprt);
		
		JMenuItem mntmAtbash = new JMenuItem("Atbash");
		mntmAtbash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filename="";
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					filename = filechooser.getSelectedFile().toString();
					if(filename.endsWith(".tex") == false) {
						filename = filename+".tex";
					}
					commandInfo.clear();
					commandInfo.add("encrypt");
					commandInfo.add("Atbash");
					commandInfo.add(filename);
					controller.enact(commandInfo);
					Document doc = controller.getCurrentDocument();
					editorPane.setText(doc.getContents());
				}
			}
		});
		mnEncyprt.add(mntmAtbash);
		
		JMenuItem mntmRot13 = new JMenuItem("Rot-13");
		mntmRot13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filename="";
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					filename = filechooser.getSelectedFile().toString();
					if(filename.endsWith(".tex") == false) {
						filename = filename+".tex";
					}
					commandInfo.clear();
					commandInfo.add("encrypt");
					commandInfo.add("Rot-13");
					commandInfo.add(filename);
					controller.enact(commandInfo);
					Document doc = controller.getCurrentDocument();
					editorPane.setText(doc.getContents());
				}
			}
		});
		mnEncyprt.add(mntmRot13);
		
		JMenu mnDecryprt = new JMenu("Decryption");;
		menuBar.add(mnDecryprt);
		
		JMenuItem mntmEnableDecrypt = new JMenuItem("Enable");
		mntmEnableDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				commandInfo.clear();
				commandInfo.add("decrypt");
				controller.enact(commandInfo);
				Document doc = controller.getCurrentDocument();
				editorPane.setText(doc.getContents());
			}
		});
		mnDecryprt.add(mntmEnableDecrypt);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 783, 467);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(editorPane);
		editorPane.setText(controller.getCurrentDocument().getContents());
		 
		
		
	}
	
}
