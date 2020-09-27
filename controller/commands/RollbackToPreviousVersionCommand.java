package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

public class RollbackToPreviousVersionCommand extends CommandSuperclass {	
	
	public RollbackToPreviousVersionCommand(LatexEditorController controller, VersionsManager manager) {
		super(controller, manager);
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Document newDoc = super.getManager().rollback();
		if (!(newDoc == null)) {
			super.getController().setCurrentDocument(newDoc);
		}
	}


	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		
	}

}
