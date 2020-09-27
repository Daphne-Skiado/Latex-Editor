package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;
import model.VersionsManager;

public class EnableVersionsManagementCommand extends CommandSuperclass{
	
	public EnableVersionsManagementCommand(LatexEditorController controller, VersionsManager manager) {
		super(controller, manager);
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		super.getManager().enableStrategy();
	}


	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		
	}

}
