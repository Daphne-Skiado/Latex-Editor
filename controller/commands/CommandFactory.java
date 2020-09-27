package controller.commands;

import controller.LatexEditorController;
import model.VersionsManager;

public class CommandFactory {
	private VersionsManager versionsManager;
	private LatexEditorController controller;
	
	
	public CommandFactory(VersionsManager versionsManager, LatexEditorController controller) {
		this.versionsManager = versionsManager;
		this.controller = controller;
	}


	public CommandSuperclass createCommand(String type) {
		if(type.equals("addLatex")) {
			return new AddLatexCommand(this.controller, this.versionsManager);
		}
		if(type.equals("changeVersionsStrategy")) {
			return new ChangeVersionsStrategyCommand(this.controller, this.versionsManager);
		}
		if(type.equals("create")) {
			//return new CreateCommand();
			return new CreateCommand(this.controller, this.versionsManager);
		}
		if(type.equals("disableVersionsManagement")) {
			return new DisableVersionsManagementCommand(this.controller, this.versionsManager);
		}
		if(type.equals("edit")) {
			return new EditCommand(this.controller, this.versionsManager);
		}
		if(type.equals("enableVersionsManagement")) {
			return new EnableVersionsManagementCommand(this.controller, this.versionsManager);
		}
		if(type.equals("load")) {
			return new LoadCommand(this.controller, this.versionsManager);
		}
		if(type.equals("rollbackToPreviousVersion")) {
			return new RollbackToPreviousVersionCommand(this.controller, this.versionsManager);
		}
		if(type.equals("save")) {
			return new SaveCommand(this.controller, this.versionsManager);
		}
		if(type.equals("encrypt")) {
			return new EncryptCommand(this.controller, this.versionsManager);
		}
		if(type.equals("decrypt")) {
			return new DecryptCommand(this.controller, this.versionsManager);
		}
		return null;
	}
}
