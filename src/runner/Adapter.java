package runner;

import java.io.File;

public class Adapter {

	public void assemble(File file) {
		String fileName = file.getName().substring(0, file.getName().indexOf('.'));
		String path = file.getPath().substring(0, file.getPath().indexOf(fileName + "."));
		Runner runner = new Runner();
		runner.assemble(fileName, path);
	}
}