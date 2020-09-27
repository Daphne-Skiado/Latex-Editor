package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;
import model.VersionsManager;

public class SaveCommand extends CommandSuperclass {
	private String filename;
	
	public SaveCommand(LatexEditorController controller, VersionsManager manager) {
		// TODO Auto-generated constructor stub
		super(controller, manager);
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		super.getController().getCurrentDocument().save(this.filename);
		if(super.getManager().isEnabled()) {
			super.getManager().putVersion(super.getController().getCurrentDocument());
			super.getController().getCurrentDocument().changeVersion();
		}
	}
	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		this.filename = info.get(1);
		super.getController().setFilename(this.filename);
	}

}
