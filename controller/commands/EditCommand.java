package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;
import model.VersionsManager;

public class EditCommand extends CommandSuperclass {	
	private String newContents;
	
	public EditCommand(LatexEditorController controller, VersionsManager manager) {
		super(controller, manager);
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		super.getController().getCurrentDocument().setContents(newContents);
		if (super.getManager().isEnabled()) {
			super.getManager().putVersion(super.getController().getCurrentDocument());
		}

	}


	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		newContents = info.get(1);
	}

}
