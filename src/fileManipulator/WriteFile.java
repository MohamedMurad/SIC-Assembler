package fileManipulator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import validation.Exceptions;

public class WriteFile {

	public void writeIntermediateFile(File file, String cPath, String intermedFileContent) {
		PrintWriter writer = null;
		String fileName = "\\" + file.getName().substring(0, file.getName().indexOf('.')) + "intermed.asm";
		try {
			writer = new PrintWriter(cPath + fileName, "UTF-8");
			writer.write(intermedFileContent.toString());
			writer.close();

		} catch (IOException e) {
			Exceptions.getInstance().throwCouldNotWriteException();
		} finally {
			writer.close();
		}
	}

	public void writeObjFile(File file, String cPath, String objFileContent) {
		PrintWriter writer = null;
		String fileName = "\\" + file.getName().substring(0, file.getName().indexOf('.')) + ".o";
		try {
			writer = new PrintWriter(cPath + fileName, "UTF-8");
			writer.write(objFileContent.toString());
			writer.close();
		} catch (IOException e) {
			Exceptions.getInstance().throwCouldNotWriteException();
		} finally {
			writer.close();
		}
	}
}