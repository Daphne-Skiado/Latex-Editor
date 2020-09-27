package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;

public class EncryptCommand extends CommandSuperclass {
	private String cipher_type;
	private String filename;

	public EncryptCommand(LatexEditorController editorController, VersionsManager versionsManager) {
		super(editorController, versionsManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String text = super.getController().getCurrentDocument().getContents();
		String encrypted_text;
		if (cipher_type.equals("Atbash")) {
			encrypted_text = atbash_encryption(text);
		}
		else {
			encrypted_text = rot_13_encryption(text);
		}
		String enrypted_filename = filename;
		Document encrypted = new Document();
		encrypted.setContents(cipher_type+"\n"+encrypted_text);
		encrypted.save(enrypted_filename);
	}

	@Override
	public void getParameters(ArrayList<String> info) {
		// TODO Auto-generated method stub
		this.cipher_type = info.get(1);
		this.filename = info.get(2);
	}
	
	private String atbash_encryption(String text) {
		String encrypted = "";
		char currentChar;
		int currentAscii;
		int encryptedAscii;
		char encryptedChar;
		for (int i=0; i < text.length(); i++) {
			currentChar = text.charAt(i);
			currentAscii = (int) currentChar;
			if ( currentAscii > 64 && currentAscii < 91) {
				int temp = currentAscii - 65;
				encryptedAscii = 90 - temp;
				encryptedChar = (char) encryptedAscii;
				encrypted = encrypted + encryptedChar;
			}
			else if ( currentAscii > 96 && currentAscii < 123) {
				int temp = currentAscii - 97;
				encryptedAscii = 122 - temp;
				encryptedChar = (char) encryptedAscii;
				encrypted = encrypted + encryptedChar;
			}
			else {
				encrypted = encrypted + currentChar;
			}
		}
		return encrypted;
	}
	
	private String rot_13_encryption(String text) {
		String encrypted = "";
		char currentChar;
		int currentAscii;
		int encryptedAscii;
		char encryptedChar;
		for (int i=0; i < text.length(); i++) {
			currentChar = text.charAt(i);
			currentAscii = (int) currentChar;
			if ( currentAscii > 64 && currentAscii < 91) {
				encryptedAscii = currentAscii + 13;
				if (encryptedAscii > 90) {
					encryptedAscii = 64 + (encryptedAscii - 90);
				}
				encryptedChar = (char) encryptedAscii;
				encrypted = encrypted + encryptedChar;
			}
			else if ( currentAscii > 96 && currentAscii < 123) {
				encryptedAscii = currentAscii + 13;
				if (encryptedAscii > 122) {
					encryptedAscii = 96 + (encryptedAscii - 122);
				}
				encryptedChar = (char) encryptedAscii;
				encrypted = encrypted + encryptedChar;
			}
			else {
				encrypted = encrypted + currentChar;
			}
		}
		return encrypted;
	}
}
