package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;


class ChangeVersionsStrategyCommandTest {
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private ChangeVersionsStrategyCommand changeCommand = new ChangeVersionsStrategyCommand(controller, versionsManager);
	
	@Test
	void testVolatile() {
		VersionsStrategy strategy = new VolatileVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		versionsManager.setStrategyType("stable");
		changeCommand.execute();
		
		String test = "test ok";
		if(versionsManager.getStrategy() instanceof VolatileVersionsStrategy)
			test = "not ok";
		
		assertEquals("test ok", test);
	}
	
	@Test
	void testStable() {
		VersionsStrategy strategy = new StableVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		versionsManager.setStrategyType("volatile");
		changeCommand.execute();
		
		String test = "test ok";
		if(versionsManager.getStrategy() instanceof StableVersionsStrategy)
			test = "not ok";
		
		assertEquals("test ok", test);
	}
		
}
