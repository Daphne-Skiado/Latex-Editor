package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

class SaveCommandTest {
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private CommandSuperclass saveCommand = new SaveCommand(controller, versionsManager);

	@Test
	void test() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		controller.getCurrentDocument().setContents("save command test");
		
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("save");
		commandInfo.add("savecommandtest");
		saveCommand.getParameters(commandInfo);
		saveCommand.execute();
		
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream("savecommandtest"));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			fileContents = fileContents.substring(0, fileContents.length()-1);
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(fileContents, actualContents);
	}

}
