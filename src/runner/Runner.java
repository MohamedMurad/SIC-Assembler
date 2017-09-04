package runner;

import java.io.File;
import java.util.ArrayList;

import data.Data;
import fileManipulator.ReadFile;
import fileManipulator.WriteFile;
import sicAssembler.Pass1;
import sicAssembler.Pass2;

public class Runner {

	ReadFile readFile;
	WriteFile writeFile;
	Pass1 pass1;
	Pass2 pass2;

	public Runner() {
		// TODO Auto-generated constructor stub
		readFile = new ReadFile();
		writeFile = new WriteFile();
		pass1 = new Pass1();
		pass2 = new Pass2();
	}

	public void assemble(String fileName, String path) {
		String fileName1 = "\\" + fileName + ".asm";
		File file1 = new File(path + fileName1);
		ArrayList<String> lines = readFile.readSrcFile(file1);
		String intermedFileContent = new String(pass1.run(lines));
		writeFile.writeIntermediateFile(file1, path, intermedFileContent);
		if (Data.errorMsgFlag) {
			String fileName2 = "\\" + fileName + "intermed.asm";
			File file2 = new File(path + fileName2);
			ArrayList<String> lines2 = readFile.readSrcFile(file2);
			String objFileContent = new String(pass2.run(lines2));
			writeFile.writeObjFile(file2, path, objFileContent);

			String fileName3 = "\\" + fileName + "ListingFile.txt";
			File file3 = new File(path + fileName3);
			String listingFileContent = new String(pass2.getListingFileContent());
			writeFile.writeIntermediateFile(file3, path, listingFileContent);
		}
	}

	public static void main(String[] args) {
		Runner runner = new Runner();
		// your selected path from GUI.
		String path = new String(System.getProperty("user.home"));
		// your selected name from GUI.
		String fileName = new String("test");
		runner.assemble(fileName, path);
	}
}