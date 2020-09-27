package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;


class AddLatexCommandTest {
	
	private VersionsManager versionsManager = new VersionsManager(null);
	private LatexEditorController controller = new LatexEditorController(versionsManager);
	private CommandSuperclass addLatexCommand = new AddLatexCommand(controller, versionsManager);

	@Test
	void test1() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("addLatex");
		commandInfo.add("chapter");
		commandInfo.add("...\n");
		commandInfo.add("\n...\n");
		addLatexCommand.getParameters(commandInfo);
		addLatexCommand.execute();
		String contents = "...\n" + "\\chapter{...}\n" + "\n...\n";
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test2() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("addLatex");
		commandInfo.add("section");
		commandInfo.add("...\n");
		commandInfo.add("\n...\n");
		addLatexCommand.getParameters(commandInfo);
		addLatexCommand.execute();
		String contents = "...\n" + "\\section{...}\n" + "\n...\n";
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test3() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("addLatex");
		commandInfo.add("subsection");
		commandInfo.add("...\n");
		commandInfo.add("\n...\n");
		addLatexCommand.getParameters(commandInfo);
		addLatexCommand.execute();
		String contents = "...\n" + "\\subsection{...}\n" + "\n...\n";
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test4() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("addLatex");
		commandInfo.add("subsubsection");
		commandInfo.add("...\n");
		commandInfo.add("\n...\n");
		addLatexCommand.getParameters(commandInfo);
		addLatexCommand.execute();
		String contents = "...\n" + "\\subsubsection{...}\n"+ "\n...\n";
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test5() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("addLatex");
		commandInfo.add("enumerate");
		commandInfo.add("...\n");
		commandInfo.add("\n...\n");
		addLatexCommand.getParameters(commandInfo);
		addLatexCommand.execute();
		String contents = "...\n" + "\\begin{enumerate}\n" + 
				"\\item ...\n" + 
				"\\item ...\n" + 
				"\\end{enumerate}\n" + "\n...\n";
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test6() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("addLatex");
		commandInfo.add("itemize");
		commandInfo.add("...\n");
		commandInfo.add("\n...\n");
		addLatexCommand.getParameters(commandInfo);
		addLatexCommand.execute();
		String contents = "...\n" + "\\begin{itemize}\n" + 
				"\\item ...\n" + 
				"\\item ...\n" + 
				"\\end{itemize}\n" + "\n...\n";
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test7() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("addLatex");
		commandInfo.add("table");
		commandInfo.add("...\n");
		commandInfo.add("\n...\n");
		addLatexCommand.getParameters(commandInfo);
		addLatexCommand.execute();
		String contents = "...\n" + "\\begin{table}\n" + 
				"\\caption{....}\\label{...}\n" + 
				"\\begin{tabular}{|c|c|c|}\n" + 
				"\\hline\n" + 
				"... &...&...\\" + "\n" + 
				"... &...&...\\" + "\n" + 
				"... &...&...\\" + "\n" + 
				"\\hline\n" + 
				"\\end{tabular}\n" + 
				"\\end{table}\n" + 
				"" + "\n...\n";
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test8() {
		Document doc = new Document();
		controller.setCurrentDocument(doc);
		ArrayList<String> commandInfo = new ArrayList<String>();
		commandInfo.add("addLatex");
		commandInfo.add("figure");
		commandInfo.add("...\n");
		commandInfo.add("\n...\n");
		addLatexCommand.getParameters(commandInfo);
		addLatexCommand.execute();
		String contents = "...\n" + "\\begin{figure}\n" + 
				"\\includegraphics[width=...,height=...]{...}\n" + 
				"\\caption{....}\\label{...}\n" + 
				"\\end{figure}\n" + "\n...\n";
		
		String actualContents = controller.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}

}
