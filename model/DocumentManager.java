package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class DocumentManager {
	private HashMap<String, Document> templates;
	
	public DocumentManager() {
		templates = new HashMap<String, Document>();
		
		createTemplatesHashmap();
	}
	
	public Document createDocument(String type) {
		return templates.get(type).clone();
	}

	public String getContents(String type) {
		String contents = "";
		Scanner s = null;
		try {
			s = new Scanner(new File(type));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.useDelimiter("\\s+"); 
		while (s.hasNext()) { 
			 if (s.hasNextLine()) { 
				 contents = contents.concat(s.nextLine() + "\n");
			 } 
			 else { 
				 contents = contents.concat(s.next());
			 } 
		}
		s.close();
		return contents;
	}

	private void createTemplatesHashmap() {
		
		String template = "";
		Scanner s = null;
		try {
			s = new Scanner(new File("templates"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.useDelimiter("\\s+"); 
		while (s.hasNext()) { 
			 if (s.hasNextLine()) { 
				 template = s.nextLine();
				 Document doc = new Document();
				 String contents = getContents(template);
				 doc.setContents(contents);
				 templates.put(template, doc);
			 }
		}
		s.close();
	}
}
