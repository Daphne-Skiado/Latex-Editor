package controller.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import controller.LatexEditorController;
import model.VersionsManager;

public class AddLatexCommand extends CommandSuperclass{	
	private HashMap<String, String> latexHashmap;
	private String latexCommand = "";
	private String before;
	private String after;
	
	public AddLatexCommand(LatexEditorController controller, VersionsManager manager) {
		super(controller, manager);
		createLatexCommandHashmap();
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String latexCode = latexHashmap.get(latexCommand);
		String newContents = before + latexCode + after;
		super.getController().getCurrentDocument().setContents(newContents);
	}


	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		latexCommand = info.get(1);
		before = info.get(2);
		after = info.get(3);
	}
	
	private void createLatexCommandHashmap() {
		latexHashmap = new HashMap<String, String>();
		
		String commandName = "";
		Scanner s = null;
		try {
			s = new Scanner(new File("addLatexCommands"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.useDelimiter("\\s+"); 
		while (s.hasNext()) { 
			 if (s.hasNextLine()) { 
				 commandName = s.nextLine();
				 String contents = getCommandCode(commandName);
				 latexHashmap.put(commandName, contents);
			 }
		}
		s.close();
	}
	
	private String getCommandCode(String commandName) {
		String contents = "";
		Scanner s = null;
		try {
			s = new Scanner(new File(commandName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.useDelimiter("\\s+"); 
		while (s.hasNext()) { 
			 if (s.hasNextLine()) { 
				 contents= contents.concat(s.nextLine() + "\n");
			 } 
			 else { 
				 contents = contents.concat(s.next());
			 } 
		}
		s.close();
		return contents;
	}
	

}
