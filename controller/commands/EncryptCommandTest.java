package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

class EncryptCommandTest {
	
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private CommandSuperclass encryptCommand = new EncryptCommand(controller, versionsManager);
	private CommandSuperclass decryptCommand = new DecryptCommand(controller, versionsManager);

	@Test
	void test() {
		Document doc = new Document();
		doc.setContents("doc contents");
		controller.setCurrentDocument(doc);
		controller.setFilename("beforeencryptcommandtest");
		
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("encrypt");
		commandInfo.add("Atbash");
		commandInfo.add("encryptcommandtest");
		encryptCommand.getParameters(commandInfo);
		encryptCommand.execute();
		
		controller.getCurrentDocument().setDecyptionType("Atbash");
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		controller.setFilename("encryptcommandtest");
		
		decryptCommand.execute();
		
		String contents = controller.getCurrentDocument().getContents();
		
		System.out.println("contents: " + contents);
		
		System.out.println("actual contents: " + actualContents);
		
		assertEquals(contents, actualContents);
	}

}
