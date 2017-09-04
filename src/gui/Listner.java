package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import fileManipulator.ReadFile;
import fileManipulator.WriteFile;
import runner.Adapter;
import runner.Runner;
import validation.Exceptions;

public class Listner implements ActionListener {

	private AssemblerPanel assemPanel;
	private AssemblerFrame assemFrame;
	private Runner runner;
	private File currentFile;
	private ReadFile readFile;
	private WriteFile writeFile;
	private boolean unsavedWork = true;

	public Listner(AssemblerFrame f, AssemblerPanel p) {
		assemFrame = f;
		assemPanel = p;
		runner = new Runner();
		readFile = new ReadFile();
	}

	private File getFile(String label) {
		JFileChooser test = new JFileChooser();
		test.showDialog(null, label);
		File file = test.getSelectedFile();
		if (file == null)
			return null;

		return file;
	}

	private void setAreaContent(ArrayList<String> contentList) {
		String content = contentList.get(0);
		for (int i = 1; i < contentList.size(); i++) {
			content += "\n";
			content += contentList.get(i);
		}
		assemPanel.area.setText(content);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Object s = event.getSource();
		if (s == assemFrame.newF) {
			// this
			unsavedWork = true;
			assemPanel.area.setText("");
			File newFile = new File(getFile("Add New File").getAbsolutePath() + ".asm");
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			currentFile = newFile;
			assemPanel.currentPath.setText("Path: " + currentFile.getAbsolutePath());

		} else if (s == assemFrame.open) {
			currentFile = getFile("Load");
			setAreaContent(readFile.readSrcFile(currentFile));
			assemPanel.currentPath.setText("Path: " + currentFile.getAbsolutePath());

		} else if (s == assemFrame.save) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(currentFile, "UTF-8");
				writer.write(assemPanel.area.getText().toString());
				writer.close();
				unsavedWork = false;
			} catch (IOException e) {
				Exceptions.getInstance().throwCouldNotWriteException();
			} finally {
				writer.close();
			}

		} else if (s == assemFrame.exit) {
			System.exit(0);
		} else if (s == assemFrame.assemble) {
			Adapter adapter = new Adapter();
			adapter.assemble(currentFile);
			// if(!unsavedWork)
			// runner.assemble(currentFile);
			// else{
			// JOptionPane.showMessageDialog(null, "Warning ! , Unsaved Work",
			// "Warning", JOptionPane.INFORMATION_MESSAGE);
			//
			// }
			// you need to adjust writer by adding two variables that hold the
			// two
			// output files: object file and list file
			// so that we can do the following: (look at next else if)

		} else if (s == assemFrame.objectFile) {

			// setAreaContent(readFile.readSrcFile(writer.getObjectFile()));

		} else if (s == assemFrame.listFile) {
			// setAreaContent(readFile.readSrcFile(writer.getListFile());
		}
	}
}