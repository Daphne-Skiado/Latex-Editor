package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;

public class CreateCommand extends CommandSuperclass {
	private DocumentManager documentManager = new DocumentManager();
	private String type;
	
	public CreateCommand(LatexEditorController controller, VersionsManager manager) {
		super(controller, manager);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Document document = documentManager.createDocument(type);
		document.setType(type);
		super.getController().setCurrentDocument(document);
	}

	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		type = info.get(1);
	}

}
