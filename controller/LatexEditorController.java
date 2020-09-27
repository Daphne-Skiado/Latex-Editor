package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import controller.commands.CommandSuperclass;
import controller.commands.CommandFactory;
import model.Document;
import model.VersionsManager;
import view.MainWindow;

public class LatexEditorController{
	private HashMap<String, CommandSuperclass> commands;
	private Document currentDocument = null;
	private String filename;
	private MainWindow main;
	
	public LatexEditorController(VersionsManager versionsManager) {

		loadCommandHashmap(versionsManager);
		
	}
	
	public void enact(ArrayList<String> info) {
		if(info.size()>0){
			commands.get(info.get(0)).getParameters(info);
			commands.get(info.get(0)).execute();
		}else {
			commands.get(info.get(0)).execute();
		}
	}
	
	public void setCurrentDocument(Document doc) {
		this.currentDocument = doc;
	}
	
	public Document getCurrentDocument() {
		return this.currentDocument;
	}
	
	public String getFilename() {
		return this.filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void setMainWindow(MainWindow mainWin) {
		main = mainWin;
	}
	
	public MainWindow getMainWindow() {
		return main;
	}

	private void loadCommandHashmap(VersionsManager versionsManager) {
		CommandFactory commandFactory = new CommandFactory(versionsManager, this);
		
		commands = new HashMap<String, CommandSuperclass>();
		
		String commandName;
		Scanner s = null;
		try {
			s = new Scanner(new File("commands"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.useDelimiter("\\s+"); 
		while (s.hasNext()) { 
			 commandName= "";
			 if (s.hasNextLine()) { 
				 commandName = commandName.concat(s.nextLine());
				 commands.put(commandName, commandFactory.createCommand(commandName));
			 }
		}
		
		s.close();
	}
	
}
