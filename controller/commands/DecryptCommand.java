package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

public class DecryptCommand extends CommandSuperclass{
	private String cipher_type;
	private String filename;

	public DecryptCommand(LatexEditorController editorController, VersionsManager versionsManager) {
		super(editorController, versionsManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String text = super.getController().getCurrentDocument().getContents();
		filename = super.getController().getFilename();
		cipher_type = super.getController().getCurrentDocument().getDecryptionType();
		String decrypted_text;
		if (cipher_type.equals("Atbash")) {
			decrypted_text = atbash_decryption(text);
		}
		else {
			decrypted_text = rot_13_decryption(text);
		}
		String enrypted_filename = filename;
		Document decrypted = new Document();
		decrypted.setContents(decrypted_text);
		decrypted.save(enrypted_filename);
		super.getController().setCurrentDocument(decrypted);
	}

	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		
	}
	
	public String atbash_decryption(String text) {
		String decrypted = "";
		char currentChar;
		int currentAscii;
		int decryptedAscii;
		char decryptedChar;
		for (int i=0; i < text.length(); i++) {
			currentChar = text.charAt(i);
			currentAscii = (int) currentChar;
			if ( currentAscii > 64 && currentAscii < 91) {
				int temp = currentAscii - 65;
				decryptedAscii = 90 - temp;
				decryptedChar = (char) decryptedAscii;
				decrypted = decrypted + decryptedChar;
			}
			else if ( currentAscii > 96 && currentAscii < 123) {
				int temp = currentAscii - 97;
				decryptedAscii = 122 - temp;
				decryptedChar = (char) decryptedAscii;
				decrypted = decrypted + decryptedChar;
			}
			else {
				decrypted = decrypted + currentChar;
			}
		}
		return decrypted;
	}
	
	public String rot_13_decryption(String text) {
		String decrypted = "";
		char currentChar;
		int currentAscii;
		int decryptedAscii;
		char decryptedChar;
		for (int i=0; i < text.length(); i++) {
			currentChar = text.charAt(i);
			currentAscii = (int) currentChar;
			if ( currentAscii > 64 && currentAscii < 91) {
				decryptedAscii = currentAscii + 13;
				if (decryptedAscii > 90) {
					decryptedAscii = 64 + (decryptedAscii - 90);
				}
				decryptedChar = (char) decryptedAscii;
				decrypted = decrypted + decryptedChar;
			}
			else if ( currentAscii > 96 && currentAscii < 123) {
				decryptedAscii = currentAscii + 13;
				if (decryptedAscii > 122) {
					decryptedAscii = 96 + (decryptedAscii - 122);
				}
				decryptedChar = (char) decryptedAscii;
				decrypted = decrypted + decryptedChar;
			}
			else {
				decrypted = decrypted + currentChar;
			}
		}
		return decrypted;
	}

}
