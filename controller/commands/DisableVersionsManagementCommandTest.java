package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

class DisableVersionsManagementCommandTest {
	
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private EditCommand editCommand = new EditCommand(controller, versionsManager);
	private DisableVersionsManagementCommand disableCommand = new DisableVersionsManagementCommand(controller, versionsManager);

	@Test
	void testVolatile() {
		VersionsStrategy strategy = new VolatileVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		versionsManager.setStrategyType("volatile");
		disableCommand.execute();
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("edit");
		commandInfo.add("newContents");
		editCommand.getParameters(commandInfo);
		editCommand.execute();
		
		assertEquals(versionsManager.isEnabled(), false);
		assertEquals(strategy.getEntireHistory().size(), 0);
	}
}
