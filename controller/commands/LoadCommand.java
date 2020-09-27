package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

public class LoadCommand extends CommandSuperclass {
	private String type;
	private String filename;
	
	public LoadCommand(LatexEditorController controller, VersionsManager manager) {
		super(controller, manager);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		loadFromFile();
	}

	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		this.filename = info.get(1);
		super.getController().setFilename(this.filename);
	}
	
	public void loadFromFile() {
		// TODO Auto-generated method stub
		String fileContents = "";
		String line ="";
		String decryptionType = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(this.filename));
			while(scanner.hasNextLine()) {
				line = scanner.nextLine();
				if(!(line.contentEquals("Atbash")) && !(line.contentEquals("Rot-13"))) {
					fileContents = fileContents + line + "\n";
				}else {
					decryptionType = line;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.getController().setCurrentDocument(new Document());
		super.getController().getCurrentDocument().setContents(fileContents);
		super.getController().getCurrentDocument().setDecyptionType(decryptionType);
		
		type = "emptyTemplate";
		
		fileContents = fileContents.trim();
		if(fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
			type = "articleTemplate";
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
			type = "bookTemplate";
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
			type = "reportTemplate";
		}
		else if(fileContents.startsWith("\\documentclass{letter}")) {
			type = "letterTemplate";
		}
		super.getController().getCurrentDocument().setType(type);
		
	}
	

}
