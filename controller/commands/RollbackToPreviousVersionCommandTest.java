package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;


class RollbackToPreviousVersionCommandTest {
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private CommandSuperclass editCommand = new EditCommand(controller, versionsManager);
	private CommandSuperclass enableCommand = new EnableVersionsManagementCommand(controller, versionsManager);
	private CommandSuperclass rollback = new RollbackToPreviousVersionCommand(controller, versionsManager);
	
	
	@Test
	void testStable() {
		VersionsStrategy strategy = new VolatileVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		Document doc = new Document();
		doc.setContents("doc contents");
		controller.setCurrentDocument(doc);
		versionsManager.setStrategyType("volatile");
		enableCommand.execute();
		
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("edit");
		commandInfo.add("versionContents");
		editCommand.getParameters(commandInfo);
		editCommand.execute();
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		controller.getCurrentDocument().setContents("newContents");
		
		if (versionsManager.isEnabled()){
			System.out.println("ok");
		}
		rollback.execute();
		String contents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
}
