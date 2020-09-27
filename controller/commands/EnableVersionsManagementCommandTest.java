package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

class EnableVersionsManagementCommandTest {
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private CommandSuperclass editCommand = new EditCommand(controller, versionsManager);
	private CommandSuperclass enableCommand = new EnableVersionsManagementCommand(controller, versionsManager);

	@Test
	void testVolatile() {
		VersionsStrategy strategy = new VolatileVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		controller.getCurrentDocument().setContents("doc contents");
		String actualContents = controller.getCurrentDocument().getContents();
		versionsManager.setStrategyType("volatile");
		enableCommand.execute();
		
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("edit");
		commandInfo.add("newContents");
		editCommand.getParameters(commandInfo);
		editCommand.execute();
		
		actualContents = controller.getCurrentDocument().getContents();
				
		String contents = strategy.getVersion().getContents();
		
		assertEquals(contents, actualContents);
	}
	@Test
	void testStable() {
		VersionsStrategy strategy = new StableVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		controller.getCurrentDocument().setContents("doc contents");
		String actualContents = controller.getCurrentDocument().getContents();
		versionsManager.setStrategyType("stable");
		enableCommand.execute();
		
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("edit");
		commandInfo.add("newContents");
		editCommand.getParameters(commandInfo);
		editCommand.execute();
		
		actualContents = controller.getCurrentDocument().getContents();
				
		String contents = strategy.getVersion().getContents();
		
		assertEquals(contents, actualContents);
	}
}
