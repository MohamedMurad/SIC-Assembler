package validation;

public class Exceptions {

	private Exceptions() {
		// TODO Auto-generated constructor stub
	}

	private static Exceptions instance = null;

	public static Exceptions getInstance() {
		if (instance == null) {
			instance = new Exceptions();
		}
		return instance;
	}

	public void throwOpCodeNotFoundException() {
		throw new RuntimeException("operation code is not found");
	}

	public void throwOpCodeInvalidException() {
		throw new RuntimeException("operation code is invalid");
	}

	public void throwDuplicatedLabelException() {
		throw new RuntimeException("duplicated label");
	}

	public void throwValidLineLenException() {
		throw new RuntimeException("line length exceeded the Max length");
	}

	public void throwTabException() {
		throw new RuntimeException("tab are not allowed");
	}

	public void throwBlankException() {
		throw new RuntimeException("Identation Error, missing blanks in col 8,16 or 17");
	}

	public void throwInvalidPrefixLabelException() {
		throw new RuntimeException("Invalid prefix label");
	}

	public void throwInvalidSpacePrefixException() {
		throw new RuntimeException("Invalid space prefix");
	}

	public void throwCouldNotReadException() {
		throw new RuntimeException("Could not read the file");
	}

	public void throwCouldNotWriteException() {
		throw new RuntimeException("Could not write the file");
	}
}
