package validation;

import data.Data;

public class Parser {

	// return false in case of exceptions.
	public boolean parseUnCheckedDataLine() {
		String line = new String(Data.line);
		Data.label = new String(line.substring(0, 8));
		if (line.length() > 15) {
			Data.opCode = new String(line.substring(9, 15));
			if (line.length() <= 35) {
				if (line.length() > 17) {
					Data.operand = line.substring(17, line.length());
				} else {
					Data.operand = new String();
				}
				Data.comment = new String();
			} else {
				Data.operand = line.substring(17, 35);
				Data.comment = line.substring(35, line.length());
			}
		} else {
			Data.opCode = new String(line.substring(9, line.length()));
			Data.operand = new String();
			Data.comment = new String();
		}
		try {
			Handler.getInstance().validLineLen();
			Handler.getInstance().tabNotExists();
			Handler.getInstance().blankExists();
			Handler.getInstance().validData();
			Data.label = Data.label.trim();
			Data.opCode = Data.opCode.trim();
			Data.operand = Data.operand.trim();
			Data.comment = Data.comment.trim();
			Handler.getInstance().validTrimedData();
		} catch (Exception e) {
			Data.errorMsg = new String(e.getMessage());
			return false;
		}

		return true;
	}

	public void parseCheckedDataLine() {
		String line = new String(Data.line);
		Data.label = new String(line.substring(0, 8));
		if (line.length() > 15) {
			Data.opCode = new String(line.substring(9, 15));
			if (line.length() <= 35) {
				if (line.length() > 17) {
					Data.operand = line.substring(17, line.length());
				} else {
					Data.operand = new String();
				}
				Data.comment = new String();
			} else {
				Data.operand = line.substring(17, 35);
				Data.comment = line.substring(35, line.length());
			}
		} else {
			Data.opCode = new String(line.substring(9, line.length()));
			Data.operand = new String();
			Data.comment = new String();
		}
		Data.label = Data.label.trim();
		Data.opCode = Data.opCode.trim();
		Data.operand = Data.operand.trim();
		Data.comment = Data.comment.trim();
	}
}