package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

class EditCommandTest {
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private CommandSuperclass editCommand = new EditCommand(controller, versionsManager);

	@Test
	void test() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		controller.getCurrentDocument().setType("emptyTemplate");
				
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("edit");
		commandInfo.add("newContents");
		editCommand.getParameters(commandInfo);
		editCommand.execute();
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals("newContents", actualContents);
	}

}
