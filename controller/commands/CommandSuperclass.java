package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;
import model.VersionsManager;

public abstract class CommandSuperclass implements Command{

	private static LatexEditorController controller;
	private static VersionsManager manager;
	
	public CommandSuperclass(LatexEditorController editorController, VersionsManager versionsManager) {
		controller = editorController;
		manager = versionsManager;
	}
	

	public VersionsManager getManager() {
		return manager;
	}
	
	public LatexEditorController getController() {
		return controller;
	}

	@Override
	public abstract void execute();
	public abstract void getParameters(ArrayList<String> info);
	
}
