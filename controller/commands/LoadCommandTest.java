package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.VersionsManager;

class LoadCommandTest {
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private CommandSuperclass loadCommand = new LoadCommand(controller, versionsManager);

	@Test
	void test() {
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("load");
		commandInfo.add("loadcommandtest");
		loadCommand.getParameters(commandInfo);
		loadCommand.execute();
		
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream("loadcommandtest"));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(fileContents, actualContents);
	}

}
